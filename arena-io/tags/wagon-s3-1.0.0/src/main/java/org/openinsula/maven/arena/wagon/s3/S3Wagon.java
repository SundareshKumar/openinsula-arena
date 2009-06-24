package org.openinsula.maven.arena.wagon.s3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.maven.wagon.AbstractWagon;
import org.apache.maven.wagon.ConnectionException;
import org.apache.maven.wagon.ResourceDoesNotExistException;
import org.apache.maven.wagon.TransferFailedException;
import org.apache.maven.wagon.authentication.AuthenticationException;
import org.apache.maven.wagon.authorization.AuthorizationException;
import org.apache.maven.wagon.events.TransferEvent;
import org.apache.maven.wagon.resource.Resource;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.jets3t.service.utils.Mimetypes;

/**
 * A REST impl of S3 Wagon.
 * 
 * @author Eric Redmond
 * @author Edson Yanaga
 */
public class S3Wagon extends AbstractWagon {

	private S3Service s3Service;

	private S3Bucket bucket;

	private AccessControlList bucketAcl;

	private String basedir;

	public void openConnection() throws ConnectionException, AuthenticationException {
		if (authenticationInfo == null) {
			throw new AuthenticationException("Authentication info must be set prior to opening S3 connection.");
		}

		String username = authenticationInfo.getUserName();
		String password = authenticationInfo.getPassword();

		AWSCredentials awsCredentials = new AWSCredentials(username, password);

		String host = getRepository().getHost();
		basedir = getRepository().getBasedir();

		try {
			s3Service = new RestS3Service(awsCredentials);

			bucket = s3Service.getOrCreateBucket(host);

			bucketAcl = s3Service.getBucketAcl(bucket);
			bucketAcl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			bucket.setAcl(bucketAcl);
			s3Service.putBucketAcl(bucket);
		}
		catch (S3ServiceException e) {
			throw new ConnectionException("Cannot create service", e);
		}
	}

	public void get(String resourceName, File destination) throws TransferFailedException,
			ResourceDoesNotExistException, AuthorizationException {
		Resource resource = new Resource(resourceName);

		fireGetInitiated(resource, destination);

		S3Object s3Object = null;

		try {
			s3Object = s3Service.getObject(bucket, resourceName);

			BufferedInputStream bis = new BufferedInputStream(s3Object.getDataInputStream());

			FileOutputStream fos = new FileOutputStream(destination);

			byte[] readerBytes = new byte[1024 * 8];
			int size = 0;
			while ((size = bis.read(readerBytes)) > 0) {
				fos.write(readerBytes, 0, size);
			}
		}
		catch (S3ServiceException e) {
			throw new ResourceDoesNotExistException("Cannot put object to S3", e);
		}
		catch (FileNotFoundException e) {
			throw new ResourceDoesNotExistException("", e);
		}
		catch (IOException e) {
			throw new TransferFailedException("", e);
		}

		fireGetCompleted(resource, destination);
	}

	public boolean getIfNewer(String resourceName, File destination, long timestamp) throws TransferFailedException,
			ResourceDoesNotExistException, AuthorizationException {
		Resource resource = new Resource(resourceName);

		S3Object s3Object = null;

		try {
			s3Object = s3Service.getObjectDetails(bucket, resource.getName());

			Date lastModified = s3Object.getLastModifiedDate();

			if (lastModified.getTime() > timestamp) {
				get(resourceName, destination);

				return true;
			}
		}
		catch (S3ServiceException e) {
			throw new TransferFailedException("Cannot put object to S3", e);
		}

		return false;
	}

	public void put(File source, String destination) throws TransferFailedException, ResourceDoesNotExistException,
			AuthorizationException {
		Resource resource = new Resource(destination);
		resource.setContentLength(source.length());
		resource.setLastModified(source.lastModified());
		
		InputStream is;
		try {
			is = new FileInputStream(source);
		}
		catch (FileNotFoundException e) {
			fireTransferError(resource, e, TransferEvent.REQUEST_PUT);

			throw new ResourceDoesNotExistException("Cannot find source file", e);
		}

		try {
			S3Object s3Object = new S3Object(destination);

			s3Object.setAcl(bucketAcl);
			s3Object.setDataInputStream(is);
			s3Object.setContentLength(source.length());
			String mimetype = Mimetypes.getInstance().getMimetype(source);
			s3Object.setContentType(mimetype);

			s3Service.putObject(bucket, s3Object);
		}
		catch (Exception e) {
			throw new TransferFailedException("Cannot put object to S3", e);
		}
	}

	@Override
	public boolean supportsDirectoryCopy() {
		return true;
	}

	@Override
	public void putDirectory(File sourceDirectory, String destinationDirectory) throws TransferFailedException,
			ResourceDoesNotExistException, AuthorizationException {

		for (File file : sourceDirectory.listFiles()) {
			String destinationName = String.format("%s/%s", destinationDirectory, file.getName());

			if (file.isDirectory()) {
				putDirectory(file, destinationName);
			}
			else {
				StringBuilder sb = new StringBuilder();
				sb.append(basedir.substring(1));

				if (destinationName.startsWith(".")) {
					sb.append(destinationName.substring(1));
				}
				else {
					sb.append(destinationName);
				}

				String filename = sb.toString();
				
				Resource resource = new Resource(sourceDirectory.getName());
				resource.setContentLength(file.length());
				resource.setLastModified(file.lastModified());
				
				put(file, filename);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List getFileList(String destinationDirectory) throws TransferFailedException, ResourceDoesNotExistException,
			AuthorizationException {
		Resource resource = new Resource(destinationDirectory);

		try {
			S3Object[] filteredObjects = s3Service.listObjects(bucket, resource.getName(), null);

			if (filteredObjects == null || filteredObjects.length <= 0) {
				throw new ResourceDoesNotExistException("Could not find file: '" + resource + "'");
			}

			List ret = new ArrayList();
			for (int i = 0; i < filteredObjects.length; i++) {
				String key = filteredObjects[i].getKey();

				ret.add(key.substring(key.lastIndexOf('/') + 1));
			}

			return ret;
		}
		catch (S3ServiceException e) {
			throw new TransferFailedException("Error getting file list via S3", e);
		}
	}

	public boolean resourceExists(String resourceName) throws TransferFailedException, AuthorizationException {
		Resource resource = new Resource(resourceName);

		try {
			S3Object s3Object = s3Service.getObjectDetails(bucket, resource.getName());

			return s3Object != null;
		}
		catch (S3ServiceException e) {
			return false;
		}
	}

	protected void closeConnection() throws ConnectionException {
	}

	protected void openConnectionInternal() throws ConnectionException, AuthenticationException {
	}

}

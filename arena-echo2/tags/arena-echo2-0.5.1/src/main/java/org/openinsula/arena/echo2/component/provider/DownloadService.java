/*
 * This file is part of the Echo File Transfer Library (hereinafter "EFTL").
 * Copyright (C) 2002-2005 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package org.openinsula.arena.echo2.component.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import nextapp.echo2.webcontainer.ContainerInstance;
import nextapp.echo2.webrender.Connection;
import nextapp.echo2.webrender.ContentType;
import nextapp.echo2.webrender.Service;
import nextapp.echo2.webrender.WebRenderServlet;

/**
 * A service that outputs a <code>Download</code> component's file.
 */
class DownloadService implements Service, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SERVICE_ID = "Echo.Download";

	private static final String PARAMETER_DOWNLOAD_UID = "downloaduid";

	private static final String[] URL_PARAMETERS = new String[] { PARAMETER_DOWNLOAD_UID };

	public static final DownloadService INSTANCE = new DownloadService();

	static {
		WebRenderServlet.getServiceRegistry().add(INSTANCE);
	}

	/**
	 * Creates a URI to execute a specific <code>DownloadProvider</code>
	 *
	 * @param containerInstance the relevant application container instance.
	 * @param downloadId the unique id to retrieve the download from the
	 * <code>ContainerInstance</code>
	 */
	public String createUri(ContainerInstance containerInstance, String downloadId) {
		return containerInstance.getServiceUri(this, URL_PARAMETERS, new String[] { downloadId });
	}

	/**
	 * @see nextapp.echo2.webrender.Service#getId()
	 */
	public String getId() {
		return SERVICE_ID;
	}

	/**
	 * @see nextapp.echo2.webrender.Service#getVersion()
	 */
	public int getVersion() {
		return DO_NOT_CACHE;
	}

	/**
	 * @see nextapp.echo2.webrender.Service#service(Connection)
	 */
	public void service(Connection conn) throws IOException {
		final ContainerInstance containerInstance = (ContainerInstance) conn.getUserInstance();
		if (containerInstance == null) {
			serviceBadRequest(conn, "No container available.");
			return;
		}
		final String downloadId = conn.getRequest().getParameter(PARAMETER_DOWNLOAD_UID);
		if (downloadId == null) {
			serviceBadRequest(conn, "Download UID not specified.");
			return;
		}
		final Download download = DownloadPeer.getDownload(downloadId);

		if (download == null) {
			serviceBadRequest(conn, "Download UID is not valid.");
			return;
		}
		service(conn, download);
	}

	public void service(Connection conn, Download download) throws IOException {
		final OutputStream out = conn.getOutputStream();
		final DownloadProvider provider = download.getProvider();
		final HttpServletResponse response = conn.getResponse();

		/**
		 * As 4 linhas abaixo foram adicionadas pelo Tuim, para arrumar problema
		 * de abrir arquivos pdf em IE.
		 */
		response.setHeader("Last-Modified", "'.gmdate('D, d M Y H:i:s') . ' GMT'");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.setHeader("Cache-Control", "pre-check=0, post-check=0, max-age=0");
		response.setHeader("Content-Transfer-Encoding", "none");

		if (provider.getFileName() == null) {
			response.setHeader("Content-Disposition", "attachment");
		}
		else {
			response.setHeader("Content-Disposition", "attachment; filename=\"" + provider.getFileName() + "\"");
		}
		if (provider.getSize() > 0) {
			response.setIntHeader("Content-Length", provider.getSize());
		}
		final String contentType = provider.getContentType();
		if (contentType == null) {
			response.setContentType("application/octet-stream");
		}
		else {
			response.setContentType(provider.getContentType());
		}
		provider.writeFile(out);
	}

	public void serviceBadRequest(Connection conn, String message) {
		conn.getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		conn.setContentType(ContentType.TEXT_PLAIN);
		conn.getWriter().write(message);
	}

}

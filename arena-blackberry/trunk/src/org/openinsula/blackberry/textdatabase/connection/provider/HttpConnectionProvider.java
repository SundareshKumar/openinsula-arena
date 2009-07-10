package org.openinsula.blackberry.textdatabase.connection.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.openinsula.blackberry.textdatabase.connection.Configuration;
import org.openinsula.blackberry.textdatabase.connection.ConnectionProvider;
import org.openinsula.blackberry.textdatabase.connection.configuration.HttpConfiguration;

public class HttpConnectionProvider implements ConnectionProvider {

	private HttpConfiguration configuration;
	
	private HttpConnection conn;

	private HttpConnection getConnection() throws IOException {
		if (conn == null) {
			conn = (HttpConnection) Connector.open(configuration.getUrl());
			conn.setRequestMethod(configuration.getHttpMethod());
		}
		return conn;
	}
	
	public InputStream getInputStream() throws Throwable {
		return getConnection().openInputStream();
	}

	public OutputStream getOutputStream() throws Throwable {
		return getConnection().openOutputStream();
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = (HttpConfiguration) configuration;
	}

	public void finish() {
		try {
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

}

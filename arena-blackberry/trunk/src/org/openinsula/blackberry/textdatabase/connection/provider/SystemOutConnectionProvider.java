package org.openinsula.blackberry.textdatabase.connection.provider;

import java.io.InputStream;
import java.io.OutputStream;

import org.openinsula.blackberry.textdatabase.connection.Configuration;
import org.openinsula.blackberry.textdatabase.connection.ConnectionProvider;

public class SystemOutConnectionProvider implements ConnectionProvider {

	public void finish() {

	}

	public InputStream getInputStream() throws Throwable {
		return null;
	}

	public OutputStream getOutputStream() throws Throwable {
		return System.out;
	}

	public void setConfiguration(Configuration configuration) {
	}

}

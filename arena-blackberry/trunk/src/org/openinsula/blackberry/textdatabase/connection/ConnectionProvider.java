package org.openinsula.blackberry.textdatabase.connection;

import java.io.InputStream;
import java.io.OutputStream;


public interface ConnectionProvider {

	void setConfiguration(Configuration configuration);

	OutputStream getOutputStream() throws Throwable;

	InputStream getInputStream() throws Throwable;

	void finish();
	
}

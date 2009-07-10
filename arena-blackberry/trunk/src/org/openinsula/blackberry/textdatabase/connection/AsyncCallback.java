package org.openinsula.blackberry.textdatabase.connection;

public interface AsyncCallback {

	void success(Object result);
	
	void failure(String message, Throwable t);
	
}

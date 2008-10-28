package org.openinsula.arena.gwt.http.client;

import com.google.gwt.http.client.Response;

/**
 * @author Eduardo Rebola
 */
public class ResponseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private Response response;
	
	public ResponseException(Response response) {
		super(response.getStatusText());
	}
	
	public Response getResponse() {
		return response;
	}

}

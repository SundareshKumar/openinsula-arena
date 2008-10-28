package org.openinsula.arena.gwt.client.rest;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public abstract class HttpRequestCallback implements RequestCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.http.client.RequestCallback#onResponseReceived(com.google
	 * .gwt.http.client.Request, com.google.gwt.http.client.Response)
	 */
	public final void onResponseReceived(Request request, Response response) {
		final int statusCode = response.getStatusCode();

		if (statusCode >= 200 && statusCode <= 299) {
			onSuccess(response);
		}
		else if (statusCode >= 400 && statusCode <= 599) {
			onError(request, new ResponseException(response));
		}
	}

	/**
	 * Callback method for response with status code between 200 and 299.
	 */
	protected abstract void onSuccess(Response response);

}

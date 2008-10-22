package org.openinsula.arena.gwt.client.rest;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * @author Lucas K Mogari
 */
public abstract class HttpRequestCallback implements RequestCallback, HttpResponseListener {

	private final List<HttpResponseListener> listeners = new LinkedList<HttpResponseListener>();

	public HttpRequestCallback() {
		addHttpResponseListener(this);
	}

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
			onResponseReturned(response);
		}
		else if (statusCode >= 400 && statusCode <= 599) {
			onResponseError(response);
		}
	}

	public void addHttpResponseListener(HttpResponseListener listener) {
		listeners.add(listener);
	}

	public void removeHttpResponseListener(HttpResponseListener listener) {
		listeners.remove(listener);
	}

}

package org.openinsula.arena.gwt.http.client;

import org.openinsula.arena.gwt.components.client.util.StringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * @author Eduardo Rebola
 */
public abstract class HTTPRequestCallback implements RequestCallback {
	private Request request;
	private Response response;
	private Throwable errorCause;

	public Request getRequest() {
		return request;
	}

	public Response getResponse() {
		return response;
	}

	public Throwable getErrorCause() {
		return errorCause;
	}

	private void setValues(final Request request, final Response response, final Throwable exception) {
		this.request = request;
		this.response = response;
		this.errorCause = exception;
	}

	public final void onError(final Request request, final Throwable exception) {
		setValues(request, null, exception);

		if (!GWT.isScript()) {
			GWT.log("Request error: ", exception);
		}

		onError();
	}

	public final void onResponseReceived(final Request request, final Response response) {
		setValues(request, response, null);

		final int statusCode = response.getStatusCode();

		if (!GWT.isScript()) {
			GWT.log("[" + statusCode + " - " + response.getStatusText() + "] " + response.getText(), null);
		}

		if (statusCode == 200 || statusCode == 201) {
			onSuccess();
		} else {
			onError();
		}
	}
	
	protected final boolean responseHasText() {
		if (response == null) {
			throw new IllegalStateException("Response not ready!");
		}
		String responseText = response.getText();
		
		return responseText != null 
		&& StringUtils.hasText(responseText) 
		&& !"null".equals(responseText);
	}

	public abstract void onSuccess();

	/**
	 * Default implementation is empty.
	 **/
	public void onError() {
	}

}

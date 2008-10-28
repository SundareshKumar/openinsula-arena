package org.openinsula.arena.gwt.client.rest;

import org.openinsula.arena.gwt.client.request.HttpMethod;
import org.openinsula.arena.gwt.client.request.RequestBuilder;
import org.openinsula.arena.gwt.client.request.RequestBuilderFactory;

import com.google.gwt.http.client.RequestCallback;

public abstract class AbstractRestServiceHandler implements RestServiceHandler {
	private RequestBuilderFactory requestBuilderFactory;

	public void setRequestBuilderFactory(RequestBuilderFactory requestBuilderFactory) {
		this.requestBuilderFactory = requestBuilderFactory;
	}

	public RequestBuilder handle(String url, HttpMethod method, Object data, RequestCallback callback) {
		RequestBuilder requestBuilder = requestBuilderFactory.createRequestBuilder();
		requestBuilder.setUrl(url);
		requestBuilder.setHttpMethod(method);
		requestBuilder.setCallback(callback);
		requestBuilder.setRequestData(getRequestData(data, callback));
		
		afterHandle(requestBuilder);
		
		return requestBuilder;
	}

	/**
	 * Override this if you need customization on <code>requestBuilder</code> (e.g add/remove header values)
	 */
	protected void afterHandle(final RequestBuilder requestBuilder) {
		
	}

	/**
	 * Override this!
	 * @param data may be <code>null</code>.
	 */
	protected String getRequestData(Object data, RequestCallback callback) {
		if (data != null) {
			return data.toString();
		}
		return null;
	}

}

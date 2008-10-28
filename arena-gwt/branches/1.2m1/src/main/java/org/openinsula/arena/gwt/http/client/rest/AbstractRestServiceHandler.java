package org.openinsula.arena.gwt.http.client.rest;

import org.openinsula.arena.gwt.http.client.HttpMethod;
import org.openinsula.arena.gwt.http.client.RequestBuilder;
import org.openinsula.arena.gwt.http.client.RequestBuilderFactory;

import com.google.gwt.http.client.RequestCallback;

public abstract class AbstractRestServiceHandler implements RestServiceHandler {
	private RequestBuilderFactory requestBuilderFactory;

	public AbstractRestServiceHandler() {
	}

	/**
	 * @param requestBuilderFactory injected by {@link DefaultRestService}
	 */
	void setRequestBuilderFactory(final RequestBuilderFactory requestBuilderFactory) {
		this.requestBuilderFactory = requestBuilderFactory;
	}

	public final RequestBuilder handle(final String url, final HttpMethod method, final Object data,
			final RequestCallback callback) {

		RequestBuilder requestBuilder = requestBuilderFactory.createRequestBuilder();
		requestBuilder.setUrl(url);
		requestBuilder.setHttpMethod(method);
		requestBuilder.setCallback(callback);
		requestBuilder.setRequestData(getRequestDataValue(data));

		afterHandle(requestBuilder);

		return requestBuilder;
	}

	/**
	 * Override this if you need customization on <code>requestBuilder</code>
	 * (e.g add/remove header values)
	 */
	protected void afterHandle(final RequestBuilder requestBuilder) {

	}

	/**
	 * Overwrite this for custom requestData serialization
	 */
	protected String getRequestDataValue(final Object data) {
		if (data != null) {
			return data.toString();
		}
		return null;
	}

}

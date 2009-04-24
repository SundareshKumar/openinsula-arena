package org.openinsula.arena.gwt.http.client;

import java.util.Map.Entry;

import com.google.gwt.http.client.RequestBuilder;

/**
 * @author Eduardo Rebola
 */
public class HTTPProxyRequestBuilderFilter implements EnhancedRequestBuilderFilter {

	private final String proxyUrl;

	public HTTPProxyRequestBuilderFilter(final String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}

	public void beforeBuildRequestBuilder(final EnhancedRequestBuilder requestBuilder) {
		final String requestData = requestBuilder.buildRequestData();
		requestBuilder.clearParameters();
		requestBuilder.body(null);

		requestBuilder.addParameter("_url", requestBuilder.url());
		requestBuilder.addParameter("_method", requestBuilder.httpMethod());
		requestBuilder.addParameter("_user", requestBuilder.user());
		requestBuilder.addParameter("_password", requestBuilder.password());
		requestBuilder.addParameter("_timeout", requestBuilder.timeoutMillis());
		requestBuilder.addParameter("_requestData", requestData);

		for (final Entry<String, String> header : requestBuilder.headerValues().entrySet()) {
			requestBuilder.addParameter("_header" + header.getKey(), header.getValue());
		}
	}

	public RequestBuilder overrideBuiltRequestBuilder(final EnhancedRequestBuilder requestBuilder,
			final RequestBuilder builtRequestBuilder) {

		final RequestBuilder proxy = new RequestBuilder(RequestBuilder.POST, proxyUrl);
		proxy.setHeader("Content-type", "application/x-www-form-urlencoded");
		proxy.setRequestData(requestBuilder.buildRequestData());
		proxy.setCallback(requestBuilder.requestCallback());

		return proxy;
	}

}

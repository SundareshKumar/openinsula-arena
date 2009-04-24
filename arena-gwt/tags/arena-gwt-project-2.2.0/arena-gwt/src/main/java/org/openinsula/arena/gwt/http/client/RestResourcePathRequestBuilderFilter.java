package org.openinsula.arena.gwt.http.client;

import com.google.gwt.http.client.RequestBuilder;

public class RestResourcePathRequestBuilderFilter implements EnhancedRequestBuilderFilter {

	private final String serverBaseUri;

	public RestResourcePathRequestBuilderFilter(final String serverBaseUri) {
		this.serverBaseUri = serverBaseUri;
	}

	public void beforeBuildRequestBuilder(final EnhancedRequestBuilder requestBuilder) {
		requestBuilder.url(serverBaseUri + requestBuilder.url());
	}

	public RequestBuilder overrideBuiltRequestBuilder(final EnhancedRequestBuilder requestBuilder,
			final RequestBuilder builtRequestBuilder) {

		return builtRequestBuilder;
	}

}

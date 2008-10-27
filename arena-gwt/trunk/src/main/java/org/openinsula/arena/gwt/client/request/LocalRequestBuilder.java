package org.openinsula.arena.gwt.client.request;

import java.util.Map.Entry;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.RequestBuilder.Method;

/**
 * @author Lucas K Mogari
 */
public class LocalRequestBuilder extends AbstractRequestBuilder {

	public Request send() {
		final RequestBuilder requestBuilder = createRequestBuilder();
		Request request = null;

		try {
			request = requestBuilder.send();
		}
		catch (final RequestException e) {
			e.printStackTrace();
		}
		return request;
	}

	private RequestBuilder createRequestBuilder() {
		final RequestBuilder requestBuilder = new RequestBuilder(getMethod(), buildUrl());

		for (final Entry<String, String> entry : getHeaders().entrySet()) {
			requestBuilder.setHeader(entry.getKey(), entry.getValue());
		}

		if (getUser() != null) {
			requestBuilder.setUser(getUser());
		}
		if (getPassword() != null) {
			requestBuilder.setPassword(getPassword());
		}

		requestBuilder.setTimeoutMillis(getTimeoutMillis());
		requestBuilder.setCallback(getCallback());
		requestBuilder.setRequestData(getRequestData());
		return requestBuilder;
	}

	private String buildUrl() {
		final StringBuilder sb = new StringBuilder();
		final HttpMethod httpMethod = getHttpMethod();

		sb.append(getUrl());

		switch (httpMethod) {
		case DELETE:
		case PUT:
			sb.append("?method=");
			sb.append(httpMethod.name());
			break;
		}

		return sb.toString();
	}

	private Method getMethod() {
		return getHttpMethod() == HttpMethod.GET ? RequestBuilder.GET : RequestBuilder.POST;
	}

}

package org.openinsula.arena.gwt.http.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractRequestBuilder implements RequestBuilder {

	private final Map<String, String> headers = new HashMap<String, String>();

	private RequestCallback callback;

	private String url;

	private HttpMethod httpMethod;

	private String requestData;

	private String user;

	private String password;

	private int timeoutMillis;

	public AbstractRequestBuilder() {
	}

	public AbstractRequestBuilder(final HttpMethod httpMethod, final String url) {
		this.httpMethod = httpMethod;
		this.url = url;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(final String requestData) {
		this.requestData = requestData;
	}

	public String getUser() {
		return user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public void setTimeoutMillis(final int timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(final HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHeaderValue(final String header) {
		return headers.get(header);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeader(final String header, final String value) {
		if (value == null || value.trim().length() == 0) {
			headers.remove(header);
		}
		else {
			headers.put(header, value);
		}
	}

	public RequestCallback getCallback() {
		return callback;
	}

	public void setCallback(final RequestCallback callback) {
		this.callback = callback;
	}

}

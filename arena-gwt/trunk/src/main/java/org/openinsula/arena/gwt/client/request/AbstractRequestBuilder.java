package org.openinsula.arena.gwt.client.request;

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

	public AbstractRequestBuilder(HttpMethod httpMethod, String url) {
		this.httpMethod = httpMethod;
		this.url = url;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public void setTimeoutMillis(int timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHeader(String header) {
		return headers.get(header);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeader(String header, String value) {
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

	public void setCallback(RequestCallback callback) {
		this.callback = callback;
	}

}

package org.openinsula.arena.gwt.http.client;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;

/**
 * @author Eduardo Rebola
 */
// TODO Test
public class EnhancedRequestBuilder {

	private EnhancedRequestBuilderFilter filter;

	private final Map<String, String> header;

	private String user;

	private String password;

	private int timeoutMillis;

	private final Map<String, Object> requestDataMap;

	private String requestData;

	private String url;

	private String httpMethod;

	private RequestCallback requestCallback;

	private final RequestDataBuilder requestDataBuilder = new RequestDataBuilder();

	private boolean requestDataDirty = true;

	private String requestDataCache;

	public EnhancedRequestBuilder() {
		header = new LinkedHashMap<String, String>();
		requestDataMap = new LinkedHashMap<String, Object>();
	}

	public void setFilter(final EnhancedRequestBuilderFilter filter) {
		this.filter = filter;
	}

	public EnhancedRequestBuilder json() {
		setHeader("Content-type", "application/json");
		return this;
	}
	
	public EnhancedRequestBuilder xml() {
		setHeader("Content-type", "application/xml");
		return this;
	} 
	
	public EnhancedRequestBuilder form() {
		setHeader("Content-type", "application/x-www-form-urlencoded");
		return this;
	}
	
	public EnhancedRequestBuilder setHeader(final String key, final String value) {
		header.put(key, value);
		return this;
	}

	public String getHeader(final String key) {
		return header.get(key);
	}

	public Map<String, String> headerValues() {
		return Collections.unmodifiableMap(header);
	}

	public EnhancedRequestBuilder user(final String value) {
		this.user = value;
		return this;
	}

	public String user() {
		return user;
	}

	public EnhancedRequestBuilder password(final String value) {
		this.password = value;
		return this;
	}

	public String password() {
		return password;
	}

	public EnhancedRequestBuilder timeoutMillis(final int value) {
		this.timeoutMillis = value;
		return this;
	}

	public int timeoutMillis() {
		return timeoutMillis;
	}

	public EnhancedRequestBuilder addParameter(final String key, final Object value) {
		if (value == null) {
			if (requestDataMap.remove(key) != null) {
				requestDataDirty = true;
			}
		}
		else {
			requestDataMap.put(key, value);
			requestDataDirty = true;
		}

		return this;
	}

	public EnhancedRequestBuilder clearParameters() {
		if (!requestDataMap.isEmpty()) {
			requestDataMap.clear();
			requestDataDirty = true;
		}
		return this;
	}

	public EnhancedRequestBuilder body(final Object requestData) {
		if (requestData != null) {
			this.requestData = requestData.toString();
		} else {
			this.requestData = null;
		}
		return this;
	}

	public Object getParameter(final String key) {
		return requestDataMap.get(key);
	}

	public Map<String, Object> parameterValues() {
		return Collections.unmodifiableMap(requestDataMap);
	}

	public EnhancedRequestBuilder url(final String url) {
		this.url = url;
		return this;
	}

	public String url() {
		return url;
	}

	public EnhancedRequestBuilder httpMethod(final String method) {
		this.httpMethod = method;
		return this;
	}

	public String httpMethod() {
		return httpMethod;
	}

	public EnhancedRequestBuilder requestCallback(final RequestCallback callback) {
		this.requestCallback = callback;
		return this;
	}

	public RequestCallback requestCallback() {
		return requestCallback;
	}

	public void send() throws IllegalArgumentException {
		if (filter != null) {
			filter.beforeBuildRequestBuilder(this);
		}

		RequestBuilder requestBuilder = buildRequestBuilder();

		if (filter != null) {
			requestBuilder = filter.overrideBuiltRequestBuilder(this, requestBuilder);
		}

		try {
			requestBuilder.send();
		}
		catch (RequestException e) {
			if (!GWT.isScript()) {
				GWT.log("HTTP request failed!", e);
			}
			throw new IllegalArgumentException(e);
		}
	}

	public void get() {
		httpMethod("GET").send();
	}

	public void get(final RequestCallback callback) {
		httpMethod("GET").requestCallback(callback).send();
	}

	public void post() {
		httpMethod("POST").send();
	}

	public void post(final RequestCallback callback) {
		httpMethod("POST").requestCallback(callback).send();
	}

	public void put() {
		httpMethod("PUT").send();
	}

	public void put(final RequestCallback callback) {
		httpMethod("PUT").requestCallback(callback).send();
	}

	public void delete() {
		httpMethod("DELETE").send();
	}

	public void delete(final RequestCallback callback) {
		httpMethod("DELETE").requestCallback(callback).send();
	}

	public String buildRequestData() {
		String result = requestData;

		if (requestData == null) {
			if (requestDataDirty) {
				requestDataCache = requestDataBuilder.buildRequestData(requestDataMap);
				requestDataDirty = false;
			}
			result = requestDataCache;
		}

//		if (!GWT.isScript()) {
//			GWT.log("requestData: " + result, null);
//		}

		return result;
	}

	private RequestBuilder buildRequestBuilder() {
		RequestBuilder requestBuilder = new RequestBuilder(httpMethod, url) {
		};

		requestBuilder.setCallback(requestCallback);

		if (user != null) {
			requestBuilder.setUser(user);
		}

		if (password != null) {
			requestBuilder.setPassword(password);
		}

		requestBuilder.setTimeoutMillis(timeoutMillis);

		for (Entry<String, String> entry : header.entrySet()) {
			requestBuilder.setHeader(entry.getKey(), entry.getValue());
		}

		requestBuilder.setRequestData(buildRequestData());

		return requestBuilder;
	}

}

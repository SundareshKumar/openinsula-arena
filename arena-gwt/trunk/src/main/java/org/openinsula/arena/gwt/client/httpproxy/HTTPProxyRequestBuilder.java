package org.openinsula.arena.gwt.client.httpproxy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class HTTPProxyRequestBuilder {
	private Map headers;

	private String password;

	private int timeoutMillis;

	private String user;

	StringBuffer postBuilder = new StringBuffer();

	public HTTPProxyRequestBuilder(RequestBuilder.Method httpMethod, String url) {
		this((httpMethod == null) ? null : httpMethod.toString(), url);
	}

	public HTTPProxyRequestBuilder(String httpMethod, String url) {
		// build
		postBuilder.append("method=");
		postBuilder.append(httpMethod);
		setParam("url", url);
	}

	protected void setParam(String key, String value) {
		postBuilder.append("&");
		postBuilder.append(key);
		postBuilder.append("=");
		postBuilder.append(URL.encodeComponent(value));
	}

	public Request sendRequest(String requestData, RequestCallback callback) throws RequestException {
		if (user != null)
			setParam("user", user);
		if (password != null)
			setParam("password", password);
		if (timeoutMillis > 0)
			setParam("timeout", Integer.toString(timeoutMillis));
		if (headers != null) {
			Set entrySet = headers.entrySet();
			for (Iterator iter = entrySet.iterator(); iter.hasNext();) {
				Map.Entry header = (Map.Entry) iter.next();
				setParam((String) header.getKey(), (String) header.getValue());
			}
		}
		if (requestData != null)
			setParam("post", requestData);
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, GWT.getModuleBaseURL() + "/HTTPProxy");
		requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");
		return requestBuilder.sendRequest(postBuilder.toString(), callback);
	}

	public void setHeader(String header, String value) {
		if (headers == null) {
			headers = new HashMap();
		}
		headers.put(header, value);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTimeoutMillis(int timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

	public void setUser(String user) {
		this.user = user;
	}
}

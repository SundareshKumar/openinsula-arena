package org.openinsula.arena.gwt.client.httpproxy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class HTTPProxyRequestBuilder {

	private String user;

	private String password;

	private int timeoutMillis;

	private Map<String, String> query = new HashMap<String, String>();

	private Map<String, String> params = new HashMap<String, String>();

	public HTTPProxyRequestBuilder() {
	}

	public HTTPProxyRequestBuilder(String url) {
		setUrl(url);
	}

	public HTTPProxyRequestBuilder(RequestBuilder.Method httpMethod, String url) {
		this((httpMethod == null) ? null : httpMethod.toString(), url);
	}

	public HTTPProxyRequestBuilder(String httpMethod, String url) {
		this(url);
		putParameter("method", httpMethod);
	}

	public void setUrl(String url) {
		putParameter("url", url);
	}

	public String getUrl() {
		return params.get("url");
	}

	/**
	 * Sends a request with the GET method
	 * @param callback
	 * @return
	 * @throws RequestException
	 */
	public Request sendGet(RequestCallback callback) throws RequestException {
		setRequestMethod("GET");
		return sendRequest(null, callback);
	}

	/**
	 * Sends a request with the POST method
	 * @param requestData
	 * @param callback
	 * @return
	 * @throws RequestException
	 */
	public Request sendPost(String requestData, RequestCallback callback) throws RequestException {
		setRequestMethod("POST");
		return sendRequest(requestData, callback);
	}

	/**
	 * Sends a request with the PUT method
	 * @param requestData
	 * @param callback
	 * @return
	 * @throws RequestException
	 */
	public Request sendPut(String requestData, RequestCallback callback) throws RequestException {
		setRequestMethod("PUT");
		return sendRequest(requestData, callback);
	}

	/**
	 * Sends a request with the DELETE method
	 * @param requestData
	 * @param callback
	 * @return
	 * @throws RequestException
	 */
	public Request sendDelete(RequestCallback callback) throws RequestException {
		setRequestMethod("DELETE");
		return sendRequest(null, callback);
	}

	/**
	 * Puts the requestMethod in the parameters list.
	 * @param requestMethod
	 */
	public void setRequestMethod(String requestMethod) {
		putParameter("method", requestMethod);
	}

	/**
	 * Put a parameter in the params map.
	 * @param key
	 * @param value
	 */
	protected void putParameter(String key, String value) {
		params.put(key, value);
	}

	public void putAllQueries(Map<String, String> queryParameters) {
		this.query.putAll(queryParameters);
	}

	public void putQuery(String key, String value) {
		query.put(key, value);
	}

	public void removeQuery(String key) {
		query.remove(key);
	}

	/**
	 * @return The list of params in String
	 */
	public String buildParametersAsString() {
		StringBuffer postBuilder = new StringBuffer("");
		postBuilder.append('?');
		{
			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();

				if (entry.getValue() == null) {
					continue;
				}

				postBuilder.append(entry.getKey());
				postBuilder.append("=");
				postBuilder.append(URL.encodeComponent(entry.getValue()));

				if (iterator.hasNext()) {
					postBuilder.append("&");
				}
			}
		}
		{
			Iterator<Entry<String, String>> iteratorQuery = query.entrySet().iterator();

			if (iteratorQuery.hasNext()) {
				postBuilder.append("&");
			}

			while (iteratorQuery.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iteratorQuery.next();

				if (entry.getValue() == null) {
					continue;
				}

				postBuilder.append("query");
				postBuilder.append(entry.getKey());
				postBuilder.append("=");
				postBuilder.append(URL.encodeComponent(entry.getValue()));

				if (iteratorQuery.hasNext()) {
					postBuilder.append("&");
				}
			}

		}

		return postBuilder.toString();
	}

	public Request sendRequest(String requestData, RequestCallback callback) throws RequestException {

		putParameter("user", user);
		putParameter("password", password);
		putParameter("post", requestData);

		if (timeoutMillis > 0) {
			putParameter("timeout", Integer.toString(timeoutMillis));
		}

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, GWT.getModuleBaseURL() + "/HTTPProxy");
		requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");

		GWT.log(buildParametersAsString(), null);

		return requestBuilder.sendRequest(buildParametersAsString(), callback);
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

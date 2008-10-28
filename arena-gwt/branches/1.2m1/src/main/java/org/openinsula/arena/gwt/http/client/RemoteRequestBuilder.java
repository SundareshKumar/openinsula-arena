package org.openinsula.arena.gwt.http.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

/**
 * @author Lucas K Mogari
 */
public class RemoteRequestBuilder extends AbstractRequestBuilder {

	private static final String DEFAULT_REQUEST_DISPATCHER_SERVLET_NAME = "RequestDispatcherServlet";

	private String requestDispatcherServletName = DEFAULT_REQUEST_DISPATCHER_SERVLET_NAME;

	public RemoteRequestBuilder() {
	}

	public RemoteRequestBuilder(String requestDispatcherServletName) {
		this.requestDispatcherServletName = requestDispatcherServletName;
	}

	public Request send() {
		final StringBuilder sb = new StringBuilder();
		Request request = null;

		sb.append(GWT.getModuleBaseURL());
		sb.append(requestDispatcherServletName);

		final RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, sb.toString());
		final String requestData = buildRequestData();

		requestBuilder.setCallback(getCallback());
		requestBuilder.setRequestData(requestData);
		requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");

		try {
			request = requestBuilder.send();
		}
		catch (final RequestException e) {
			e.printStackTrace();
		}
		return request;
	}

	String buildRequestData() {
		final StringBuilder sb = new StringBuilder();
		final Map<String, String> params = new HashMap<String, String>();

		params.put("user", getUser());
		params.put("password", getPassword());
		params.put("requestData", getRequestData());
		params.put("method", getHttpMethod().name());
		params.put("url", getUrl());

		for (final Entry<String, String> entry : getHeaders().entrySet()) {
			params.put(entry.getKey(), entry.getValue());
		}

		if (getTimeoutMillis() > 0) {
			params.put("timeout", Integer.toString(getTimeoutMillis()));
		}

		addParamsToQueryString(params, sb, false);

		return sb.toString();
	}

	private void addParamsToQueryString(Map<String, String> map, StringBuilder sb, boolean query) {
		final Iterator<Entry<String, String>> iteratorQuery = map.entrySet().iterator();

		while (iteratorQuery.hasNext()) {
			final Map.Entry<String, String> entry = iteratorQuery.next();

			if (entry.getValue() != null) {
				if (query) {
					sb.append("query");
				}
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(URL.encodeComponent(entry.getValue()));

				if (iteratorQuery.hasNext()) {
					sb.append("&");
				}
			}
		}
	}

	public String getRequestDispatcherServletName() {
		return requestDispatcherServletName;
	}

	public void setRequestDispatcherServletName(String requestDispatcherServletName) {
		this.requestDispatcherServletName = requestDispatcherServletName;
	}

}

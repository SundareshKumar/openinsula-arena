package org.openinsula.arena.gwt.http.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;

/**
 * Wrapper interface for {@link com.google.gwt.http.client.RequestBuilder}.
 * 
 * @author Lucas K Mogari
 */
public interface RequestBuilder {

	public void setHeader(String header, String value);

	public void setUser(String user);

	public void setPassword(String password);

	public void setTimeoutMillis(int timeoutMillis);

	public void setRequestData(String requestData);

	public void setCallback(RequestCallback callback);

	public void setUrl(String url);

	public void setHttpMethod(HttpMethod httpMethod);

	public Request send();

}

package org.openinsula.arena.gwt.client.rest;

import org.openinsula.arena.gwt.client.request.HttpMethod;
import org.openinsula.arena.gwt.client.request.RequestBuilder;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Eduardo Rebola
 *
 */
public interface RestServiceHandler {
	
	boolean matches(String url, RequestCallback callback);
	
	RequestBuilder handle(String url, HttpMethod method, Object data, RequestCallback callback);

}

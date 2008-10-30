package org.openinsula.arena.gwt.http.client.rest;

import org.openinsula.arena.gwt.http.client.HttpMethod;
import org.openinsula.arena.gwt.http.client.RequestBuilder;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Eduardo Rebola
 */
public interface RestServiceHandler {

	boolean matches(String url, Object data);

	RequestBuilder handle(String url, HttpMethod method, Object data, RequestCallback callback);

}

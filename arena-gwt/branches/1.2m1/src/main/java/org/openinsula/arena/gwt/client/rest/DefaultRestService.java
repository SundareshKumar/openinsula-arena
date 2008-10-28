package org.openinsula.arena.gwt.client.rest;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.request.HttpMethod;
import org.openinsula.arena.gwt.client.request.RequestBuilder;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Eduardo Rebola
 */
public class DefaultRestService implements RestService {
	private final List<RestServiceHandler> handlerList = new ArrayList<RestServiceHandler>();
	
	public void create(String url, Object data, RequestCallback callback) {
		send(url, HttpMethod.POST, callback, data);
	}

	public void delete(String url, RequestCallback callback) {
		send(url, HttpMethod.DELETE, callback, null);		
	}

	public void read(String url, RequestCallback callback) {
		send(url, HttpMethod.GET, callback, null);
	}

	public void update(String url, Object data, RequestCallback callback) {
		send(url, HttpMethod.PUT, callback, data);
	}
	
	private void send(String url, HttpMethod method, RequestCallback callback, Object data) {
		RestServiceHandler handler = getFirstMatchDelegateFor(url, callback);
		RequestBuilder requestBuilder = handler.handle(url, method, data, callback);
		requestBuilder.send();
	}
	
	private RestServiceHandler getFirstMatchDelegateFor(String url, RequestCallback callback) {
		for (RestServiceHandler handler : handlerList) {
			if (handler.matches(url, callback)) {
				return handler;
			}
		}
		throw new IllegalStateException("No service delegate found for url '" + url + "'");
	}
	
	public void addHandler(RestServiceHandler handler) {
		handlerList.add(handler);
	}
	
	public void removeHandler(RestServiceHandler handler) {
		handlerList.remove(handler);
	}

}

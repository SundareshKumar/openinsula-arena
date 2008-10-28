package org.openinsula.arena.gwt.http.client.rest;

import java.util.LinkedList;

import org.openinsula.arena.gwt.http.client.HttpMethod;
import org.openinsula.arena.gwt.http.client.RequestBuilder;
import org.openinsula.arena.gwt.http.client.RequestBuilderFactory;
import org.openinsula.arena.gwt.http.client.json.JSONRestServiceHandler;
import org.openinsula.arena.gwt.http.client.xml.XmlRestServiceHandler;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Eduardo Rebola
 */
public class DefaultRestService implements RestService {
	private final LinkedList<RestServiceHandler> handlerList = new LinkedList<RestServiceHandler>();
	
	public DefaultRestService() {
		this(null);
	}
	
	public DefaultRestService(final RequestBuilderFactory requestBuilderFactory) {
		addHandler(new DefaultRestServiceHandler());
		addHandler(new JSONRestServiceHandler());
		addHandler(new XmlRestServiceHandler());
		
		setRequestBuilderFactory(requestBuilderFactory);
	}
	
	public void setRequestBuilderFactory(final RequestBuilderFactory requestBuilderFactory) {
		for (RestServiceHandler handler : handlerList) {
			if (handler instanceof AbstractRestServiceHandler) {
				((AbstractRestServiceHandler) handler).setRequestBuilderFactory(requestBuilderFactory);
			}
		}
	}
	
	public void create(final String url, final Object data, final RequestCallback callback) {
		send(url, HttpMethod.POST, callback, data);
	}

	public void delete(final String url, final RequestCallback callback) {
		send(url, HttpMethod.DELETE, callback, null);		
	}

	public void read(final String url, final RequestCallback callback) {
		send(url, HttpMethod.GET, callback, null);
	}

	public void update(final String url, final Object data, final RequestCallback callback) {
		send(url, HttpMethod.PUT, callback, data);
	}
	
	private void send(final String url, final HttpMethod method, final RequestCallback callback, final Object data) {
		RestServiceHandler handler = getFirstMatchDelegateFor(url, data);
		RequestBuilder requestBuilder = handler.handle(url, method, data, callback);
		requestBuilder.send();
	}
	
	private RestServiceHandler getFirstMatchDelegateFor(final String url, final Object data) {
		for (RestServiceHandler handler : handlerList) {
			if (handler.matches(url, data)) {
				return handler;
			}
		}
		throw new IllegalStateException("No service delegate found for url '" + url + "'");
	}
	
	public void addHandler(final RestServiceHandler handler) {
		handlerList.addFirst(handler);
	}
	
	public void removeHandler(final RestServiceHandler handler) {
		handlerList.remove(handler);
	}
	
	private final class DefaultRestServiceHandler extends AbstractRestServiceHandler {
		
		public boolean matches(final String url, final Object data) {
			return true;
		}
		
	}

}

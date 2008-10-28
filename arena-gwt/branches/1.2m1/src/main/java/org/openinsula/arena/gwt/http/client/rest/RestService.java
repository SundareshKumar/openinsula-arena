package org.openinsula.arena.gwt.http.client.rest;

import com.google.gwt.http.client.RequestCallback;

/**
 * @author Lucas K Mogari
 */
public interface RestService {
	
	public void create(String url, Object data, RequestCallback callback);

	public void delete(String url, RequestCallback callback);

	public void read(String url, RequestCallback callback);

	public void update(String url, Object data, RequestCallback callback);

}

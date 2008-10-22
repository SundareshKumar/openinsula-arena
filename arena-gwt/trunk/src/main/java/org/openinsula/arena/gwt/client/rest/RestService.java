package org.openinsula.arena.gwt.client.rest;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;

/**
 * @author Lucas K Mogari
 */
public interface RestService {

	public Request create(String url, String createData, RequestCallback callback);

	public Request delete(String url, RequestCallback callback);

	public Request read(String url, RequestCallback callback);

	public Request update(String url, String updateData, RequestCallback callback);

}

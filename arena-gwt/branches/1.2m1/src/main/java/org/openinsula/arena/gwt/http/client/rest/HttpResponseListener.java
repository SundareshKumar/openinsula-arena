package org.openinsula.arena.gwt.http.client.rest;

import com.google.gwt.http.client.Response;

/**
 * @author Lucas K Mogari
 */
public interface HttpResponseListener {

	public void onResponseReturned(Response response);

	/**
	 * Called when response code is between 400 and 599.
	 */
	public void onResponseError(Response response);

}

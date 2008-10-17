package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.Service;

import com.google.gwt.http.client.Request;

/**
 * @author Lucas K Mogari
 */
public interface AtomService extends Service {

	public <T extends Entry> Request getEntry(String url, EntryRequestCallback<T> callback);

	public <T extends Entry> Request getFeed(String url, FeedRequestCallback<T> callback);

}

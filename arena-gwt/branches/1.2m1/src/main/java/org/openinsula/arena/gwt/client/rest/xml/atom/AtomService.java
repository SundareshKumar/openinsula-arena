package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.RestService;

import com.google.gwt.http.client.Request;

/**
 * @author Lucas K Mogari
 */
public interface AtomService extends RestService {

	public <T extends BaseEntry<T>> Request createEntry(String url, T entry, EntryRequestCallback<T> callback);

	public <T extends BaseEntry<T>> Request updateEntry(String url, T entry, EntryRequestCallback<T> callback);

	public <T extends BaseEntry<T>> Request getEntry(String url, EntryRequestCallback<T> callback);

	public <T extends BaseEntry<T>> Request getFeed(String url, FeedRequestCallback<T> callback);

}

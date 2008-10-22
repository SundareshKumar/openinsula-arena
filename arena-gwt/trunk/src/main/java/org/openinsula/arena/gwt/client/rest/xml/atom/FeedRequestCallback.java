package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 */
public abstract class FeedRequestCallback<T extends Entry> extends XmlRequestCallback {

	private EntryFactory<T> entryFactory;

	public FeedRequestCallback() {
	}

	public FeedRequestCallback(EntryFactory<T> entryFactory) {
		this.entryFactory = entryFactory;
	}

	protected abstract void onFeedParsed(Feed<T> feed);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#onXmlParsed
	 * (com.google.gwt.xml.client.Document)
	 */
	@Override
	protected final void onXmlParsed(Document document) {
		final Feed<T> feed = new Feed<T>();
		feed.parse(document);
		feed.setEntryFactory(entryFactory);

		onFeedParsed(feed);
	}

}

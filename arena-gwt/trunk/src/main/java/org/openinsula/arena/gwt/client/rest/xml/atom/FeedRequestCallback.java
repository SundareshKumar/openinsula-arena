package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 */
public class FeedRequestCallback<T extends Entry> extends XmlRequestCallback {

	private BaseFeed<T> feed;

	public FeedRequestCallback() {
	}

	public FeedRequestCallback(BaseFeed<T> feed) {
		this.feed = feed;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onXmlParsed(Document document) {
		if (feed == null) {
			feed = (BaseFeed<T>) new Feed();
		}

		feed.parse(document);

		onFeedParsed(feed);
	}

	protected void onFeedParsed(BaseFeed<T> feed) {
	}

}

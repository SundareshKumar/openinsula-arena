package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.http.client.xml.XmlRequestCallback;
import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;
import org.openinsula.arena.gwt.xml.client.NodeTextAppender;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class FeedRequestCallback<T extends BaseEntry<T>> extends XmlRequestCallback implements EntryFactory<T> {

	protected abstract void onFeedParsed(Feed<T> feed);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#doWithXml
	 * (com.google.gwt.xml.client.Document)
	 */
	@Override
	protected void doWithXml(Document document) {
		final FeedDocumentParser feedParser = new FeedDocumentParser();
		final Feed<T> feed = feedParser.parse(document);

		onFeedParsed(feed);
	}

	private final class FeedDocumentParser extends AtomResourceNodeParser<Feed<T>> {

		public FeedDocumentParser() {
			super(new Feed<T>());
		}

		@Override
		protected void initParsers() {
			super.initParsers();

			add("subtitle", new AtomTextNodeAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, Text text) {
					feed.setSubtitle(text);
				}
			});
			add("icon", new NodeTextAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, String icon) {
					feed.setIcon(icon);
				}
			});
			add("logo", new NodeTextAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, String logo) {
					feed.setLogo(logo);
				}
			});
			add("entry", new AbstractParsedNodeResultAppender<Feed<T>, T>() {
				@Override
				public void appendResultTo(Feed<T> feed, T entry) {
					feed.addEntry(entry);
				}

				@SuppressWarnings("unchecked")
				@Override
				public T parseNode(Node node) {
					T entry = createEntry();

					if (entry == null) {
						entry = (T) new Entry();
					}

					return entry.parse(node);
				}
			});
		}

	}

}

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
	protected void doWithXml(final Document document) {
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

			addNodeParser("subtitle", new AtomTextNodeAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, Text text) {
					feed.setSubtitle(text);
				}
			});
			addNodeParser("icon", new NodeTextAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, String icon) {
					feed.setIcon(icon);
				}
			});
			addNodeParser("logo", new NodeTextAppender<Feed<T>>() {
				@Override
				public void appendResultTo(Feed<T> feed, String logo) {
					feed.setLogo(logo);
				}
			});
			addNodeParser("entry", new AbstractParsedNodeResultAppender<Feed<T>, T>() {
				@Override
				public void appendResultTo(Feed<T> feed, T entry) {
					feed.addEntry(entry);
				}

				@Override
				public T parseNode(Node node) {
					final T entry = createEntry();

					return entry.parse(node);
				}
			});
		}

	}

}

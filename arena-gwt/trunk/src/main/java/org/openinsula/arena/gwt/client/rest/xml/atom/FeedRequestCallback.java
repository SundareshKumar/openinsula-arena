package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.ValueNodeParser;

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
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#onXmlParsed
	 * (com.google.gwt.xml.client.Document)
	 */
	@Override
	protected final void onXmlParsed(Document document) {
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

			addNodeParser("subtitle", new TextNodeParser() {
				public void onNodeParsed(Text text) {
					getAtomResource().setSubtitle(text);
				}
			});
			addNodeParser("icon", new ValueNodeParser() {
				public void onNodeParsed(String value) {
					getAtomResource().setIcon(value);
				}
			});
			addNodeParser("logo", new ValueNodeParser() {
				public void onNodeParsed(String value) {
					getAtomResource().setLogo(value);
				}
			});
			addNodeParser("entry", new NodeParser<T>() {
				public T parse(Node node) {
					final T entry = createEntry();

					getAtomResource().addEntry(entry);

					return entry.parse(node);
				}
			});
		}

	}

}

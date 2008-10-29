package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;
import org.openinsula.arena.gwt.client.xml.parse.NodeParser;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class FeedRequestCallback<T extends BaseEntry<T>> extends XmlRequestCallback implements EntryFactory<T> {

	protected abstract void doWithFeed(Feed<T> feed);

	/* (non-Javadoc)
	 * @see org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#doWithXml(com.google.gwt.xml.client.Document)
	 */
	@Override
	protected void doWithXml(final Document document) {
		final FeedDocumentParser feedParser = new FeedDocumentParser();
		final Feed<T> feed = feedParser.parse(document);

		doWithFeed(feed);
	}

	private final class FeedDocumentParser extends AtomResourceNodeParser<Feed<T>> {

		public FeedDocumentParser() {
			super(new Feed<T>());
		}

		@Override
		protected void initParsers() {
			super.initParsers();

			addNodeParser("subtitle", new TextNodeParser<Feed<T>>() {
				public void onNodeParsed(final Text text) {
					getAtomResource().setSubtitle(text);
				}
			});
			addNodeParser("icon", new ValueNodeParser() {
				public void onNodeParsed(final String value) {
					getAtomResource().setIcon(value);
				}
			});
			addNodeParser("logo", new ValueNodeParser() {
				public void onNodeParsed(final String value) {
					getAtomResource().setLogo(value);
				}
			});
			addNodeParser("entry", new NodeParser<T>() {
				public T parse(final Node node) {
					final T entry = createEntry();

					getAtomResource().addEntry(entry);

					return entry.parse(node);
				}
			});
		}

	}

}

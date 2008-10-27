package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class FeedRequestCallback<T extends Entry> extends XmlRequestCallback {

	private final EntryFactory<T> entryFactory;

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
		final FeedParser feedParser = new FeedParser();

		feedParser.parse(document);

		onFeedParsed(feedParser.getAtomResource());
	}

	private final class FeedParser extends AtomResourceParser<Feed<T>> {

		private FeedParser() {
			super(new Feed<T>());

			addParser("subtitle", new TextNodeParser() {
				public void onNodeParsed(Text text) {
					getAtomResource().setSubtitle(text);
				}
			});
			addParser("icon", new IconNodeParser());
			addParser("logo", new LogoNodeParser());
			addParser("entry", new EntryNodeParser());
		}

		private final class SubtitleNodeParser extends TextNodeParser {

			@Override
			public T parse(Node node) {
				super.parse(node);

				getAtomResource().setSubtitle(getText());
			}

		}

		private final class IconNodeParser implements NodeParser {

			public T parse(Node node) {
				getAtomResource().setIcon(XmlParserUtils.getText(node));
			}

		}

		private final class LogoNodeParser implements NodeParser {

			public T parse(Node node) {
				getAtomResource().setLogo(XmlParserUtils.getText(node));
			}

		}

		private final class EntryNodeParser implements NodeParser {

			@SuppressWarnings("unchecked")
			public T parse(Node node) {
				T entry = entryFactory.createEntry();

				if (entry == null) {
					entry = (T) new Entry();
				}

				// entry.parse(node);

				getAtomResource().addEntry(entry);
			}

		}

	}

}

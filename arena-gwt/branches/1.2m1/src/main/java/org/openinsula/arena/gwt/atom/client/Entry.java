package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;
import org.openinsula.arena.gwt.xml.client.SingleNodeFactory;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class Entry extends PublishingEntry<Entry> {

	public Entry() {
	}

	public Entry(String title, String id) {
		super(title, id);

		addNodeFactory(new ContentNodeFactory());
		addNodeParser("content", new ContentAppender());
	}

	private final class ContentAppender extends AbstractParsedNodeResultAppender<Entry, Content> {

		@Override
		public void appendResultTo(Entry entry, Content content) {
			entry.setContent(content);
		}

		@Override
		public Content parseNode(Node node) {
			final Content content = new Content();

			content.setValue(getNodeText());
			content.setSrc(getNodeAttribute("src"));
			content.setType(getNodeAttribute("type"));

			return content;
		}

	}

	private final class ContentNodeFactory extends SingleNodeFactory {

		@Override
		public Node createNode() {
			final Content content = getContent();

			if (content != null) {
				final Element element = createTextElement("content", content.getValue());

				final String type = content.getType();
				if (type != null) {
					element.setAttribute("type", type);
				}

				final String src = content.getSrc();
				if (src != null) {
					element.setAttribute("src", src);
				}

				return element;
			}

			return null;
		}

	}

}

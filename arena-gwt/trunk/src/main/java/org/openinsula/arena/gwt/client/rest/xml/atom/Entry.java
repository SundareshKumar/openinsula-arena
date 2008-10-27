package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.AwareNodeParser;
import org.openinsula.arena.gwt.client.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class Entry extends BaseEntry<Entry> {

	public Entry() {
	}

	public Entry(String title, String id) {
		super(title, id);

		addNodeParser("content", new ContentNodeParser() {
			public void onNodeParsed(Content content) {
				setContent(content);
			}
		});
		addNodeFactory(new ContentNodeFactory());
	}

	private abstract class ContentNodeParser implements AwareNodeParser<Content> {

		public Content parse(Node node) {
			final String value = XmlParserUtils.getText(node);

			if (value == null) {
				throw new NullPointerException("");
			}

			final Content content = new Content();

			content.setValue(value);
			content.setType(XmlParserUtils.getAttribute(node, "type"));
			content.setSrc(XmlParserUtils.getAttribute(node, "src"));
			return content;
		}

	}

	private final class ContentNodeFactory extends CompositeNodeFactory {

		@Override
		protected Node createBaseNode() {
			Element contentElement = null;
			final Content content = getContent();

			if (content == null) {
				contentElement = createElement("content");
			}
			else {
				contentElement = createTextElement("content", content);
				final String src = content.getSrc();

				if (src != null && src.trim().length() > 0) {
					contentElement.setAttribute("src", src);
				}
			}
			return contentElement;
		}

	}

}

package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.Date;

import org.openinsula.arena.gwt.client.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 */
public class Entry extends AtomResource {

	private Text summary;

	private Content content;

	private Date published;

	private CompositeNodeFactory entryNodeFactory;

	private final CompositeNodeFactory contentNodeFactory = new ContentNodeFactory();

	private final CompositeNodeParser contentNodeParser = new ContentNodeParser();

	public Entry() {
	}

	public Entry(String title, String id) {
		super(id, title);
	}

	// {
	// final CompositeNodeParser rootNodeParser = getRootNodeParser();
	//
	// rootNodeParser.addParser("summary", new SummaryNodeParser());
	// rootNodeParser.addParser("published", new PublishedNodeParser());
	// rootNodeParser.addParser("content", contentNodeParser);
	// }

	public final void parseContent(Node node) {
		contentNodeParser.parse(node);
	}

	public final CompositeNodeFactory getContentNodeFactory() {
		return contentNodeFactory;
	}

	public final CompositeNodeParser getContentNodeParser() {
		return contentNodeParser;
	}

	public CompositeNodeFactory getEntryNodeFactory() {
		return entryNodeFactory;
	}

	public void setEntryNodeFactory(CompositeNodeFactory entryNodeFactory) {
		this.entryNodeFactory = entryNodeFactory;
	}

	public String toXml() {
		final Document document = XMLParser.createDocument();
		final Node entryNode = createNode(document);

		if (entryNode != null) {
			document.appendChild(entryNode);
		}

		return document.toString();
	}

	public Node createNode(Document document) {
		if (entryNodeFactory == null) {
			entryNodeFactory = new EntryNodeFactory();
		}
		entryNodeFactory.setDocument(document);

		return entryNodeFactory.createNode();
	}

	public Text getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		setSummary(new Text(summary));
	}

	public void setSummary(Text summary) {
		this.summary = summary;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	private final class SummaryNodeParser extends TextNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			summary = getText();
		}

	}

	private final class ContentNodeParser extends CompositeNodeParser {

		@Override
		public final T parse(Node node) {
			if (hasParsers()) {
				super.parse(node);
			}
			else {
				final String value = XmlParserUtils.getText(node);

				if (value == null) {
					throw new NullPointerException("");
				}

				if (content == null) {
					content = new Content();
				}

				content.setValue(value);
				content.setType(XmlParserUtils.getAttribute(node, "type"));
				content.setSrc(XmlParserUtils.getAttribute(node, "src"));
			}
		}

	}

	private final class PublishedNodeParser implements NodeParser {

		public T parse(Node node) {
			published = XmlParserUtils.getDate(node);
		}

	}

	private final class EntryNodeFactory extends CompositeNodeFactory {

		public EntryNodeFactory() {
			addNodeFactory(new CategoriesNodesFactory(getCategories()));
			addNodeFactory(new PeopleNodesFactory("author", getAuthors()));
			addNodeFactory(new PeopleNodesFactory("contributor", getContributors()));
			addNodeFactory(new LinksNodesFactory(getLinks()));
			addNodeFactory(new ContentNodeFactory());
		}

		@Override
		protected Node createBaseNode() {
			final Element entryElement = createElement("entry");
			final Text title = getTitle();
			final Text summary = getSummary();

			entryElement.setAttribute("xmlns", "http://www.w3.org/2005/Atom");

			if (title != null) {
				entryElement.appendChild(createTextElement("title", title));
			}
			if (summary != null) {
				entryElement.appendChild(createTextElement("summary", summary));
			}
			return entryElement;
		}

	}

	private final class ContentNodeFactory extends CompositeNodeFactory {

		@Override
		protected Node createBaseNode() {
			Element contentElement = null;

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

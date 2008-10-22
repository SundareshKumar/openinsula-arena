package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.Date;

import org.openinsula.arena.gwt.client.rest.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.rest.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.rest.xml.NodeParser;
import org.openinsula.arena.gwt.client.rest.xml.SingleNodeFactory;
import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 */
public class Entry extends AtomResource implements SingleNodeFactory {

	private Text summary;

	private Content content;

	private Date published;

	private Document document;

	private EntryNodeFactory entryNodeFactory;

	private final CompositeNodeFactory contentNodeFactory = new ContentNodeFactory();

	private final CompositeNodeParser contentNodeParser = new ContentNodeParser();

	public Entry() {
	}

	public Entry(String title, String id) {
		super(id, title);
	}

	{
		addParser("summary", new SummaryNodeParser());
		addParser("published", new PublishedNodeParser());
		addParser("content", contentNodeParser);
	}

	public void parseContent(Node node) {
		contentNodeParser.parse(node);
	}

	public final CompositeNodeFactory getContentNodeFactory() {
		return contentNodeFactory;
	}

	public final CompositeNodeParser getContentNodeParser() {
		return contentNodeParser;
	}

	public String toXml() {
		document = XMLParser.createDocument();
		final Node entryNode = createNode();

		if (entryNode != null) {
			document.appendChild(entryNode);
		}

		return document.toString();
	}

	public Node createNode() {
		if (entryNodeFactory == null) {
			entryNodeFactory = new EntryNodeFactory();
		}
		entryNodeFactory.setDocument(document);

		return entryNodeFactory.createNode();
	}

	public void setDocument(Document document) {
		this.document = document;
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
		public void parse(Node node) {
			super.parse(node);

			summary = getText();
		}

	}

	private final class ContentNodeParser extends CompositeNodeParser {

		@Override
		public final void parse(Node node) {
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

		public void parse(Node node) {
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

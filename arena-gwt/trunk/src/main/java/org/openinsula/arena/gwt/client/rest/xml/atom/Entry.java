package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.Date;

import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.http.client.Request;
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

	private AtomService service;

	private Document document;

	private CompositeNodeParser contentNodeParser;

	public Entry() {
	}

	public Entry(String title, String id) {
		super(id, title);
	}

	@Override
	protected void initNodeParsers() {
		super.initNodeParsers();

		contentNodeParser = new ContentNodeParser();

		addParser("summary", new SummaryNodeParser());
		addParser("published", new PublishedNodeParser());
		addParser("content", contentNodeParser);
	}

	public CompositeNodeParser getContentNodeParser() {
		return contentNodeParser;
	}

	public String buildData() {
		document = XMLParser.createDocument();
		final Element entryElement = createAndAppendEntryToDocument();

		if (getTitle() != null) {
			entryElement.appendChild(createTextElement("title", getTitle()));
		}
		if (getId() != null) {
			entryElement.appendChild(createTextElement("id", getId()));
		}
		if (getSummary() != null) {
			entryElement.appendChild(createTextElement("summary", getSummary()));
		}
		if (getContent() != null) {
			entryElement.appendChild(createContentNode());
		}
		// if (updated != null) {
		// entryElement.appendChild(createTextElement("updated", updated));
		// }

		for (final Person author : getAuthors()) {
			entryElement.appendChild(createPersonNode("author", author));
		}
		for (final Person contributor : getContributors()) {
			entryElement.appendChild(createPersonNode("contributor", contributor));
		}
		for (final Link link : getLinks()) {
			entryElement.appendChild(createLinkNode(link));
		}
		for (final Category category : getCategories()) {
			entryElement.appendChild(createCategoryNode(category));
		}

		return document.toString();
	}

	private Request gettinRequest;

	/**
	 * Parse the content retrived from the specified self link.
	 */
	@SuppressWarnings("unchecked")
	public Request getSelf() {
		if (gettinRequest != null && gettinRequest.isPending()) {
			return gettinRequest;
		}

		final Link selfLink = getSelfLink();

		if (selfLink == null) {
			return null;
		}

		final String url = selfLink.getHref();

		if (url == null || url.isEmpty()) {
			return null;
		}

		gettinRequest = service.getEntry(url, new EntryRequestCallback(this));

		return gettinRequest;
	}

	private Node createContentNode() {
		final Element contentElement = createTextElement("content", content);

		if (content.getSrc() != null) {
			contentElement.setAttribute("src", content.getSrc());
		}

		return contentElement;
	}

	protected Element createTextElement(String name, String value) {
		final Element element = document.createElement(name);

		element.appendChild(document.createTextNode(value));

		return element;
	}

	protected Element createTextElement(String name, Text text) {
		final Element textElement = createTextElement(name, text.getValue());

		if (text.getType() != null) {
			textElement.setAttribute("type", text.getType());
		}

		return textElement;
	}

	private Node createCategoryNode(Category category) {
		final Element categoryElement = document.createElement("link");

		categoryElement.setAttribute("term", category.getTerm());

		if (category.getLabel() != null) {
			categoryElement.setAttribute("label", category.getLabel());
		}
		if (category.getScheme() != null) {
			categoryElement.setAttribute("scheme", category.getScheme());
		}
		return categoryElement;
	}

	private Node createLinkNode(Link link) {
		final Element linkElement = document.createElement("link");

		linkElement.setAttribute("href", link.getHref());

		if (link.getTitle() != null) {
			linkElement.setAttribute("title", link.getTitle());
		}
		if (link.getType() != null) {
			linkElement.setAttribute("type", link.getType());
		}
		if (link.getHreflang() != null) {
			linkElement.setAttribute("hreflang", link.getHreflang());
		}
		if (link.getLength() > 0) {
			final String length = Integer.toString(link.getLength());
			linkElement.setAttribute("length", length);
		}
		if (link.getRel() != null) {
			linkElement.setAttribute("rel", link.getRel());
		}
		return linkElement;
	}

	private Node createPersonNode(String name, Person person) {
		final Element personElement = document.createElement(name);

		personElement.appendChild(createTextElement("name", person.getName()));

		if (person.getEmail() != null) {
			personElement.appendChild(createTextElement("email", person.getEmail()));
		}
		if (person.getUri() != null) {
			personElement.appendChild(createTextElement("uri", person.getUri()));
		}

		return personElement;
	}

	private Element createAndAppendEntryToDocument() {
		final Element entryElement = document.createElement("entry");

		entryElement.setAttribute("xmlns", "http://www.w3.org/2005/Atom");

		document.appendChild(entryElement);
		return entryElement;
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

	public final class ContentNodeParser extends CompositeNodeParser {

		@Override
		public final void parse(Node node) {
			if (getParsersCount() == 0) {
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
			else {
				super.parse(node);
			}
		}

	}

	private final class PublishedNodeParser implements NodeParser {

		public void parse(Node node) {
			published = XmlParserUtils.getDate(node);
		}

	}

}

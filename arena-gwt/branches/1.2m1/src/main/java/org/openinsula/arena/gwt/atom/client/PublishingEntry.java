package org.openinsula.arena.gwt.atom.client;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.util.client.Assert;
import org.openinsula.arena.gwt.xml.client.CompositeNodeFactory;
import org.openinsula.arena.gwt.xml.client.ListNodesFactory;
import org.openinsula.arena.gwt.xml.client.NodeFactorySupport;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 */
public abstract class PublishingEntry<T extends PublishingEntry<T>> extends BaseEntry<T> {

	private final CompositeNodeFactory entryNodeFactory = new EntryNodeFactory();

	public PublishingEntry() {
		this(null, null);
	}

	public PublishingEntry(String title, String id) {
		super(title, id);

		entryNodeFactory.add(new PeopleNodesFactory("author", getAuthors()));
		entryNodeFactory.add(new PeopleNodesFactory("contributor", getContributors()));
		entryNodeFactory.add(new CategoriesNodesFactory(getCategories()));
		entryNodeFactory.add(new LinksNodesFactory(getLinks()));
	}

	public void addNodeFactory(NodeFactorySupport nodeFactory) {
		entryNodeFactory.add(nodeFactory);
	}

	public void removeNodeFactory(NodeFactorySupport nodeFactory) {
		entryNodeFactory.remove(nodeFactory);
	}

	public String toXml() {
		final Document document = XMLParser.createDocument();

		entryNodeFactory.setDocument(document);

		document.appendChild(entryNodeFactory.createNode());

		return document.toString();
	}

	@Override
	public String toString() {
		return toXml();
	}

	private final class EntryNodeFactory extends CompositeNodeFactory {

		@Override
		protected Node createBaseNode() {
			final Element entryElement = createElement("entry");

			entryElement.setAttribute("xmlns", "http://www.w3.org/2005/Atom");
			return entryElement;
		}

	}

	private class CategoriesNodesFactory extends ListNodesFactory<Category> {

		public CategoriesNodesFactory(List<Category> categories) {
			super(categories);
		}

		@Override
		protected Node createNode(Category category) {
			final String term = category.getTerm();

			Assert.notEmpty(term, "'term' must not be null.");

			final Element categoryElement = createElement("category");
			final String label = category.getLabel();
			final String scheme = category.getScheme();

			categoryElement.setAttribute("term", term);

			if (label != null) {
				categoryElement.setAttribute("label", label);
			}
			if (scheme != null) {
				categoryElement.setAttribute("scheme", scheme);
			}
			return categoryElement;
		}

	}

	private class PeopleNodesFactory extends ListNodesFactory<Person> {

		private final String nodeName;

		public PeopleNodesFactory(String nodeName, List<Person> people) {
			super(people);

			this.nodeName = nodeName;
		}

		@Override
		protected Node createNode(Person person) {
			final String name = person.getName();

			Assert.notEmpty(name, "'name' must not b null.");

			final Element personElement = createElement(nodeName);
			final String email = person.getEmail();
			final String uri = person.getUri();

			personElement.appendChild(createTextElement("name", name));

			if (email != null) {
				personElement.appendChild(createTextElement("email", email));
			}
			if (uri != null) {
				personElement.appendChild(createTextElement("uri", uri));
			}
			return personElement;
		}

	}

	private class LinksNodesFactory extends ListNodesFactory<Link> {

		public LinksNodesFactory(Link link) {
			super(new ArrayList<Link>(1));

			getList().add(link);
		}

		public LinksNodesFactory(List<Link> links) {
			super(links);
		}

		@Override
		protected Node createNode(Link link) {
			final String href = link.getHref();

			Assert.notEmpty(href, "'href' must not b null.");

			final Element linkElement = createElement("link");
			final String title = link.getTitle();
			final String type = link.getType();
			final String hreflang = link.getHreflang();
			final byte length = link.getLength();
			final String rel = link.getRel();

			linkElement.setAttribute("href", href);

			if (title != null) {
				linkElement.setAttribute("title", title);
			}
			if (type != null) {
				linkElement.setAttribute("type", type);
			}
			if (hreflang != null) {
				linkElement.setAttribute("hreflang", hreflang);
			}
			if (length > 0) {
				linkElement.setAttribute("length", Integer.toString(length));
			}
			if (rel != null) {
				linkElement.setAttribute("rel", rel);
			}
			return linkElement;
		}

	}

}

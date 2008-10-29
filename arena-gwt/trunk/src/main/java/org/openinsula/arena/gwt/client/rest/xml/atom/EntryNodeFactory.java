package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.xml.ListNodesFactory;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class EntryNodeFactory<T extends BaseEntry<T>> extends CompositeNodeFactory {

	private final T entry;

	public EntryNodeFactory(T entry) {
		this.entry = entry;

		addNodeFactory(new PeopleNodesFactory("author", entry.getAuthors()));
		addNodeFactory(new PeopleNodesFactory("contributor", entry.getContributors()));
		addNodeFactory(new CategoriesNodesFactory(entry.getCategories()));
		addNodeFactory(new LinksNodesFactory(entry.getLinks()));
	}

	@Override
	protected Node createBaseNode() {
		final Element entryElement = createElement("entry");
		final Text title = entry.getTitle();
		final Text summary = entry.getSummary();

		entryElement.setAttribute("xmlns", "http://www.w3.org/2005/Atom");

		if (title != null) {
			entryElement.appendChild(createTextElement("title", title));
		}
		if (summary != null) {
			entryElement.appendChild(createTextElement("summary", summary));
		}
		return entryElement;
	}

	private class CategoriesNodesFactory extends ListNodesFactory<Category> {

		public CategoriesNodesFactory(List<Category> categories) {
			super(categories);
		}

		@Override
		protected Node createNode(Category category) {
			final String term = category.getTerm();

			if (term == null || term.trim().length() == 0) {
				throw new IllegalArgumentException("'term' must not b null.");
			}

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

			if (name == null || name.trim().length() == 0) {
				throw new IllegalArgumentException("'name' must not b null.");
			}

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

			if (href == null || href.trim().length() == 0) {
				throw new IllegalArgumentException("'href' must not b null.");
			}

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

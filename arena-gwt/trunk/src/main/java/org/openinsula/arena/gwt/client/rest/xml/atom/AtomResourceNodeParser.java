package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.parse.AbstractNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.TextNodeAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
class AtomResourceNodeParser<T extends AtomResource> extends CompositeNodeParser<T> {

	public AtomResourceNodeParser() {
	}

	public AtomResourceNodeParser(T appendable) {
		super(appendable);
	}

	protected void initParsers() {
		addNodeParser("id", new TextNodeAppender<T>() {
			@Override
			public void appendTo(T appendable, String returned) {
				appendable.setId(returned);
			}
		});
		addNodeParser("title", new TextNodeParser<T>() {
			@Override
			public void appendTo(T appendable, Text returned) {
				appendable.setTitle(returned);
			}
		});
		addNodeParser("updated", new TextNodeAppender<T>() {
			@Override
			public void appendTo(T appendable, String returned) {
				appendable.setUpdated(null); // FIXME
			}
		});
		addNodeParser("rights", new TextNodeParser<T>() {
			@Override
			public void appendTo(T appendable, Text returned) {
				appendable.setRights(returned);
			}
		});
		addNodeParser("author", new PersonNodeParser() {
			public void onNodeParsed(Person person) {
				atomResource.addAuthor(person);
			}
		});
		addNodeParser("contributor", new PersonNodeParser() {
			public void onNodeParsed(Person person) {
				atomResource.addContributor(person);
			}
		});
		addNodeParser("category", new CategoryNodeParser() {
			public void onNodeParsed(Category category) {
				atomResource.addCategory(category);
			}
		});
		addNodeParser("link", new LinkNodeParser() {
			public void onNodeParsed(Link link) {
				atomResource.addLink(link);
			}
		});
	}

	public T getAtomResource() {
		return atomResource;
	}

	public void setAtomResource(T atomResource) {
		this.atomResource = atomResource;
	}

	private abstract class CategoryNodeParser extends AbstractNodeParser<Category> {

		@Override
		public Category parseNode(Node node) {
			final String term = getNodeAttribute("term");

			if (term == null) {
				throw new NullPointerException("");
			}

			final Category category = new Category();
			category.setTerm(term);
			category.setScheme(getNodeAttribute("scheme"));
			category.setLabel(getNodeAttribute("label"));

			atomResource.addCategory(category);
			return category;
		}

	}

	private abstract class PersonNodeParser extends CompositeNodeParser<Person> {

		public PersonNodeParser() {
			super(new Person());

			addNodeParser("name", new TextNodeAppender<Person>() {
				@Override
				public void appendTo(Person person, String value) {
					person.setName(value);
				}
			});
			addNodeParser("uri", new ValueNodeParser() {
				public void onNodeParsed(String value) {
					person.setUri(value);
				}
			});
			addNodeParser("email", new ValueNodeParser() {
				public void onNodeParsed(String value) {
					person.setEmail(value);
				}
			});
		}

	}

	private abstract class LinkNodeParser extends AbstractNodeParser<Link> {

		@Override
		public Link parseNode(Node node) {
			final String href = getNodeAttribute("href");

			if (href == null) {
				throw new NullPointerException("");
			}
			final Link link = new Link();

			link.setHref(href);
			link.setHreflang(getNodeAttribute("hreflang"));
			link.setRel(getNodeAttribute("rel"));
			link.setType(getNodeAttribute("type"));
			link.setTitle(getNodeAttribute("title"));

			final String length = getNodeAttribute("length");

			if (length != null) {
				link.setLength(Byte.valueOf(length));
			}
			return link;
		}

	}

}

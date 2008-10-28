package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.AwareNodeParser;
import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.ValueNodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
class AtomResourceNodeParser<T extends AtomResource> extends CompositeNodeParser<T> {

	private T atomResource;

	public AtomResourceNodeParser() {
		this(null);
	}

	public AtomResourceNodeParser(final T atomResource) {
		this.atomResource = atomResource;

		initParsers();
	}

	protected void initParsers() {
		addNodeParser("id", new ValueNodeParser() {
			public void onNodeParsed(String value) {
				atomResource.setId(value);
			}
		});
		addNodeParser("title", new TextNodeParser() {
			public void onNodeParsed(Text text) {
				atomResource.setTitle(text);
			}
		});
		addNodeParser("updated", new ValueNodeParser() {
			public void onNodeParsed(String value) {
				atomResource.setUpdated(null); // FIXME
			}
		});
		addNodeParser("rights", new TextNodeParser() {
			public void onNodeParsed(Text text) {
				atomResource.setRights(text);
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

	private abstract class CategoryNodeParser implements AwareNodeParser<Category> {

		public Category parse(Node node) {
			final String term = XmlParserUtils.getAttribute(node, "term");

			if (term == null) {
				throw new NullPointerException("");
			}

			final Category category = new Category();
			category.setTerm(term);
			category.setScheme(XmlParserUtils.getAttribute(node, "scheme"));
			category.setLabel(XmlParserUtils.getAttribute(node, "label"));

			atomResource.addCategory(category);

			return category;
		}

	}

	private abstract class PersonNodeParser extends CompositeNodeParser<Person> implements AwareNodeParser<Person> {

		private final Person person = new Person();

		public PersonNodeParser() {
			addNodeParser("name", new ValueNodeParser() {
				public void onNodeParsed(String value) {
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

		@Override
		public Person parse(Node node) {
			super.parse(node);

			return person;
		}

	}

	private abstract class LinkNodeParser implements AwareNodeParser<Link> {

		public Link parse(Node node) {
			final String href = XmlParserUtils.getAttribute(node, "href");

			if (href == null) {
				throw new NullPointerException("");
			}
			final Link link = new Link();

			link.setHref(href);
			link.setHreflang(XmlParserUtils.getAttribute(node, "hreflang"));
			link.setRel(XmlParserUtils.getAttribute(node, "rel"));
			link.setType(XmlParserUtils.getAttribute(node, "type"));
			link.setTitle(XmlParserUtils.getAttribute(node, "title"));

			final String length = XmlParserUtils.getAttribute(node, "length");

			if (length != null) {
				link.setLength(Byte.valueOf(length));
			}
			return link;
		}

	}

}

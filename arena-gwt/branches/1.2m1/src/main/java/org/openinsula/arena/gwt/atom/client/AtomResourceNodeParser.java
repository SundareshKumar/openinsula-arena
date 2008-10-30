package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;
import org.openinsula.arena.gwt.xml.client.AppendableProvider;
import org.openinsula.arena.gwt.xml.client.CompositeNodeParser;
import org.openinsula.arena.gwt.xml.client.NodeTextAppender;
import org.openinsula.arena.gwt.xml.client.ParsedNodeResultAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
class AtomResourceNodeParser<T extends AtomResource> extends CompositeNodeParser<T> {

	public AtomResourceNodeParser(T component) {
		super(component);
	}

	protected void initParsers() {
		addNodeParser("id", new NodeTextAppender<T>() {
			@Override
			public void appendResultTo(T atomResource, String id) {
				atomResource.setId(id);
			}
		});
		addNodeParser("title", new AtomTextNodeAppender<T>() {
			@Override
			public void appendResultTo(T atomResource, Text title) {
				atomResource.setTitle(title);
			}
		});
		addNodeParser("updated", new NodeTextAppender<T>() {
			@Override
			public void appendResultTo(T appendable, String returned) {
				appendable.setUpdated(null); // FIXME
			}
		});
		addNodeParser("rights", new AtomTextNodeAppender<T>() {
			@Override
			public void appendResultTo(T atomResource, Text rights) {
				atomResource.setRights(rights);
			}
		});
		addNodeParser("author", new ParsedNodeResultAppender<T, Person>(new PersonNodeParser()) {
			@Override
			public void appendResultTo(T atomResource, Person person) {
				atomResource.addAuthor(person);
			}
		});
		addNodeParser("contributor", new ParsedNodeResultAppender<T, Person>(new PersonNodeParser()) {
			@Override
			public void appendResultTo(T atomResource, Person person) {
				atomResource.addContributor(person);
			}
		});
		addNodeParser("category", new CategoryNodeParser());
		addNodeParser("link", new LinkNodeParser<T>() {
			@Override
			public void appendResultTo(T atomResource, Link link) {
				atomResource.addLink(link);
			}
		});
	}

	private class CategoryNodeParser extends AbstractParsedNodeResultAppender<T, Category> {

		@Override
		public void appendResultTo(T atomResource, Category category) {
			atomResource.addCategory(category);
		}

		@Override
		public Category parseNode(Node node) {
			final String term = getNodeAttribute("term");

			if (term == null) {
				throw new NullPointerException("'term' must not be null.");
			}

			final Category category = new Category();
			category.setTerm(term);
			category.setScheme(getNodeAttribute("scheme"));
			category.setLabel(getNodeAttribute("label"));

			return category;
		}

	}

	private class PersonNodeParser extends CompositeNodeParser<Person> {

		public PersonNodeParser() {
			super(new AppendableProvider<Person>() {
				public Person getAppendable() {
					return new Person();
				}
			});

			addNodeParser("name", new NodeTextAppender<Person>() {
				@Override
				public void appendResultTo(Person person, String value) {
					person.setName(value);
				}
			});
			addNodeParser("uri", new NodeTextAppender<Person>() {
				@Override
				public void appendResultTo(Person person, String value) {
					person.setUri(value);
				}
			});
			addNodeParser("email", new NodeTextAppender<Person>() {
				@Override
				public void appendResultTo(Person person, String value) {
					person.setEmail(value);
				}
			});
		}

	}

}

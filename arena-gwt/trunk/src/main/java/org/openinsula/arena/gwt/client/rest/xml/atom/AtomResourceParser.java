package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class AtomResourceParser<T extends AtomResource> extends CompositeNodeParser {

	private T atomResource;

	public AtomResourceParser() {
		this(null);
	}

	public AtomResourceParser(T atomResource) {
		this.atomResource = atomResource;

		addParser("id", new IdNodeParser());
		addParser("title", new TitleNodeParser());
		addParser("updated", new UpdatedNodeParser());
		addParser("rights", new RightsNodeParser());
		addParser("author", new AuthorNodeParser());
		addParser("contributor", new ContributorNodeParser());
		addParser("category", new CategoryNodeParser());
		addParser("link", new EntryLinkNodeParser());
	}

	public T getAtomResource() {
		return atomResource;
	}

	public void setAtomResource(T atomResource) {
		this.atomResource = atomResource;
	}

	private final class IdNodeParser implements NodeParser {

		public T parse(Node node) {
			final Node firstChild = node.getFirstChild();

			if (firstChild != null) {
				atomResource.setId(firstChild.getNodeValue());
			}
		}

	}

	private final class TitleNodeParser extends TextNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			atomResource.setTitle(getText());
		}

	}

	private final class UpdatedNodeParser implements NodeParser {

		public T parse(Node node) {
			atomResource.setUpdated(XmlParserUtils.getDate(node));
		}

	}

	private final class RightsNodeParser extends TextNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			atomResource.setRights(getText());
		}

	}

	private final class AuthorNodeParser extends PersonNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			atomResource.addAuthor(getPerson());
		}

	}

	private final class ContributorNodeParser extends PersonNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			atomResource.addContributor(getPerson());
		}

	}

	private final class CategoryNodeParser implements NodeParser {

		public T parse(Node node) {
			final String term = XmlParserUtils.getAttribute(node, "term");

			if (term == null) {
				throw new NullPointerException("");
			}

			final Category category = new Category();
			category.setTerm(term);
			category.setScheme(XmlParserUtils.getAttribute(node, "scheme"));
			category.setLabel(XmlParserUtils.getAttribute(node, "label"));

			atomResource.addCategory(category);
		}

	}

	private final class EntryLinkNodeParser extends LinkNodeParser {

		@Override
		public T parse(Node node) {
			super.parse(node);

			atomResource.addLink(getLink());
		}

	}

}

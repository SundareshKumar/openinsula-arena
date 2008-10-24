package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.List;

import org.openinsula.arena.gwt.client.xml.ListNodesFactory;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class CategoriesNodesFactory extends ListNodesFactory<Category> {

	public CategoriesNodesFactory(List<Category> categories) {
		super(categories);
	}

	@Override
	protected Node createNode(Category category) {
		final String term = category.getTerm();

		if (term == null || term.trim().length() == 0) {
			throw new IllegalArgumentException("");
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

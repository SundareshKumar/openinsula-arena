package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.xml.ListNodesFactory;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class LinksNodesFactory extends ListNodesFactory<Link> {

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
			throw new IllegalArgumentException("");
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

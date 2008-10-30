package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class LinkNodeParser<T> extends AbstractParsedNodeResultAppender<T, Link> {

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

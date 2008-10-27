package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class LinkNodeParser implements NodeParser<Link> {

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
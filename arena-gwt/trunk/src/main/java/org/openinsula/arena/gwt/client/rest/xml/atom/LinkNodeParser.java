package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.NodeParser;
import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class LinkNodeParser implements NodeParser {

	private final Link link;

	public LinkNodeParser() {
		this(new Link());
	}

	public LinkNodeParser(Link link) {
		this.link = link;
	}

	public void parse(Node node) {
		final String href = XmlParserUtils.getAttribute(node, "href");

		if (href == null) {
			throw new NullPointerException("");
		}

		link.setHref(href);
		link.setHreflang(XmlParserUtils.getAttribute(node, "hreflang"));
		link.setRel(XmlParserUtils.getAttribute(node, "rel"));
		link.setType(XmlParserUtils.getAttribute(node, "type"));
		link.setTitle(XmlParserUtils.getAttribute(node, "title"));

		final String length = XmlParserUtils.getAttribute(node, "length");

		if (length != null) {
			link.setLength(Byte.valueOf(length));
		}
	}

	public final Link getLink() {
		return link;
	}

}
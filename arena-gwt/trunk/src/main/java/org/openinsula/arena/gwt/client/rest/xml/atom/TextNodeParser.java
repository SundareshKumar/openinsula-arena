package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class TextNodeParser implements NodeParser {

	private final Text text = new Text();

	public void parse(Node node) {
		final String value = XmlParserUtils.getText(node);

		if (value == null) {
			throw new NullPointerException("");
		}

		text.setValue(value);
		text.setType(XmlParserUtils.getAttribute(node, "type"));
	}

	public final Text getText() {
		return text;
	}

}

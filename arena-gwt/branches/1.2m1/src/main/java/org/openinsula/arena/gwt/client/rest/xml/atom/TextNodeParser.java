package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.AwareNodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class TextNodeParser implements AwareNodeParser<Text> {

	public Text parse(Node node) {
		final String value = XmlParserUtils.getText(node);

		if (value == null) {
			throw new NullPointerException("");
		}
		final Text text = new Text();

		text.setValue(value);
		text.setType(XmlParserUtils.getAttribute(node, "type"));

		return text;
	}

}
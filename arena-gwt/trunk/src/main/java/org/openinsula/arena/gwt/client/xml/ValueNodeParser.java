package org.openinsula.arena.gwt.client.xml;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class ValueNodeParser implements AwareNodeParser<String> {

	public final String parse(Node node) {
		return XmlParserUtils.getText(node);
	}

}

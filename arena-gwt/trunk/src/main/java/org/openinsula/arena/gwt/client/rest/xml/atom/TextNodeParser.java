package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.parse.NodeAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class TextNodeParser<A> extends NodeAppender<A, Text> {

	@Override
	public Text parseNode(Node node) {
		final String value = getNodeText();

		if (value == null) {
			throw new NullPointerException("Node value must not be null.");
		}
		final Text text = new Text();

		text.setValue(value);
		text.setType(getNodeAttribute("type"));

		return text;
	}

}

package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class AtomTextNodeAppender<A> extends AbstractParsedNodeResultAppender<A, Text> {

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

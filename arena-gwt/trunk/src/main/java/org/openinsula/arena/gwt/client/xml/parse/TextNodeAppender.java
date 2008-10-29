package org.openinsula.arena.gwt.client.xml.parse;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class TextNodeAppender<A> extends NodeAppender<A, String> {

	@Override
	public String parseNode(Node node) {
		return getNodeText();
	}

}

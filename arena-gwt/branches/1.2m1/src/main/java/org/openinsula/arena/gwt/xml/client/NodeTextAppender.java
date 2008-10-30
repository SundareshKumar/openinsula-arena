package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class NodeTextAppender<A> extends AbstractParsedNodeResultAppender<A, String> {

	@Override
	public String parseNode(Node node) {
		return getNodeText();
	}

}

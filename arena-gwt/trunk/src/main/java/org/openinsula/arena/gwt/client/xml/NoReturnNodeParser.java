package org.openinsula.arena.gwt.client.xml;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class NoReturnNodeParser implements NodeParser<Void> {

	protected abstract void doParse(Node node);

	public final Void parse(Node node) {
		return null;
	}

}

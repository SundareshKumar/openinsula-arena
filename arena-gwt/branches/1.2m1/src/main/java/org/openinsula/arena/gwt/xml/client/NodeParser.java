package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public interface NodeParser<R> {

	public R parse(Node node);

}

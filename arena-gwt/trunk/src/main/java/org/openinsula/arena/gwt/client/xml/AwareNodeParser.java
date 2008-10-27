package org.openinsula.arena.gwt.client.xml;

/**
 * @author Lucas K Mogari
 */
public interface AwareNodeParser<T> extends NodeParser<T> {

	public void onNodeParsed(T t);

}

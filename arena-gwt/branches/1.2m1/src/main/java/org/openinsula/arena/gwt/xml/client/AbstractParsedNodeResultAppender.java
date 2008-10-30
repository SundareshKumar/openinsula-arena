package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.Node;

/**
 * Provides a method to append the result from parsing a node to an object.
 * 
 * @author Lucas K Mogari
 */
public abstract class AbstractParsedNodeResultAppender<T, R> extends AbstractNodeParser<R> {

	private T appendable;

	public AbstractParsedNodeResultAppender() {
	}

	public AbstractParsedNodeResultAppender(T appendable) {
		this.appendable = appendable;
	}

	public abstract void appendResultTo(T appendable, R result);

	@Override
	public final R parse(Node node) {
		final R result = super.parse(node);

		appendResultTo(appendable, result);
		return result;
	}

	void setAppendable(T appendable) {
		this.appendable = appendable;
	}

}

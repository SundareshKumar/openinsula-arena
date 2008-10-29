package org.openinsula.arena.gwt.client.xml.parse;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class NodeAppender<A, R> extends AbstractNodeParser<R> {

	private A appendable;

	public NodeAppender() {
	}

	public NodeAppender(A appendable) {
		this.appendable = appendable;
	}

	public abstract void appendTo(A appendable, R returned);

	@Override
	public final R parse(Node node) {
		final R returned = super.parse(node);

		appendTo(appendable, returned);

		return returned;
	}

	public void setAppendable(A appendable) {
		this.appendable = appendable;
	}

}

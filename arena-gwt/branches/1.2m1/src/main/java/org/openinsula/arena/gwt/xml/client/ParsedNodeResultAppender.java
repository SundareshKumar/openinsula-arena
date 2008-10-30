package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.Node;

/**
 * Provides a method to append the result from {@link NodeParser} to an object.
 * 
 * @author Lucas K Mogari
 */
public abstract class ParsedNodeResultAppender<T, R> extends AbstractParsedNodeResultAppender<T, R> {

	private NodeParser<R> nodeParser;

	public ParsedNodeResultAppender(NodeParser<R> nodeParser) {
		this(nodeParser, null);
	}

	public ParsedNodeResultAppender(NodeParser<R> nodeParser, T appendable) {
		super(appendable);

		this.nodeParser = nodeParser;
	}

	@Override
	public final R parseNode(Node node) {
		return nodeParser.parse(node);
	}

}

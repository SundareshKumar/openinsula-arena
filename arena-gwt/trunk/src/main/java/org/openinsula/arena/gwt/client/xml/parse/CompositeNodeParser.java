package org.openinsula.arena.gwt.client.xml.parse;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeParser<R> extends AbstractNodeParser<R> {

	private final Map<String, NodeParser<?>> parsers = new HashMap<String, NodeParser<?>>();

	private R appendable;

	public CompositeNodeParser() {
	}

	public CompositeNodeParser(R appendable) {
		this.appendable = appendable;
	}

	@Override
	public final R parse(Node node) {
		return super.parse(node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public R parseNode(Node node) {
		final NodeList childNodes = node.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final String nodeName = childNode.getNodeName();
			final NodeParser<?> nodeParser = parsers.get(nodeName);

			if (nodeParser != null) {
				if (appendable != null && nodeParser instanceof NodeAppender) {
					((NodeAppender) nodeParser).setAppendable(appendable);
				}

				nodeParser.parse(node);
			}
		}
		return appendable;
	}

	public boolean hasParsers() {
		return !parsers.isEmpty();
	}

	public CompositeNodeParser<R> addNodeParser(String nodeName, NodeParser<?> parser) {
		parsers.put(nodeName, parser);
		return this;
	}

	public void removeNodeParser(String nodeName) {
		parsers.remove(nodeName);
	}

	public NodeParser<?> getNodeParser(String nodeName) {
		return parsers.get(nodeName);
	}

}

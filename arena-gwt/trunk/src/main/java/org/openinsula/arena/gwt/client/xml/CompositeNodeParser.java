package org.openinsula.arena.gwt.client.xml;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeParser<T> implements NodeParser<T> {

	private final Map<String, NodeParser<?>> parsers = new HashMap<String, NodeParser<?>>();

	public T parse(Node node) {
		final NodeList childNodes = node.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final String nodeName = childNode.getNodeName();
			final NodeParser<?> nodeParser = parsers.get(nodeName);

			if (nodeParser != null) {
				parseNode(nodeParser, childNode);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	void parseNode(NodeParser<?> nodeParser, Node node) {
		final Object parsed = nodeParser.parse(node);

		if (parsed != null && nodeParser instanceof AwareNodeParser) {
			((AwareNodeParser) nodeParser).onNodeParsed(parsed);
		}
	}

	public boolean hasParsers() {
		return !parsers.isEmpty();
	}

	public void addParser(String nodeName, NodeParser<?> parser) {
		parsers.put(nodeName, parser);
	}

	public void removeParser(String nodeName) {
		parsers.remove(nodeName);
	}

	public NodeParser<?> getNodeParser(String nodeName) {
		return parsers.get(nodeName);
	}

}

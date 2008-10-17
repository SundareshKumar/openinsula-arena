package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeParser implements NodeParser {

	private final Map<String, NodeParser> parsers = new HashMap<String, NodeParser>();

	public void parse(Node node) {
		final NodeList childNodes = node.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final NodeParser nodeParser = parsers.get(childNode.getNodeName());

			if (nodeParser != null) {
				nodeParser.parse(childNode);
			}
		}
	}

	public int getParsersCount() {
		return parsers.size();
	}

	public void addParser(String nodeName, NodeParser parser) {
		parsers.put(nodeName, parser);
	}

	public void removeParser(String nodeName) {
		parsers.remove(nodeName);
	}

	public NodeParser getNodeParser(String nodeName) {
		return parsers.get(nodeName);
	}

}

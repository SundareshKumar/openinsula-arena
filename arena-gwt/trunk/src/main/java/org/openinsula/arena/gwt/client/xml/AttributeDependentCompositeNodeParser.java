package org.openinsula.arena.gwt.client.xml;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public class AttributeDependentCompositeNodeParser<T> implements NodeParser<T> {

	private final CompositeNodeParser compositeNodeParser = new CompositeNodeParser();

	private final String attributeName;

	public AttributeDependentCompositeNodeParser(String attributeName) {
		this.attributeName = attributeName;
	}

	public T parse(Node node) {
		final NamedNodeMap namedNodeMap = node.getAttributes();
		final Node attributeNode = namedNodeMap.getNamedItem(attributeName);

		if (attributeNode != null) {
			final String nodeName = XmlParserUtils.getText(attributeNode);
			final NodeParser nodeParser = compositeNodeParser.getNodeParser(nodeName);

			compositeNodeParser.parseNode(nodeParser, node);
		}
		return null;
	}

	public void addParser(String attributeValue, NodeParser parser) {
		compositeNodeParser.addParser(attributeValue, parser);
	}

	public void removeParser(String attributeValue) {
		compositeNodeParser.removeParser(attributeValue);
	}

}

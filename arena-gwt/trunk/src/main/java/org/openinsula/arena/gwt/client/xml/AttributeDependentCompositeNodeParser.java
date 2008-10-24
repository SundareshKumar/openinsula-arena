package org.openinsula.arena.gwt.client.xml;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class AttributeDependentCompositeNodeParser implements NodeParser {

	private final CompositeNodeParser compositeNodeParser;

	private final String attributeName;

	public AttributeDependentCompositeNodeParser(String attributeName) {
		this.attributeName = attributeName;

		compositeNodeParser = new CompositeNodeParser();
	}

	public final void parse(Node node) {
		final NamedNodeMap namedNodeMap = node.getAttributes();
		final Node attributeNode = namedNodeMap.getNamedItem(attributeName);

		if (attributeNode != null) {
			final String nodeName = XmlParserUtils.getText(attributeNode);
			final NodeParser nodeParser = compositeNodeParser.getNodeParser(nodeName);

			nodeParser.parse(node);
		}
	}

	public void addParser(String attributeValue, NodeParser parser) {
		compositeNodeParser.addParser(attributeValue, parser);
	}

	public void removeParser(String attributeValue) {
		compositeNodeParser.removeParser(attributeValue);
	}

}

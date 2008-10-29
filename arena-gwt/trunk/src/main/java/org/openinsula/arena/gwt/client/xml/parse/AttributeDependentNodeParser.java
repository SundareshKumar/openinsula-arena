package org.openinsula.arena.gwt.client.xml.parse;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public class AttributeDependentNodeParser<R> extends AbstractNodeParser<R> {

	private final CompositeNodeParser<R> compositeNodeParser;

	private final String attributeName;

	public AttributeDependentNodeParser(String attributeName) {
		this(attributeName, null);
	}

	public AttributeDependentNodeParser(String attributeName, R appendable) {
		this.attributeName = attributeName;
		compositeNodeParser = new CompositeNodeParser<R>(appendable);
	}

	@Override
	public final R parseNode(Node node) {
		final NamedNodeMap namedNodeMap = node.getAttributes();
		final Node attributeNode = namedNodeMap.getNamedItem(attributeName);
		R returned = null;

		if (attributeNode != null) {
			returned = compositeNodeParser.parse(attributeNode);
		}
		return returned;
	}

	public AttributeDependentNodeParser<R> addNodeParser(String attributeValue, NodeParser parser) {
		compositeNodeParser.addNodeParser(attributeValue, parser);
		return this;
	}

	public void removeNodeParser(String attributeValue) {
		compositeNodeParser.removeNodeParser(attributeValue);
	}

}

package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractNodeParser<R> implements NodeParser<R> {

	private Node node;

	public abstract R parseNode(Node node);

	public R parse(Node node) {
		this.node = node;

		return parseNode(node);
	}

	public String getNodeName() {
		return node.getNodeName();
	}

	public String getNodeText() {
		return getValue(node);
	}

	public String getNodeAttribute(String attributeName) {
		final NamedNodeMap namedNodeMap = node.getAttributes();
		final Node attributeNode = namedNodeMap.getNamedItem(attributeName);

		return getValue(attributeNode);
	}

	public Node findNode(String targetNodeName) {
		return findNode(targetNodeName, false);
	}

	public Node findNode(String targetNodeName, boolean recursive) {
		final NodeList childNodes = node.getChildNodes();
		Node foundNode = null;

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final String nodeName = childNode.getNodeName();

			if (targetNodeName.equals(nodeName)) {
				foundNode = childNode;
			}
			else if (recursive) {
				foundNode = findNode(targetNodeName);
			}

			if (foundNode != null) {
				break;
			}
		}

		return foundNode;
	}

	private String getValue(Node node) {
		final Node firstChild = node.getFirstChild();
		return firstChild != null ? firstChild.getNodeValue() : null;
	}

}

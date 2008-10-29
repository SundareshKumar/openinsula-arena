package org.openinsula.arena.gwt.client.xml.parse;

import java.util.Date;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public abstract class XmlParserUtils {

	public static Date getDate(Node node) {
		// TODO
		return null;
	}

	public static String getText(Node node) {
		final Node firstChild = node.getFirstChild();

		return firstChild != null ? firstChild.getNodeValue() : null;
	}

	public static String getAttribute(Node node, String attributeName) {
		final NamedNodeMap namedNodeMap = node.getAttributes();
		final Node attributeNode = namedNodeMap.getNamedItem(attributeName);

		return getText(attributeNode);
	}

	public static Node findNode(String targetNodeName, Node node) {
		return findNode(targetNodeName, node, false);
	}

	public static Node findNode(String targetNodeName, Node node, boolean recursive) {
		final NodeList childNodes = node.getChildNodes();
		Node foundNode = null;

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final String nodeName = childNode.getNodeName();

			if (targetNodeName.equals(nodeName)) {
				foundNode = childNode;
			}
			else if (recursive) {
				foundNode = findNode(targetNodeName, childNode);
			}

			if (foundNode != null) {
				break;
			}
		}

		return foundNode;
	}

}

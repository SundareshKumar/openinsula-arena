package org.openinsula.arena.gwt.client.xml;

import org.openinsula.arena.gwt.client.rest.xml.atom.Text;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class SingleNodeProvider extends ElementFactory implements SingleNodeFactory {

	private Node node;

	public SingleNodeProvider() {
	}

	public SingleNodeProvider(String tagName) {
		node = createElement(tagName);
	}

	public SingleNodeProvider(String tagName, String value) {
		node = createTextElement(tagName, value);
	}

	public SingleNodeProvider(String tagName, Text text) {
		node = createTextElement(tagName, text);
	}

	public SingleNodeProvider(Node node) {
		this.node = node;
	}

	public Node createNode() {
		return node;
	}

}

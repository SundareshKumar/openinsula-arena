package org.openinsula.arena.gwt.xml.client;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class SingleNodeFactory extends AbstractSingleNodeFactory {

	private Node node;

	public SingleNodeFactory() {
	}

	public SingleNodeFactory(String tagName) {
		node = createElement(tagName);
	}

	public SingleNodeFactory(String tagName, String value) {
		node = createTextElement(tagName, value);
	}

	public SingleNodeFactory(Node node) {
		this.node = node;
	}

	@Override
	public Node createNode() {
		return node;
	}

}

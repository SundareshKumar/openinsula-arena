package org.openinsula.arena.gwt.client.xml;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.client.rest.xml.atom.Text;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeFactory extends SingleNodeProvider {

	private final List<NodeFactory> nodeFactories = new LinkedList<NodeFactory>();

	public CompositeNodeFactory() {
	}

	public CompositeNodeFactory(Node node) {
		super(node);
	}

	public CompositeNodeFactory(String tagName, String value) {
		super(tagName, value);
	}

	public CompositeNodeFactory(String tagName, Text text) {
		super(tagName, text);
	}

	public CompositeNodeFactory(String tagName) {
		super(tagName);
	}

	protected Node createBaseNode() {
		return super.createNode();
	}

	@Override
	public final Node createNode() {
		final Node node = createBaseNode();

		if (node != null) {
			for (final NodeFactory nodeFactory : nodeFactories) {
				nodeFactory.setDocument(getDocument());

				if (nodeFactory instanceof SingleNodeProvider) {
					final SingleNodeProvider singleNodeProvider = (SingleNodeProvider) nodeFactory;
					final Node createdNode = singleNodeProvider.createNode();

					if (createdNode != null) {
						node.appendChild(createdNode);
					}
				}
				else if (nodeFactory instanceof MultipleNodeFactory) {
					final MultipleNodeFactory multipleNodeFactory = (MultipleNodeFactory) nodeFactory;
					final Node[] createdNodes = multipleNodeFactory.createNodes();

					if (createdNodes != null) {
						for (final Node createdNode : createdNodes) {
							node.appendChild(createdNode);
						}
					}
				}
			}
		}
		return node;
	}

	public CompositeNodeFactory addNodeFactory(NodeFactory nodeFactory) {
		nodeFactories.add(nodeFactory);
		return this;
	}

	public void removeNodeFactory(NodeFactory nodeFactory) {
		nodeFactories.remove(nodeFactory);
	}

}

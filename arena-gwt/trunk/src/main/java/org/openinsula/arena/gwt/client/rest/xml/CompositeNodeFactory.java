package org.openinsula.arena.gwt.client.rest.xml;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class CompositeNodeFactory extends AbstractNodeFactory implements SingleNodeFactory {

	private final List<NodeFactory> nodeFactories = new LinkedList<NodeFactory>();

	protected abstract Node createBaseNode();

	public final Node createNode() {
		final Node node = createBaseNode();

		if (node != null) {
			for (final NodeFactory nodeFactory : nodeFactories) {
				nodeFactory.setDocument(getDocument());

				if (nodeFactory instanceof SingleNodeFactory) {
					final SingleNodeFactory singleNodeFactory = (SingleNodeFactory) nodeFactory;
					final Node createdNode = singleNodeFactory.createNode();

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

	public void addNodeFactory(NodeFactory nodeFactory) {
		nodeFactories.add(nodeFactory);
	}

	public void removeNodeFactory(NodeFactory nodeFactory) {
		nodeFactories.remove(nodeFactory);
	}

}

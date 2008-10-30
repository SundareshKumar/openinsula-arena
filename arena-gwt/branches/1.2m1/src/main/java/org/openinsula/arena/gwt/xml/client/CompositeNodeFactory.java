package org.openinsula.arena.gwt.xml.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeFactory extends SingleNodeFactory {

	private final List<NodeFactorySupport> nodeFactorySupports = new LinkedList<NodeFactorySupport>();

	public CompositeNodeFactory() {
	}

	public CompositeNodeFactory(String tagName, String value) {
		super(tagName, value);
	}

	public CompositeNodeFactory(String tagName) {
		super(tagName);
	}

	public CompositeNodeFactory(Node node) {
		super(node);
	}

	/**
	 * Overwrite this method for custom node creation.
	 */
	protected Node createBaseNode() {
		return super.createNode();
	}

	@Override
	public final Node createNode() {
		final Node node = createBaseNode();

		if (node != null) {
			for (final NodeFactorySupport nodeFactorySupport : nodeFactorySupports) {
				nodeFactorySupport.setDocument(getDocument());

				if (nodeFactorySupport instanceof SingleNodeFactory) {
					final SingleNodeFactory singleNodeFactory = (SingleNodeFactory) nodeFactorySupport;
					final Node createdNode = singleNodeFactory.createNode();

					if (createdNode != null) {
						node.appendChild(createdNode);
					}
				}
				else if (nodeFactorySupport instanceof MultipleNodeFactory) {
					final MultipleNodeFactory multipleNodeFactory = (MultipleNodeFactory) nodeFactorySupport;
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

	public void add(NodeFactorySupport nodeFactorySupport) {
		nodeFactorySupports.add(nodeFactorySupport);
	}

	public void remove(NodeFactorySupport nodeFactorySupport) {
		nodeFactorySupports.remove(nodeFactorySupport);
	}

}

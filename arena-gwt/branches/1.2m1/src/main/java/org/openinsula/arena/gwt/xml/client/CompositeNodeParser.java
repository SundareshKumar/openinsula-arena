package org.openinsula.arena.gwt.xml.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

/**
 * @author Lucas K Mogari
 */
public class CompositeNodeParser<T> extends AbstractNodeParser<T> {

	private final Map<String, NodeParser<?>> nodeParses = new HashMap<String, NodeParser<?>>();

	private AppendableProvider<T> appendableProvider;

	public CompositeNodeParser() {
	}

	public CompositeNodeParser(final T result) {
		this(new AppendableProvider<T>() {
			public T getAppendable() {
				return result;
			}
		});
	}

	public CompositeNodeParser(AppendableProvider<T> appendableProvider) {
		this.appendableProvider = appendableProvider;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T parseNode(Node node) {
		final NodeList childNodes = node.getChildNodes();
		final T appendable = appendableProvider.getAppendable();

		for (int i = 0; i < childNodes.getLength(); i++) {
			final Node childNode = childNodes.item(i);
			final String nodeName = childNode.getNodeName();
			final NodeParser<?> parser = getNodeParser(nodeName);

			if (parser != null) {
				if (appendable != null && parser instanceof AbstractParsedNodeResultAppender) {
					((AbstractParsedNodeResultAppender) parser).setAppendable(appendable);
				}
				parser.parse(node);
			}
		}
		return appendable;
	}

	public boolean hasParsers() {
		return !nodeParses.isEmpty();
	}

	public void addNodeParser(String nodeName, NodeParser<?> parser) {
		nodeParses.put(nodeName, parser);
	}

	public void addNodeParser(String nodeName, AbstractParsedNodeResultAppender<T, ?> parser) {
		nodeParses.put(nodeName, parser);
	}

	public void removeNodeParser(String nodeName) {
		nodeParses.remove(nodeName);
	}

	public NodeParser<?> getNodeParser(String nodeName) {
		return nodeParses.get(nodeName);
	}

}

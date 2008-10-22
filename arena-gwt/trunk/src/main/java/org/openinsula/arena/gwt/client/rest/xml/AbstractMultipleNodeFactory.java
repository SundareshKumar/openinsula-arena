package org.openinsula.arena.gwt.client.rest.xml;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractMultipleNodeFactory<T> extends AbstractNodeFactory implements MultipleNodeFactory {

	private List<T> list = new ArrayList<T>();

	public AbstractMultipleNodeFactory(List<T> list) {
		this.list = list;
	}

	protected abstract Node createNode(T T);

	public Node[] createNodes() {
		Node[] nodes = null;

		if (list != null && !list.isEmpty()) {
			nodes = new Node[list.size()];

			for (int i = 0; i < nodes.length; i++) {
				final T t = list.get(i);

				if (t != null) {
					nodes[i] = createNode(t);
				}
			}
		}

		return nodes;
	}

	protected List<T> getList() {
		return list;
	}

}

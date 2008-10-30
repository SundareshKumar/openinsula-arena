package org.openinsula.arena.gwt.xml.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class ListNodesFactory<T> extends MultipleNodeFactory {

	private List<T> list = new ArrayList<T>();

	public ListNodesFactory(List<T> list) {
		this.list = list;
	}

	protected abstract Node createNode(T T);

	@Override
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

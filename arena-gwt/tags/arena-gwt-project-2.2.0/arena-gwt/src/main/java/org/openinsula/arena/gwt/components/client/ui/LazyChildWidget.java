package org.openinsula.arena.gwt.components.client.ui;

import org.openinsula.arena.gwt.components.client.beans.LazyProperty;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class LazyChildWidget<T> extends LazyProperty<T> {

	@Override
	protected final T createProperty() {
		return createProperty(Document.get());
	}

	protected abstract T createProperty(final Document document);

	public final void removeIfLeaf() {
		boolean isLeaf = false;

		if (property instanceof Node) {
			isLeaf = !((Node) property).hasChildNodes();
		} else if (property instanceof HasWidgets) {
			isLeaf = !((HasWidgets) property).iterator().hasNext();
		}

		if (isLeaf) {
			remove();
		}
	}

}

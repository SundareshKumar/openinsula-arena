package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link HTMLWidgetFactory#li()} instead.
 */
public class ListItem extends ComplexPanel {

	public ListItem() {
		setElement(DOM.createElement("li"));
	}

	@Override
	public void add(final Widget w) {
		super.add(w, getElement());
	}

	public void insert(final Widget w, final int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

}

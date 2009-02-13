package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link HTMLWidgetFactory#ul()} instead.
 */
public class UnorderedList extends ComplexPanel {

	public UnorderedList() {
		setElement(DOM.createElement("ul"));
	}

	@Override
	public void add(final Widget widget) {
		final Element li = widget.getElement();

		if ("LI".equals(li.getTagName())) {
			super.add(widget, getElement());
		}
		else {
			throw new IllegalArgumentException("'widget' must have a LI element.");
		}
	}

}

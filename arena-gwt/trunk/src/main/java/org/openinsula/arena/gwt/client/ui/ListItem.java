package org.openinsula.arena.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents the tag &lt;li&gt;.
 * 
 * @author Lucas K Mogari
 */
public class ListItem extends ComplexPanel {

	public ListItem() {
		setElement(DOM.createElement("li"));
	}

	@Override
	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

}

package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents the tag &lt;ul&gt;.
 * 
 * @author Lucas K Mogari
 */
public class UnorderedList extends ComplexPanel {

	public UnorderedList() {
		setElement(DOM.createElement("ul"));
	}

	public void add(ListItem listItem) {
		super.add(listItem, getElement());
	}

	public void insert(ListItem listItem, int beforeIndex) {
		super.insert(listItem, getElement(), beforeIndex, true);
	}

	@Override
	@Deprecated
	public void add(Widget widget) {
		throw new UnsupportedOperationException("This widget accepts only a ListItem.");
	}

}

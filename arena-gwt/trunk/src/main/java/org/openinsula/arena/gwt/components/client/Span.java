package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents the tag &lt;span&gt;.
 * 
 * @author Lucas K Mogari
 */
public class Span extends ComplexPanel {

	public Span() {
		setElement(DOM.createSpan());
	}

	@Override
	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

}
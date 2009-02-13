package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link HTMLWidgetFactory#wrap(com.google.gwt.dom.client.Element)} instead.
 */
public class Strong extends Widget implements HasText {

	public Strong() {
		setElement(DOM.createElement("strong"));
	}
	
	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(final String text) {
		getElement().setInnerText(text);
	}

}

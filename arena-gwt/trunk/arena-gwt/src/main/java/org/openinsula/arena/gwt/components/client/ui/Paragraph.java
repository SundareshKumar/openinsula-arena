package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link HTMLWidgetFactory#p()} instead.
 */
public class Paragraph extends Widget implements HasText {

	public Paragraph() {
		this(null);
	}

	public Paragraph(final String text) {
		setElement(DOM.createElement("P"));

		if (text != null) {
			setText(text);
		}
	}

	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(final String text) {
		getElement().setInnerText(text);
	}

}

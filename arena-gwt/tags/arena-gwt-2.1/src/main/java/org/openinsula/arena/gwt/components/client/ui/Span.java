package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link HTMLWidgetFactory#span()} instead.
 */
public class Span extends ComplexPanel implements HasText {

	public Span() {
		setElement(DOM.createSpan());
	}

	@Override
	public void add(final Widget w) {
		super.add(w, getElement());
	}

	public void insert(final Widget w, final int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}

	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(final String text) {
		getElement().setInnerText(text);
	}

}
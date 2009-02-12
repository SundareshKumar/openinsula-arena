package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Label extends ComplexPanel implements HasText {

	private static final String FOR_ATTRIBUTE = "for";

	public Label(final String label, final String id) {
		setElement(DOM.createLabel());

		if (id != null && id.length() > 0) {
			setFor(id);
		}

		setText(label);
	}

	@Override
	public void add(final Widget w) {
		super.add(w, getElement());
	}

	public void insert(final Widget w, final int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}
	
	public String getFor() {
		return getElement().getAttribute(FOR_ATTRIBUTE);
	}

	public void setFor(final String id) {
		getElement().setAttribute(FOR_ATTRIBUTE, id);
	}

	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(final String text) {
		getElement().setInnerText(text);
	}

}

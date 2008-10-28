package org.openinsula.arena.gwt.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Label extends Widget implements HasText {

	private static final String FOR_ATTRIBUTE = "for";

	public Label() {
		this(null, null);
	}

	public Label(String label, String id) {
		setElement(DOM.createLabel());

		if (id != null && !id.trim().isEmpty()) {
			setForAttributeId(id);
		}

		if (label != null) {
			setText(label);
		}
	}

	public String getForAttributeId() {
		return getElement().getAttribute(FOR_ATTRIBUTE);
	}

	public void setForAttributeId(String id) {
		getElement().setAttribute(FOR_ATTRIBUTE, id);
	}

	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(String text) {
		getElement().setInnerText(text);
	}

}

package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Header extends Widget implements HasText {

	public Header(int type) {
		this(type, null);
	}

	public Header(int type, String text) {
		if (type < 1 || type > 5) {
			throw new IllegalArgumentException("'type' must be from 1 to 5 to create for example H1");
		}

		setElement(DOM.createElement("H" + type));

		if (text != null) {
			setText(text);
		}
	}

	public String getText() {
		return getElement().getInnerText();
	}

	public void setText(String text) {
		getElement().setInnerText(text);
	}

}

package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents the tag &lt;p&gt;.
 * 
 * @author Lucas K Mogari
 */
public class Paragraph extends Widget implements HasText {

	public Paragraph() {
		this(null);
	}

	public Paragraph(String text) {
		setElement(DOM.createElement("p"));

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

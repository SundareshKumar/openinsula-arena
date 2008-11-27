package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents the tag &lt;h1&gt; to &lt;h6&gt;.
 * 
 * @author Lucas K Mogari
 */
public class Header extends Widget implements HasText {

	public Header() {
		this(null);
	}

	public Header(String text) {
		this(text, 1);
	}

	public Header(String text, int size) {
		final String tagName = new StringBuilder("h").append(size).toString();

		setElement(DOM.createElement(tagName));

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

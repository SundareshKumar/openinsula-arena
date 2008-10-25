package org.openinsula.arena.gwt.client.user.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Create a {@link Widget} in this format:
 * 
 * <pre>
 * 	&lt;div class=&quot;Title&quot;&gt;
 * 		&lt;h2&gt;title&lt;/h2&gt;
 * 		&lt;p&gt;description&lt;/p&gt;
 * 	&lt;/div&gt;
 * </pre>
 * 
 * @author Lucas K Mogari
 */
public class Title extends Widget implements HasText {

	public static final String STYLE_CLASS_NAME = "Title";

	private final Element h2;

	private final Element p;

	public Title() {
		this(null, null);
	}

	public Title(String text) {
		this(text, null);
	}

	public Title(String text, String description) {
		final Element div = DOM.createDiv();
		h2 = DOM.createElement("h2");
		p = DOM.createElement("p");

		setElement(div);
		setStyleName(STYLE_CLASS_NAME);

		div.appendChild(h2);
		div.appendChild(p);

		setText(text);
		setDescription(description);
	}

	public String getText() {
		return h2.getInnerText();
	}

	public void setText(String text) {
		h2.setInnerText(text);
	}

	public String getDescription() {
		return p.getInnerText();
	}

	public void setDescription(String description) {
		p.setInnerText(description);
	}

}

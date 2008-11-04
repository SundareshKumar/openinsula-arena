package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.ui.FlowPanel;
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
public class Title extends FlowPanel implements HasText {

	public static final String STYLE_CLASS_NAME = "Title";

	private final Header h2;

	private final Paragraph p;

	public Title() {
		this(null, null);
	}

	public Title(String text) {
		this(text, null);
	}

	public Title(String text, String description) {
		h2 = new Header(text, 2);
		p = new Paragraph(description);

		setStyleName(STYLE_CLASS_NAME);

		add(h2);
		add(p);
	}

	public String getText() {
		return h2.getText();
	}

	public void setText(String text) {
		h2.setText(text);
	}

	public String getDescription() {
		return p.getText();
	}

	public void setDescription(String description) {
		p.setText(description);
	}

}

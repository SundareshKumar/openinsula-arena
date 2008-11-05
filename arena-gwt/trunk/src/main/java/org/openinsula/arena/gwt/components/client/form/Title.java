package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.Header;
import org.openinsula.arena.gwt.components.client.Paragraph;

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
public class Title extends FlowPanel implements HasText, FormItem {

	public static final String STYLE_CLASS_NAME = "Title";

	private final Header h2;

	private Paragraph p;

	public Title() {
		this(null, null);
	}

	public Title(String titleText) {
		this(titleText, null);
	}

	public Title(String titleText, String description) {
		h2 = new Header(titleText, 2);

		setStyleName(STYLE_CLASS_NAME);

		add(h2);

		if (description != null && description.length() > 0) {
			setDescription(description);
		}
	}

	public Widget asWidget() {
		return this;
	}

	public String getText() {
		return h2.getText();
	}

	public void setText(String titleText) {
		h2.setText(titleText);
	}

	public String getDescription() {
		return p != null ? p.getText() : "";
	}

	public void setDescription(String description) {
		if (p == null) {
			p = new Paragraph();

			add(p);
		}
		p.setText(description);
	}

}

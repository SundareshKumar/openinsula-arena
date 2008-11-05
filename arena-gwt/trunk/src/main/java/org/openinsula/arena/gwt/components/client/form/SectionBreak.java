package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.Header;
import org.openinsula.arena.gwt.components.client.ListItem;
import org.openinsula.arena.gwt.components.client.Paragraph;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates a {@link Widget} in this format:
 * 
 * <pre>
 * &lt;li class=&quot;section&quot;&gt;
 * 	&lt;h3&gt;label&lt;/h3&gt;
 * 	&lt;p&gt;description&lt;/p&gt;
 * &lt;/li&gt;
 * </pre>
 * 
 * @author Lucas K Mogari
 */
public class SectionBreak extends Composite implements FormItem, HasText {

	public static final String STYLE_CLASS_NAME = "Section";

	private final ListItem listItem = new ListItem();

	private final Header titleHeader;

	private Paragraph descriptionParagraph;

	public SectionBreak() {
		titleHeader = new Header(null, 3);

		initWidget(listItem);
		setStyleName(STYLE_CLASS_NAME);

		listItem.add(titleHeader);
	}

	public Widget asWidget() {
		return this;
	}

	public void setText(String text) {
		titleHeader.setText(text);
	}

	public String getText() {
		return titleHeader.getText();
	}

	public void setDescription(String description) {
		if (descriptionParagraph == null) {
			descriptionParagraph = new Paragraph(description);

			listItem.add(descriptionParagraph);
		}
		else {
			descriptionParagraph.setText(description);
		}
	}

}

package org.openinsula.arena.gwt.components.client.ui;

import org.openinsula.arena.gwt.components.client.ui.utils.AttributeUtils;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Suffix extends Composite implements HasText {

	public static final String LABEL_STYLE_NAME = "Suffix";

	private final Label label;

	private final Widget component;

	public Suffix(String suffix, Widget component) {
		if (suffix == null || component == null) {
			throw new IllegalArgumentException("'suffix' and 'component' must not be null.");
		}

		final Span span = new Span();
		final String id = AttributeUtils.getId(component);
		label = new Label(suffix, id);
		this.component = component;

		label.setStyleName(LABEL_STYLE_NAME);

		initWidget(span);

		span.add(component);
		span.add(label);
	}

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getComponent() {
		return (T) component;
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
	}

}

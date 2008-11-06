package org.openinsula.arena.gwt.components.client.form;

import org.openinsula.arena.gwt.components.client.ForLabel;
import org.openinsula.arena.gwt.components.client.Span;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Suffix extends Composite implements HasText {

	public static final String LABEL_STYLE_NAME = "Suffix";

	private final ForLabel forLabel;

	private final Widget component;

	public Suffix(String suffix, Widget component) {
		final Span span = new Span();
		final String id = getId(component);
		forLabel = new ForLabel(suffix, id);
		this.component = component;

		forLabel.setStyleName(LABEL_STYLE_NAME);

		initWidget(span);

		span.add(component);
		span.add(forLabel);
	}

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getComponent() {
		return (T) component;
	}

	public String getText() {
		return forLabel.getText();
	}

	public void setText(String text) {
		forLabel.setText(text);
	}

	private String getId(Widget widget) {
		return getId(widget.getElement());
	}

	private String getId(Element widgetElement) {
		String id = widgetElement.getAttribute("id");

		if (id == null || id.length() == 0) {
			id = DOM.createUniqueId();

			widgetElement.setAttribute("id", id);
		}

		return id;
	}

}

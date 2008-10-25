package org.openinsula.arena.gwt.client.form;

import org.openinsula.arena.gwt.client.user.ui.Label;
import org.openinsula.arena.gwt.client.user.ui.Span;

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

	private final Label label;

	private final Widget component;

	public Suffix(String suffix, Widget component) {
		final Span span = new Span();
		final String id = getId(component);
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

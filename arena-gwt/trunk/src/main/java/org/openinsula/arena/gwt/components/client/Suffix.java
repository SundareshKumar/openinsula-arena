package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class Suffix extends Composite implements HasText {

	public static final String LABEL_STYLE_NAME = "arena-Suffix";

	private final ForLabel forLabel;

	private final Widget suffixWidget;

	public Suffix(String suffix, Widget suffixWidget) {
		final Span span = new Span();
		final String id = getId(suffixWidget);
		forLabel = new ForLabel(suffix, id);
		this.suffixWidget = suffixWidget;

		forLabel.setStyleName(LABEL_STYLE_NAME);

		initWidget(span);

		span.add(suffixWidget);
		span.add(forLabel);
	}

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getAuffixWidget() {
		return (T) suffixWidget;
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

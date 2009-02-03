package org.openinsula.arena.gwt.components.client.ui.form.field;

import org.openinsula.arena.gwt.components.client.ui.Label;
import org.openinsula.arena.gwt.components.client.ui.utils.AttributeUtils;
import org.openinsula.arena.gwt.components.client.ui.utils.EqualsUtils;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LabeledSimpleField extends DefaultSimpleField implements RequiredField {

	private Label label;
	
	private RequiredFieldSupport requiredFieldSupport;
	
	public LabeledSimpleField() {
	}

	public LabeledSimpleField(final String label, final Widget widget) {
		super(widget);

		setLabel(label);
	}

	@Override
	public void setAttribute(final String name, final Object attribute) {
		final Object oldAttribute = getAttribute(name);

		if (EqualsUtils.isDifferent(oldAttribute, attribute)) {
			if (Field.LABEL_ATTRIBUTE.equals(name)) {
				setLabel((String) attribute);
			}
			else {
				super.setAttribute(name, attribute);
			}
		}
	}

	public void setLabel(final String label) {
		final Widget widget = getComponent();

		if (widget == null) {
			throw new NullPointerException("'component' must not be null.");
		}

		if (this.label == null) {
			final String id = AttributeUtils.findFirstFocusWidgetId(widget.getElement());
			this.label = new Label(label, id);

			this.label.setStyleName(LABEL_STYLE_NAME);

			insert(0, this.label);
			requiredFieldSupport = new RequiredFieldSupport(this.label);
		}

		this.label.setText(label);
	}

	public boolean isRequired() {
		if (label == null) {
			throw new IllegalStateException("Label component not ready! Try setLabel(String value) before this.");
		}
		return requiredFieldSupport.isRequired();
	}

	public void setRequired(final boolean value) {
		if (label == null) {
			throw new IllegalStateException("Label component not ready! Try setLabel(String value) before this.");
		}
		requiredFieldSupport.setRequired(value);
	}

}

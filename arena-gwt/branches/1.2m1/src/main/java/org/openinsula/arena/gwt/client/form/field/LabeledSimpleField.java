package org.openinsula.arena.gwt.client.form.field;

import org.openinsula.arena.gwt.client.form.DefaultSimpleField;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LabeledSimpleField extends DefaultSimpleField {

	private Label label;

	public LabeledSimpleField() {
	}

	public LabeledSimpleField(String label, Widget widget) {
		super(widget);

		setLabel(label);
	}

	@Override
	public void setAttribute(String name, Object attribute) {
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

	public void setLabel(String label) {
		final Widget widget = getComponent();

		if (widget == null) {
			throw new NullPointerException("'component' must not be null.");
		}

		if (this.label == null) {
			final String id = AttributeUtils.findFirstFocusWidgetId(widget.getElement());
			this.label = new Label(label, id);

			this.label.setStyleName(LABEL_STYLE_NAME);

			insert(0, this.label);
		}

		this.label.setText(label);
	}

}

package org.openinsula.arena.gwt.components.client.form.field;


import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LabeledSimpleField extends DefaultSimpleField {

	private ForLabel forLabel;

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
		final Widget widget = getFieldWidget();

		if (widget == null) {
			throw new NullPointerException("'component' must not be null.");
		}

		if (this.forLabel == null) {
			final String id = AttributeUtils.findFirstFocusWidgetId(widget.getElement());
			this.forLabel = new ForLabel(label, id);

			this.forLabel.setStyleName(LABEL_STYLE_NAME);

			insert(0, this.forLabel);
		}

		this.forLabel.setText(label);
	}

}

package org.openinsula.arena.gwt.components.client.form.field;

import org.openinsula.arena.gwt.components.client.form.validation.ValidationCallback;
import org.openinsula.arena.gwt.components.client.util.WidgetUtils;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultSimpleField extends ListItemField implements SimpleField {

	private Widget fieldWidget;

	public DefaultSimpleField() {
	}

	public DefaultSimpleField(Widget fieldWidget) {
		this(fieldWidget, null);
	}

	public DefaultSimpleField(Widget fieldWidget, String label) {
		super(label);

		setFieldWidget(fieldWidget);
	}

	public void setFieldWidget(Widget fieldWidget) {
		if (fieldWidget == null) {
			throw new IllegalArgumentException("'fieldWidget' must not be null.");
		}

		final Widget oldFieldWidget = this.fieldWidget;

		if (oldFieldWidget == null || !oldFieldWidget.equals(fieldWidget)) {
			this.fieldWidget = fieldWidget;

			if (oldFieldWidget != null) {
				remove(oldFieldWidget);
			}

			switch (getWidgetCount()) {
			case 0:
				add(fieldWidget);
				break;

			default:
				insert(fieldWidget, 1);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Widget> T getFieldWidget() {
		return (T) fieldWidget;
	}

	public <T> T getValue() {
		return WidgetUtils.<T> getValue(fieldWidget);
	}

	public void setValue(Object value) {
		WidgetUtils.setValue(fieldWidget, value);
	}

	public void validate(ValidationCallback callback) {
		getValidators().validate(this.<Object> getValue(), callback);
	}

}

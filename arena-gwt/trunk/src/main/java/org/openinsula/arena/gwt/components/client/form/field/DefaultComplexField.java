package org.openinsula.arena.gwt.components.client.form.field;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.form.validation.CompositeValidator;
import org.openinsula.arena.gwt.components.client.form.validation.ValidationCallback;
import org.openinsula.arena.gwt.components.client.form.validation.Validator;
import org.openinsula.arena.gwt.components.client.util.value.FieldUtils;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultComplexField extends ListItemField implements ComplexField {

	private List<Widget> widgets;

	private CompositeValidator widgetsValidator;

	public DefaultComplexField() {
	}

	public DefaultComplexField(String label) {
		this(label, new LinkedList<Widget>());
	}

	public DefaultComplexField(String label, Widget... widgets) {
		this(label, new LinkedList<Widget>()); // FIXME
	}

	public DefaultComplexField(String label, List<Widget> widgets) {
		super(label);

		this.widgets = widgets;
	}

	public List<Widget> getFieldWidgets() {
		return widgets;
	}

	public Widget getFieldWidget(int index) {
		return widgets.get(index);
	}

	public <T> T getValue(Widget fieldWidget) {
		return FieldUtils.<T> getValue(fieldWidget);
	}

	public void setValue(Widget fieldWidget, Object value) {
		FieldUtils.setValue(fieldWidget, value);
	}

	public void addFieldWidget(Widget fieldWidget) {
		if (fieldWidget == null) {
			throw new IllegalArgumentException("'fieldWidget' must not be null.");
		}

		final int widgetsCount = getWidgetCount();
		final int widgetsSize = widgets.size();

		if (widgetsSize == widgetsCount) {
			add(fieldWidget);
		}
		else {
			final int index = widgetsCount - widgetsSize;

			insert(fieldWidget, index);
		}
		widgets.add(fieldWidget);
	}

	public void removeFieldWidget(Widget fieldWidget) {
		remove(fieldWidget);

		widgets.remove(fieldWidget);
	}

	public void addValidator(Widget widget, Validator validator) {
		final int index = widgets.indexOf(widget);
		CompositeValidator widgetValidator = (CompositeValidator) widgetsValidator.get(index);

		if (widgetValidator == null) {
			widgetValidator = new CompositeValidator();

			widgetsValidator.set(index, widgetValidator);
		}
		widgetValidator.add(validator);
	}

	public void removeValidator(Widget widget, Validator validator) {
		final int index = widgets.indexOf(widget);
		final CompositeValidator widgetValidator = (CompositeValidator) widgetsValidator.get(index);

		if (widgetValidator != null) {
			widgetValidator.remove(validator);
		}
	}

	public void removeValidators(Widget widget) {
		final int index = widgets.indexOf(widget);

		widgetsValidator.remove(index);
	}

	public void validate(ValidationCallback callback) {
		// TODO
	}

}

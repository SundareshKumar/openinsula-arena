package org.openinsula.arena.gwt.components.client.ui.form.field;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openinsula.arena.gwt.components.client.ui.form.validation.ValidationResult;
import org.openinsula.arena.gwt.components.client.ui.form.validation.Validator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class LabeledCompositeField extends Composite implements CompositeField, RequiredField {

	private final LabeledSimpleField simpleField;

	private final Widget[] widgets;

	private final Map<Widget, List<Validator>> validators = new HashMap<Widget, List<Validator>>();

	public LabeledCompositeField(final String label, final Widget... widgets) {
		simpleField = new LabeledSimpleField(label, widgets[0]);
		this.widgets = widgets;

		initWidget(simpleField);

		for (int i = 1; i < widgets.length; i++) {
			simpleField.add(widgets[i]);
		}
	}

	public <T> T getAttribute(final String name) {
		return simpleField.<T> getAttribute(name);
	}

	public void setAttribute(final String name, final Object attribute) {
		simpleField.setAttribute(name, attribute);
	}

	public Widget toWidget() {
		return this;
	}

	public void addValidator(final Widget widget, final Validator... validators) {
		List<Validator> list = this.validators.get(widget);

		if (list == null) {
			list = new LinkedList<Validator>();

			this.validators.put(widget, list);
		}

		for (final Validator validator : validators) {
			list.add(validator);
		}
	}

	public void removeValidator(final Widget widget, final Validator validator) {
		final List<Validator> list = validators.get(widget);

		if (list != null) {
			list.remove(validator);
		}
	}

	public void removeValidators(final Widget widget) {
		validators.remove(widget);
	}

	public boolean isValid() {
		boolean valid = true;

		validationFor: for (final Entry<Widget, List<Validator>> entry : validators.entrySet()) {
			final Widget widget = entry.getKey();

			for (final Validator validator : entry.getValue()) {
				final Object value = FieldUtils.getValue(widget);
				final ValidationResult result = validator.validate(value);

				if (!result.isValid()) {
					valid = false;

					setAttribute(ERROR_MESSAGE_ATTRIBUTE, result.getMessage());
					break validationFor;
				}
			}
		}

		if (valid) {
			setAttribute(ERROR_MESSAGE_ATTRIBUTE, null);
		}

		return valid;
	}

	public Widget[] getComponents() {
		return widgets;
	}

	public boolean isRequired() {
		return simpleField.isRequired();
	}

	public void setRequired(final boolean value) {
		simpleField.setRequired(value);
	}

}

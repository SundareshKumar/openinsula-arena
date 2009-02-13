package org.openinsula.arena.gwt.form.client.validator;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.form.client.FormItem;

public class CompositeFormItemValidator implements AsyncValidator<FormItem> {

	private final List<FormItem> validatorList = new ArrayList<FormItem>();

	private int currentValidator = 0;

	public void addFormItemWithValidator(final FormItem formItem) {
		if (formItem.validator() != null) {
			validatorList.add(formItem);
		}
	}

	public void validate(final FormItem formItem, final ValidationCallback callback) {

		if (!validatorList.isEmpty()) {
			ValidationCallback compositeCallback = new ValidationCallback() {

				public void onSuccess(final String message) {
					if (currentValidator < validatorList.size()) {
						FormItem nextFormItem = validatorList.get(currentValidator++);
						nextFormItem.validate(this);
					}
					else {
						callback.onSuccess(message);
					}
				}

				public void onFail(final String message, final Throwable error) {
					callback.onFail(message, error);
				}
			};

			FormItem current = validatorList.get(currentValidator++);
			current.validate(compositeCallback);

		}
		else {
			callback.onSuccess(null);
		}

	}

}

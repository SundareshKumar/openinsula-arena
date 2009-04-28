package org.openinsula.arena.appengine.gwt.client.forms.validator;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator<T> implements AsyncValidator<T> {

	private final List<AsyncValidator<T>> validatorList = new ArrayList<AsyncValidator<T>>();

	private int currentValidator = 0;

	public CompositeValidator<T> addValidator(final AsyncValidator<T> validator) {
		if (validator != null) {
			validatorList.add(validator);
		}
		return this;
	}

	public final void validate(final T object, final ValidationCallback callback) {
		
		if (!validatorList.isEmpty()) {
			ValidationCallback compositeCallback = new ValidationCallback() {

				public void onSuccess(final String message) {
					if (currentValidator < validatorList.size()) {
						AsyncValidator<T> validator = validatorList.get(currentValidator++);
						validator.validate(object, this);
					}
					else {
						callback.onSuccess(message);
					}
				}

				public void onFail(final String message, final Throwable error) {
					callback.onFail(message, error);
				}
			};

			AsyncValidator<T> validator = validatorList.get(currentValidator++);
			validator.validate(object, compositeCallback);
		} else {
			callback.onSuccess(null);
		}
	}

}

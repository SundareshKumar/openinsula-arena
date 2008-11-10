package org.openinsula.arena.gwt.components.client.form.validation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
public class CompositeValidator implements Validator {

	private final List<Validator> validators = new LinkedList<Validator>();

	private CompositeValidationSupport support;

	public void validate(Object value, ValidationCallback callback) {
		if (support != null) {
			support.running = false;
		}

		support = new CompositeValidationSupport();

		support.validate(value, callback);
	}

	public void add(Validator validator) {
		validators.add(validator);
	}

	public void remove(Validator validator) {
		validators.remove(validator);
	}

	public void remove(int index) {
		validators.remove(index);
	}

	public void set(int index, Validator validator) {
		validators.set(index, validator);
	}

	public Validator get(int index) {
		return validators.get(index);
	}

	private final class CompositeValidationSupport implements ValidationCallback {

		private ValidationCallback callback;

		private List<Validator> usedValidators;

		private boolean running;

		public void validate(Object value, ValidationCallback callback) {
			this.callback = callback;
			running = true;
			usedValidators = new LinkedList<Validator>(validators);
			final Iterator<Validator> iterator = validators.iterator();

			while (running && iterator.hasNext()) {
				iterator.next().validate(value, this);
			}
		}

		public void onValueValidated(ValidationResult result) {
			if (!running) {
				return;
			}

			if (result.isValid()) {
				if (!usedValidators.remove(result.getValidator())) {
					throw new ValidationException();
				}

				if (usedValidators.isEmpty()) {
					result.setValidator(CompositeValidator.this);
					result.setMessage(null);

					notifyResult(result);
				}
			}
			else {
				notifyResult(result);
			}
		}

		private void notifyResult(ValidationResult result) {
			running = false;
			support = null;

			callback.onValueValidated(result);
		}

	}

}

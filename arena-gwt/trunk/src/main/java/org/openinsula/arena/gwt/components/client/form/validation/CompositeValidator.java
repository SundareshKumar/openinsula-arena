package org.openinsula.arena.gwt.components.client.form.validation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Lucas K Mogari
 */
public class CompositeValidator extends AbstractValidator {

	private final List<Validator> validators = new LinkedList<Validator>();

	private CompositeValidationSupport support;

	public CompositeValidator() {
	}

	public CompositeValidator(int timeout) {
		super(timeout);
	}

	@Override
	protected void doValidation(Object value, ValidationCallback callback) {
		if (support != null) {
			support.stop();
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

	@Override
	protected void stopValidation(ValidationCallback callback) {
		super.stopValidation(callback);

		support.stop();
	}

	private final class CompositeValidationSupport implements ValidationCallback {

		private ValidationCallback callback;

		private boolean running;

		private final Set<Validator> toUseValidators;

		public CompositeValidationSupport() {
			toUseValidators = new HashSet<Validator>(validators);
		}

		public void validate(Object value, ValidationCallback callback) {
			this.callback = callback;
			running = true;
			final Iterator<Validator> validatorIterator = new LinkedList<Validator>(validators).iterator();

			while (running && validatorIterator.hasNext()) {
				validatorIterator.next().validate(value, this);
			}
		}

		public void onValueValidated(ValidationResult result) {
			if (!running) {
				return;
			}

			final Validator validator = result.getValidator();

			if (validator == null) {
				throw new ValidationException("'validator' from result must not be null.");
			}
			else if (result.isValid()) {
				if (toUseValidators.remove(validator) && toUseValidators.isEmpty()) {
					result.setValidator(CompositeValidator.this);

					notifyResult(result);
				}
			}
			else {
				notifyResult(result);
			}
		}

		private void notifyResult(ValidationResult result) {
			if (!running) {
				return;
			}

			stop();

			callback.onValueValidated(result);
		}

		private void stop() {
			running = false;
		}

	}

}

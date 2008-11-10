package org.openinsula.arena.gwt.components.client.form.validation;

/**
 * @author Lucas K Mogari
 */
public abstract class SynchronousValidator implements Validator {

	private String message;

	protected abstract boolean isValid(Object value);

	public final void validate(Object value, ValidationCallback callback) {
		final boolean valid = isValid(value);
		final ValidationResult result = new ValidationResult(this, valid);

		if (!valid) {
			if (message == null) {
				throw new NullPointerException("'message' must not be null.");
			}
			result.setMessage(message);
		}

		callback.onValueValidated(result);
	}

	protected void setMessage(String message) {
		this.message = message;
	}

}

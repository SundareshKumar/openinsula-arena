package org.openinsula.arena.gwt.components.client.form.validation;

/**
 * @author Lucas K Mogari
 */
public class ValidationResult {

	private final Validator validator;

	private boolean valid;

	private String message;

	public ValidationResult(Validator validator) {
		this(validator, true, null);
	}

	public ValidationResult(Validator validator, boolean valid) {
		this(validator, valid, null);
	}

	public ValidationResult(Validator validator, boolean valid, String message) {
		this.validator = validator;
		this.valid = valid;
		this.message = message;
	}

	public Validator getValidator() {
		return validator;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

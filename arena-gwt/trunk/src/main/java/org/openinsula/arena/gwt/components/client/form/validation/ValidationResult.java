package org.openinsula.arena.gwt.components.client.form.validation;

/**
 * @author Lucas K Mogari
 */
public class ValidationResult {

	private Validator validator;

	private boolean valid;

	private String message;

	public ValidationResult() {
		this(null, true, null);
	}

	public ValidationResult(boolean valid) {
		this(null, valid, null);
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

	void setValidator(Validator validator) {
		this.validator = validator;
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

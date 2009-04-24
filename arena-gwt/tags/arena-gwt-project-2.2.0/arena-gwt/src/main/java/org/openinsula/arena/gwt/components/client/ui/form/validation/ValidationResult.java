package org.openinsula.arena.gwt.components.client.ui.form.validation;

/**
 * @author Lucas K Mogari
 */
public class ValidationResult {

	private boolean valid;

	private String message;

	public ValidationResult() {
		this(true, null);
	}

	public ValidationResult(boolean valid) {
		this(valid, null);
	}

	public ValidationResult(boolean valid, String message) {
		this.valid = valid;
		this.message = message;
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

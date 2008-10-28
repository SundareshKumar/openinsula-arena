package org.openinsula.arena.gwt.client.validation;

/**
 * @author Lucas K Mogari
 */
public class ValidationResult {

	private boolean valid = true;

	private String message;

	public ValidationResult() {
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

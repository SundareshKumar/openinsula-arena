package org.openinsula.arena.gwt.components.client.form.validation;

/**
 * @author Lucas K Mogari
 */
public class ValidationTimedOut extends ValidationResult {

	public ValidationTimedOut() {
		this(null);
	}

	public ValidationTimedOut(Validator validator) {
		super(validator, false, "");
	}

}

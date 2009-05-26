package org.openinsula.arena.gwt.components.client.ui.form.validation;

/**
 * @author Lucas K Mogari
 */
public final class ValidatorFactory {

	private static Validator requiredValidator;

	private ValidatorFactory() {
	}

	public static Validator createRequiredValidator() {
		if (requiredValidator == null) {
			requiredValidator = new RequiredValidator();
		}
		return requiredValidator;
	}

}

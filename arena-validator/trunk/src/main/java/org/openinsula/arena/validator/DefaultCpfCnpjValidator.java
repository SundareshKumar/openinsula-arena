package org.openinsula.arena.validator;

public class DefaultCpfCnpjValidator implements Validator {

	public boolean validate(String value) {
		value = StringUtils.getDigitsOnly(value);
		Validator validator = resolveValidator(value);
		return validator != null && validator.validate(value);
	}

	private Validator resolveValidator(String value) {
		Validator result = null;

		if (value.matches("\\d{11}")) {
			result = new DefaultCpfValidator();
		}
		else if (value.matches("\\d{14}")) {
			result = new DefaultCnpjValidator();
		}

		return result;
	}
}

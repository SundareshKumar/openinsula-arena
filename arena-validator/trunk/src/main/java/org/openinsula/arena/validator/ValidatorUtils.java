package org.openinsula.arena.validator;

final class ValidatorUtils {

	public static String getDigitsOnly(Object value) {
		return value == null ? "" : value.toString().replaceAll("[^0-9]", "");
	}

}

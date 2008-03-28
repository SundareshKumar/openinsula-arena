package org.openinsula.arena.validator;

import java.util.HashSet;
import java.util.Set;

public class DefaultPisPasepValidator implements Validator {
	private static Set<String> invalidValues = new HashSet<String>();

	static {
		invalidValues.add("00000000000");
	}

	public boolean validate(String value) {
		if (value == null) {
			return true;
		}

		String n = StringUtils.getDigitsOnly(value);
		if (n.length() != 11 || invalidValues.contains(value)) {
			return false;
		}

		int coeficient = 2;
		int sum = 0;
		int foundDv;

		for (int i = n.length() - 2; i >= 0; i--) {
			sum += Character.digit(n.charAt(i), 10) * coeficient;

			coeficient++;
			if (coeficient > 9) {
				coeficient = 2;
			}
		}

		foundDv = 11 - sum % 11;
		if (foundDv >= 10) {
			foundDv = 0;
		}

		return Character.digit(n.charAt(n.length() - 1), 10) == foundDv;
	}

}

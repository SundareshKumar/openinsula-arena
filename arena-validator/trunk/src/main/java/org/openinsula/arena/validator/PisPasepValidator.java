package org.openinsula.arena.validator;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.Validator;

public class PisPasepValidator implements Validator<PisPasep> {

	private static Set<String> invalidValues = new HashSet<String>();

	static {
		invalidValues.add("00000000000");
	}

	@Override
	public void initialize(PisPasep parameters) {
	}

	@Override
	public boolean isValid(Object value) {
		String s = ValidatorUtils.getDigitsOnly(value);

		if (s.length() != 11 || invalidValues.contains(value)) {
			return false;
		}

		int coeficient = 2;
		int sum = 0;
		int foundDv;

		for (int i = s.length() - 2; i >= 0; i--) {
			sum += Character.digit(s.charAt(i), 10) * coeficient;

			coeficient++;
			if (coeficient > 9) {
				coeficient = 2;
			}
		}

		foundDv = 11 - sum % 11;
		if (foundDv >= 10) {
			foundDv = 0;
		}

		return Character.digit(s.charAt(s.length() - 1), 10) == foundDv;
	}

}

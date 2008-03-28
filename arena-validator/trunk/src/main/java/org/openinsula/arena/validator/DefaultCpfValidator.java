package org.openinsula.arena.validator;

import java.util.HashSet;
import java.util.Set;

public class DefaultCpfValidator implements Validator {
	private static Set<String> invalidValues = new HashSet<String>();

	static {
		String zero = "";
		for (int i = 1; i <= 11; i++) {
			invalidValues.add(zero = (zero + "0"));
		}
		invalidValues.add("11111111111");
		invalidValues.add("22222222222");
		invalidValues.add("33333333333");
		invalidValues.add("44444444444");
		invalidValues.add("55555555555");
		invalidValues.add("66666666666");
		invalidValues.add("77777777777");
		invalidValues.add("88888888888");
		invalidValues.add("99999999999");
	}

	public boolean validate(String value) {
		value = StringUtils.getDigitsOnly(value);
		if (value.matches("\\d{11}") && !invalidValues.contains(value)) {
			char[] numbers = value.toCharArray();
			int soma = 0;

			for (int i = 0; i < 9; i++) {
				soma += Character.digit(numbers[i], 10) * (10 - i);
			}

			int resto = soma % 11;
			if (resto < 2) {
				if (Character.digit(numbers[9], 10) != 0) {
					return false;
				}
			}
			else {
				if (Character.digit(numbers[9], 10) != 11 - resto) {
					return false;
				}
			}

			soma = 0;
			for (int i = 0; i < 10; i++) {
				soma += Character.digit(numbers[i], 10) * (11 - i);
			}

			resto = soma % 11;
			if (resto < 2) {
				if (Character.digit(numbers[10], 10) != 0) {
					return false;
				}
			}
			else {
				if (Character.digit(numbers[10], 10) != 11 - resto) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}

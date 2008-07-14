package org.openinsula.arena.validator;

import org.hibernate.validator.Validator;

public class BacenPaisValidator implements Validator<BacenPais> {

	@Override
	public void initialize(BacenPais parameters) {
	}

	@Override
	public boolean isValid(Object value) {
		if (value != null) {
			String s = value.toString();
			if (s.matches("\\d{2,4}")) {
				int size = s.length();
				int digito = Character.digit(s.charAt(size - 1), 10);

				int sum = 0;
				for (int i = size - 2, j = 2; i >= 0; i--, j++) {
					sum += Character.digit(s.charAt(i), 10) * j;
				}

				int modulo = sum % 11;

				if (modulo == 0 || modulo == 1) {
					if (digito == 0) {
						return true;
					}
					else {
						return false;
					}
				}

				int verificador = (11 - modulo) % 10;
				if (verificador == digito) {
					return true;
				}
			}
		}
		return false;
	}
}

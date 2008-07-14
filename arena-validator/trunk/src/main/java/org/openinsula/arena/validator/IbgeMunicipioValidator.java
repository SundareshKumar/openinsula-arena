package org.openinsula.arena.validator;

import org.hibernate.validator.Validator;

public class IbgeMunicipioValidator extends AbstractIbgeValidator implements Validator<IbgeMunicipio> {

	@Override
	public void initialize(IbgeMunicipio parameters) {
	}

	@Override
	public boolean isValid(Object value) {
		if (value != null) {
			String s = value.toString();
			if (s.matches("\\d{7}")) {
				int size = s.length();
				int digito = Character.digit(s.charAt(size - 1), 10);

				int uf = Integer.valueOf(s.substring(0, 2));
				if (!ufSet.contains(uf)) {
					return false;
				}

				int sum = 0;
				for (int i = size - 2, j = 1; i >= 0; i--, j++) {
					int parcialSum = Character.digit(s.charAt(i), 10) * ((j % 2) + 1);
					sum += (parcialSum / 10) + (parcialSum % 10);
				}

				int modulo = sum % 10;

				int verificador = 0;
				if (modulo != 0) {
					verificador = 10 - modulo;
				}

				if (verificador == digito) {
					return true;
				}
			}
		}
		return false;
	}
}

package org.openinsula.arena.validator;

import org.hibernate.validator.Validator;

public class CpfOrCnpjValidator implements Validator<CpfOrCnpj> {
	private static final CpfValidator cpfValidator = new CpfValidator();

	private static final CnpjValidator cnpjValidator = new CnpjValidator();

	@Override
	public void initialize(CpfOrCnpj parameters) {
	}

	@Override
	public boolean isValid(Object value) {
		return cpfValidator.isValid(value) || cnpjValidator.isValid(value);
	}

}

package org.openinsula.arena.validator;

import org.hibernate.validator.Validator;

public class IbgeUfValidator extends AbstractIbgeValidator implements Validator<IbgeUf> {

	@Override
	public void initialize(IbgeUf parameters) {
	}

	@Override
	public boolean isValid(Object value) {
		if (value != null) {
			String s = value.toString();
			if (s.matches("\\d{2}")) {
				Integer intValue = Integer.valueOf(s);
				return ufSet.contains(intValue);
			}
		}
		return false;
	}
}

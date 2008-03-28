package org.openinsula.arena.validator;

public class DefaultEmailValidator implements Validator {
	public boolean validate(String value) {
		String[] parts = value.split("@");
		if (parts.length == 2 && checkLocalName(parts[0]) && checkDomain(parts[1])) {
			return true;
		}
		return false;
	}

	protected boolean checkLocalName(String value) {
		String label = "[\\w|\\.|\\-]+";
		return value.matches(label);
	}

	protected boolean checkDomain(String value) {
		String label = "(\\p{Alpha}([\\p{Alnum}|-]*\\p{Alnum})*)(.(\\p{Alpha}([\\p{Alnum}|-]*\\p{Alnum})*))*";
		return value.matches(label);
	}
}

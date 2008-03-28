package org.openinsula.arena.validator;

import java.text.SimpleDateFormat;

public class DefaultDateValidator implements Validator {
	private final SimpleDateFormat sdf;

	private final int dateLength;

	public DefaultDateValidator() {
		this("dd/MM/yyyy");
	}

	public DefaultDateValidator(String dateFormat) {
		sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		this.dateLength = dateFormat.length();
	}

	public boolean validate(String value) {
		if (value == null || dateLength != value.length()) {
			return false;
		}

		try {
			sdf.parse(value);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

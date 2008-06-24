package org.openinsula.arena.validator;

import java.text.SimpleDateFormat;

import org.hibernate.validator.Validator;

public class DateFormatValidator implements Validator<DateFormat> {

	private SimpleDateFormat sdf;

	private String format;

	@Override
	public void initialize(DateFormat parameters) {
		this.format = parameters.format();
		this.sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
	}

	@Override
	public boolean isValid(Object value) {
		if (value == null) {
			return false;
		}
		String s = value.toString();
		if (s.length() != format.length()) {
			return false;
		}

		try {
			sdf.parse(s);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}

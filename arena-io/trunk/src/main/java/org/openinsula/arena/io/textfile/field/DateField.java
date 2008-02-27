package org.openinsula.arena.io.textfile.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateField extends Field<Date> {
	private SimpleDateFormat sdf;

	private char fillCharacter = '0';

	public DateField(int size, String dateFormat) {
		this(size, dateFormat, "");
	}

	public DateField(int size, String dateFormat, String description) {
		super(size, description);
		sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
	}

	public DateField setFillCharacter(char c) {
		this.fillCharacter = c;

		return this;
	}

	@Override
	protected String doFormatValue(Date value) {
		if (value == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(fillCharacter);
			}
			return sb.toString();
		}
		else {
			return sdf.format(value);
		}
	}

	@Override
	public Date parseValue(String s) {
		String trim = s.trim();
		if ("".equals(trim) || trim.matches("0+")) {
			return null;
		}
		try {
			return sdf.parse(s);
		}
		catch (ParseException ex) {
			throw new IllegalArgumentException("Formato de data invalido.");
		}
	}

	@Override
	public boolean isLegalValue(Object value) {
		if (value == null || value instanceof Date) {
			return true;
		}
		else {
			throw new IllegalArgumentException("Illegal value for field. Expected java.util.Date, actual: "
					+ value.getClass());
		}
	}
}

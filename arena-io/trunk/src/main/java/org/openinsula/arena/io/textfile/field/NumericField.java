package org.openinsula.arena.io.textfile.field;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumericField extends Field<Number> {
	private DecimalFormat df;

	private String patternString;

	public NumericField(int size) {
		this(size, "");
	}

	public NumericField(int size, String description) {
		super(size, description);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append("0");
		}
		patternString = sb.toString();
		df = new DecimalFormat(patternString);
	}

	@Override
	public Number parseValue(String s) {
		String trim = s.trim();
		if ("".equals(trim)) {
			return null;
		}
		else if (!trim.matches("\\d+")) {
			throw new IllegalArgumentException("Formato numerico invalido.");
		}
		Long value = Long.valueOf(trim);
		if (value.compareTo(new Long(Integer.MAX_VALUE)) <= 0) {
			return value.intValue();
		}
		return value;
	}

	@Override
	protected String doFormatValue(Number value) {
		if (value == null) {
			return patternString;
		}
		else if (value instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) value;

			int scale = bigValue.scale();
			BigDecimal noPointValue = bigValue.movePointRight(scale);

			return df.format(noPointValue);
		}
		return df.format(value);
	}

	@Override
	public boolean isLegalValue(Object value) {
		if (value instanceof Number || value == null) {
			return true;
		}
		else {
			throw new IllegalArgumentException("Illegal value for field. Expected java.lang.Number, actual: "
					+ value.getClass());
		}
	}

}

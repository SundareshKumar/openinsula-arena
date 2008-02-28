/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
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

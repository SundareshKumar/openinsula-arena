/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.importer.file.field;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.openinsula.arena.io.textfile.field.Field;

public class NumeroTrechoField extends Field<Object> {
	private DecimalFormat df;

	private String patternString;

	public NumeroTrechoField(int size) {
		this(size, "");
	}

	public NumeroTrechoField(int size, String description) {
		super(size, description);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append("0");
		}
		patternString = sb.toString();
		df = new DecimalFormat(patternString);
	}

	@Override
	public Object parseValue(String s) {
		String trim = s.trim();
		if ("".equals(trim)) {
			return null;
		}
		else if (trim.matches("\\d+") || trim.matches("\\d{7},\\d{3}")) {
			Long value = Long.valueOf(trim.replace(",", ""));
			if (value.compareTo(new Long(Integer.MAX_VALUE)) <= 0) {
				return value.intValue();
			}
			return value;
		}
		else {
			return trim;
		}
	}

	@Override
	protected String doFormatValue(Object value) {
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
		if (value instanceof Number || value == null || value instanceof String) {
			return true;
		}
		else {
			throw new IllegalArgumentException(
					"Illegal value for field. Expected java.lang.Number or java.lang.String, actual: "
							+ value.getClass());
		}
	}

}

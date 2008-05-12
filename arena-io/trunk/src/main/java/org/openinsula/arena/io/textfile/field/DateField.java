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
			throw new IllegalArgumentException("Invalid Date Format.");
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

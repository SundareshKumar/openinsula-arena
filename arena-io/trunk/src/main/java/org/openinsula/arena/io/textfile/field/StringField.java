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

public class StringField extends Field<String> {
	private boolean padLeft;

	public StringField(int size) {
		this(size, false, "");
	}

	public StringField(int size, String description) {
		this(size, false, description);
	}

	public StringField(int size, boolean padLeft) {
		this(size, padLeft, "");
	}

	public StringField(int size, boolean padLeft, String description) {
		super(size, description);
		this.padLeft = padLeft;
	}

	@Override
	public String parseValue(String s) {
		String trim = s.trim();

		if ("".equals(trim)) {
			return null;
		}
		else {
			return trim;
		}
	}

	@Override
	protected String doFormatValue(String value) {
		String s = null;
		if (value == null) {
			s = "";
		}
		else {
			s = value;
		}

		if (s.length() >= size) {
			return s.substring(0, size);
		}
		else {
			int diff = size - s.length();

			StringBuilder pad = new StringBuilder(diff);
			for (int i = 0; i < diff; i++) {
				pad.append(" ");
			}

			StringBuilder sb = new StringBuilder();
			if (padLeft) {
				sb.append(pad.toString());
				sb.append(s);
			}
			else {
				sb.append(s);
				sb.append(pad.toString());
			}
			return sb.toString();
		}
	}

	@Override
	public boolean isLegalValue(Object value) {
		if (value instanceof String || value == null) {
			return true;
		}
		else {
			throw new IllegalArgumentException("Illegal value for field. Expected java.lang.String, actual: "
					+ value.getClass());
		}
	}

}

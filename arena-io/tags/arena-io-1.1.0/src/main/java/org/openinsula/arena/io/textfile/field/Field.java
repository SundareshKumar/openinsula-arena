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

public abstract class Field<T> {
	protected int size;

	protected String description;

	public Field(int size) {
		this(size, "");
	}

	public Field(int size, String description) {
		this.size = size;
		this.description = description;
	}

	public String formatValue(T value) {
		String s = doFormatValue(value);
		if (s.length() > size) {
			throw new IllegalArgumentException("Formatted value exceeded field size of " + size + " value: '" + s + "'");
		}
		else if (s.length() < size) {
			throw new IllegalArgumentException("Formatted value did not complete field size of " + size);
		}
		return s;
	}

	protected abstract String doFormatValue(T value);

	public abstract T parseValue(String s);

	public abstract boolean isLegalValue(Object value);

	public int getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}
}

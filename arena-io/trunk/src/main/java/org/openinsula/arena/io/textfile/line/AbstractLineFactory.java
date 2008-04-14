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
package org.openinsula.arena.io.textfile.line;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.Field;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public abstract class AbstractLineFactory implements LineFactory {
	protected boolean used = false;

	protected int totalSize = 0;

	protected boolean checkSize = true;

	protected List<Field<?>> fields = new LinkedList<Field<?>>();

	protected List<Integer> fieldStartIndexes = new LinkedList<Integer>();

	public void addField(Field<?> field) {
		if (used) {
			throw new IllegalStateException("Tentando adicionar um Field a uma LineFactory jah utilizada.");
		}
		else {
			fields.add(field);
			fieldStartIndexes.add(totalSize);
			totalSize += field.getSize();
		}
	}

	public void addNumericField(int size, String description) {
		addField(new NumericField(size, description));
	}

	public void addStringField(int size, String description) {
		addField(new StringField(size, description));
	}

	public void addDateField(int size, String dateFormat, String description) {
		addField(new DateField(size, dateFormat, description));
	}

	public Line createLine() {
		used = true;
		return new DefaultLine(this, fields, totalSize);
	}

	public boolean matches(String s) {
		if (s == null) {
			return false;
		}
		if (checkSize && s.length() != totalSize) {
			return false;
		}
		return doMatches(s);
	}

	protected abstract boolean doMatches(String s);

	protected String getFieldString(String s, int index) {
		final Field<?> field = fields.get(index);
		final int tokenStart = fieldStartIndexes.get(index);

		return s.substring(tokenStart, tokenStart + field.getSize());
	}

	public Line parseLine(String s) {
		used = true;

		if (s == null) {
			throw new IllegalArgumentException("Parsed NULL string");
		}
		else if (checkSize && s.length() != totalSize) {
			throw new IllegalStateException("Tamanho da linha incompativel. Esperado: " + totalSize + ", real: "
					+ s.length());
		}

		final Line line = new DefaultLine(this, fields, totalSize);

		for (int i = 0; i < fields.size(); i++) {
			try {
				final Field<?> field = fields.get(i);

				line.setValue(i, field.parseValue(getFieldString(s, i)));
			}
			catch (final Exception ex) {
				final int tokenStart = fieldStartIndexes.get(i);
				throw new IllegalArgumentException("Erro ao processar campo na posicao [" + (tokenStart + 1) + ","
						+ (tokenStart + fields.get(i).getSize()) + "]. " + ex.getMessage(), ex);
			}
		}

		return line;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public boolean isCheckSize() {
		return checkSize;
	}

	public void setCheckSize(boolean checkSize) {
		this.checkSize = checkSize;
	}
}

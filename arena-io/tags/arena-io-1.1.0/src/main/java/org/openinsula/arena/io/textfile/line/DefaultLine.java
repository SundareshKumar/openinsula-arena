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

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.io.textfile.field.Field;

public class DefaultLine implements Line {
	protected final Log logger = LogFactory.getLog(getClass());

	private LineFactory lineFactory;

	private List<Field<?>> fields;

	private int lineLength;

	private Map<Integer, Object> valueMap = new HashMap<Integer, Object>();

	protected DefaultLine(LineFactory lineFactory, List<Field<?>> fields, int lineLength) {
		this.lineFactory = lineFactory;
		this.fields = fields;
		this.lineLength = lineLength;
	}

	public void setValue(int index, Object value) {
		checkIndex(index);
		fields.get(index).isLegalValue(value);
		valueMap.put(index, value);
	}

	public Object getValue(int index) {
		return valueMap.get(index);
	}

	public Date getDateValue(int index) {
		return (Date) getValue(index);
	}

	public int getIntValue(int index) {
		try {
			return ((Number) getValue(index)).intValue();
		}
		catch (NullPointerException ex) {
			throw new IllegalArgumentException("Erro ao tentar obter valor inteiro de um campo com valor nulo.");
		}
	}

	public long getLongValue(int index) {
		return ((Number) getValue(index)).longValue();
	}

	public String getStringValue(int index) {
		return (String) getValue(index);
	}

	public BigDecimal getBigDecimalValue(int index) {
		return getBigDecimalValue(index, 2);
	}

	public BigDecimal getBigDecimalValue(int index, int scale) {
		return new BigDecimal(((Number) getValue(index)).longValue()).movePointLeft(scale);
	}

	protected void checkIndex(int index) {
		if (valueMap.containsKey(index)) {
			throw new IllegalStateException("Trying to set more than one value to index " + index);
		}
		else if (index < 0 || index > fields.size() - 1) {
			throw new IllegalArgumentException("No field found at index " + index);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.size(); i++) {
			Object value = valueMap.get(i);
			Field field = fields.get(i);

			sb.append(field.formatValue(value));
		}

		String line = sb.toString();
		if (line.length() != lineLength) {
			throw new IllegalStateException("Linha gerada com tamanho incompativel, esperado: " + lineLength
					+ ", real: " + line.length());
		}
		else {
			return line;
		}
	}

	public LineFactory getLineFactory() {
		return lineFactory;
	}

	public int getFieldsCount() {
		return fields.size();
	}

}

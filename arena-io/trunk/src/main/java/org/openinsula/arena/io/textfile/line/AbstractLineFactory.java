package org.openinsula.arena.io.textfile.line;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.io.textfile.field.Field;

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
		Field<?> field = fields.get(index);
		int tokenStart = fieldStartIndexes.get(index);

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

		Line line = new DefaultLine(this, fields, totalSize);

		for (int i = 0; i < fields.size(); i++) {
			try {
				Field<?> field = fields.get(i);

				line.setValue(i, field.parseValue(getFieldString(s, i)));
			}
			catch (Exception ex) {
				int tokenStart = fieldStartIndexes.get(i);
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

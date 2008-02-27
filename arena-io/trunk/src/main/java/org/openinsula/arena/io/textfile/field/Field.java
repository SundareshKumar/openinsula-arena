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

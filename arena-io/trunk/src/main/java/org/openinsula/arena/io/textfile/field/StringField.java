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

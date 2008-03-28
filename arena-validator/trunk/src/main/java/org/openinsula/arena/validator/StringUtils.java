package org.openinsula.arena.validator;

final class StringUtils {
	private static final String PLAIN_ASCII = "AaEeIiOoUu" + // grave
			"AaEeIiOoUuYy" + // acute
			"AaEeIiOoUuYy" + // circumflex
			"AaOo" + // tilde
			"AaEeIiOoUuYy" + // umlaut
			"Aa" + // ring
			"Cc"; // cedilla

	private static final String UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
			+ "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
			+ "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" + "\u00C3\u00E3\u00D5\u00F5"
			+ "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" + "\u00C5\u00E5"
			+ "\u00C7\u00E7";

	private StringUtils() {
	}

	public static String getDigitsOnly(String value) {
		return getDigitsOnly(value, (char) 0);
	}

	public static String getDigitsOnly(String value, char ignore) {
		return value == null ? "" : value.replaceAll(String.format("[^0-9%c]", ignore), "");
	}

	public static String getNonDigitsOnly(String value) {
		return value == null ? "" : value.replaceAll("[0-9]", "");
	}

	public static String getStringWithoutAccent(String value) {
		if (value == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);

			int pos = UNICODE.indexOf(c);
			if (pos > -1) {
				sb.append(PLAIN_ASCII.charAt(pos));
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String getInitialUpperCase(String value) {
		if (value == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			boolean capitalize = i == 0 || (Character.isLetter(c) && Character.isWhitespace(value.charAt(i - 1)));

			sb.append(capitalize ? Character.toUpperCase(c) : c);
		}
		return sb.toString();
	}

}

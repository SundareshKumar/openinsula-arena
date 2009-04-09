package org.openinsula.arena.lang.string;

import java.text.Normalizer;

public class StringUtils {

	private StringUtils() {
	}

	public static String getDigitsOnly(final String value) {
		if (value == null) {
			return "";
		}

		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			final char c = value.charAt(i);

			if (Character.isDigit(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String getNonDigitsOnly(final String value) {
		if (value == null) {
			return "";
		}

		final StringBuilder sb = new StringBuilder();

		for (int i = 0; i < value.length(); i++) {
			final char c = value.charAt(i);
			if (!Character.isDigit(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String getStringWithoutAccent(final String value) {
		if (value == null) {
			return "";
		}

		return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]","");
	}

	public static String getInitialUpperCase(final String value) {
		if (value == null) {
			return "";
		}

		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			if(i == 0 || value.substring(i - 1, i).equals(" ")) {
				sb.append(value.substring(i, i + 1).toUpperCase());
			} else {
				sb.append(value.substring(i, i + 1));
			}
		}
		return sb.toString();
	}

	/**
	 * Extrai as iniciais do nome informado.<br /> Uma inicial &eacute; identificada como a primeira letra ap&oacute;s um espa&ccedil;o.
	 * <br /><br />
	 * Exemplo: <code>getNameInitials("Zequinha Barbosa")</code> retorna <code>"ZB"</code>.
	 *
	 * @param name String de onde ser&atilde;o extra&iacute;das as iniciais dos nomes.
	 * @return String contendo as iniciais, sempre em letras mai&uacute;sculas.
	 *
	 * @author dalton
	 */
	public static String getNameInitials (final String name) {
		if (name == null) {
			return "";
		}

		final StringBuilder initials = new StringBuilder();

		for(int i = 0; i < name.length(); i++) {
			if ((i == 0 || name.charAt(i - 1) == ' ') && Character.isLetter(name.charAt(i))) {
				initials.append(Character.toUpperCase(name.charAt(i)));
			}
		}

		return initials.toString();
	}

	public static String getValidChars(final String value) {
		if (value == null) {
			return "";
		}
		return value.replaceAll("[^\\s\\w\\.,$@#()&:\"\'%]", "").trim();
	}

}

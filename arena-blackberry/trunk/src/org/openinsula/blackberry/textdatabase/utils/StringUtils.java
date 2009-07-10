package org.openinsula.blackberry.textdatabase.utils;

import java.util.Vector;

public abstract class StringUtils {

	/**
	 * Split the given String text in a String array divided by the char
	 * parameter 'c'.
	 * @param text
	 * @param c
	 * @return
	 */
	public static String[] split(final String text, char c) {
		int next = -1;
		String aux = text;

		Vector v = new Vector();

		while ((next = aux.indexOf(c)) > -1) {
			v.addElement(aux.substring(0, next));
			aux = aux.substring(next + 1);
		}

		v.addElement(aux);

		String[] s = new String[v.size()];

		for (int i = 0; i < v.size(); i++) {
			s[i] = (String) v.elementAt(i);
		}

		return s;
	}

}

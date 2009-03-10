package org.openinsula.arena.gwt.components.client.ui.utils;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link org.openinsula.arena.gwt.components.client.util.StringUtils} instead
 */
public abstract class StringUtils {

	
	/**
	 * @deprecated Use {@link org.openinsula.arena.gwt.components.client.util.StringUtils#hasText(String)} instead
	 */
	public static boolean isEmpty(final String string) {
		return string == null || string.trim().length() == 0;
	}

	/**
	 * @deprecated Use {@link org.openinsula.arena.gwt.components.client.util.StringUtils#hasText(String)} instead
	 */
	public static boolean isNotEmpty(final String string) {
		return !isEmpty(string);
	}

}

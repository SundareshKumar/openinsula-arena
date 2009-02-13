package org.openinsula.arena.gwt.components.client.ui.utils;

import org.openinsula.arena.gwt.components.client.util.ObjectUtils;

/**
 * @author Lucas K Mogari
 * @deprecated Use {@link ObjectUtils#nullSafeEquals(Object, Object)} instead.
 */
public abstract class EqualsUtils {

	public static boolean isDifferent(final Object s1, final Object s2) {
		return s1 == null && s2 != null || s1 != null && !s1.equals(s2);
	}

	public static boolean isIntegersDifferent(final String int1, final String int2) {
		if (int1 != null && int1.trim().length() > 0 && int2 != null && int2.trim().length() > 0) {
			return !Integer.valueOf(int1).equals(Integer.valueOf(int2));
		}
		return true;
	}

}

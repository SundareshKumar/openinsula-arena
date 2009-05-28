package org.openinsula.arena.gwt.components.client.ui.utils;

/**
 * @author Lucas K Mogari
 */
public abstract class EqualsUtils {

	public static boolean isDifferent(Object s1, Object s2) {
		return s1 == null && s2 != null || s1 != null && !s1.equals(s2);
	}

	public static boolean isIntegersDifferent(String int1, String int2) {
		try {
			if (int1 != null && int1.trim().length() > 0 && int2 != null
					&& int2.trim().length() > 0) {
				return !Integer.valueOf(int1).equals(Integer.valueOf(int2));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

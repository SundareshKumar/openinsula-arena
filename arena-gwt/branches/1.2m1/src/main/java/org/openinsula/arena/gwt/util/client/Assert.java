package org.openinsula.arena.gwt.util.client;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lucas K Mogari
 */
public abstract class Assert {

	public static void notNull(Object... objs) {
		for (final Object obj : objs) {
			notNull(obj, "object must not be null");
		}
	}

	public static void notNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isNull(Object... objs) {
		for (final Object obj : objs) {
			isNull(obj, "object must be null");
		}
	}

	public static void isNull(Object obj, String message) {
		if (obj != null) {
			throw new IllegalArgumentException(message);
		}
	}

	@SuppressWarnings("unchecked")
	public static void notEmpty(Object obj, String message) {
		boolean valid = true;

		if (obj == null) {
			valid = false;
		}
		else if (String.class == obj.getClass()) {
			valid = !obj.toString().isEmpty();
		}
		else if (Boolean.class == obj.getClass()) {
			valid = (Boolean) obj;
		}
		else {
			int length = 1;

			if (obj instanceof Collection) {
				length = ((Collection) obj).size();
			}
			else if (obj instanceof Map) {
				length = ((Map) obj).size();
			}

			valid = length > 0;
		}

		if (!valid) {
			throw new IllegalArgumentException(message);
		}
	}

}

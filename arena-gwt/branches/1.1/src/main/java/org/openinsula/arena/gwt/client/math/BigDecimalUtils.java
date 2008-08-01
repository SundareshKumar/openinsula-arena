package org.openinsula.arena.gwt.client.math;

import java.math.BigDecimal;

public abstract class BigDecimalUtils {

	public static final BigDecimal ZERO = new BigDecimal("0");

	public static boolean isZero(final BigDecimal value) {
		return nullSafeEquals(value, ZERO);
	}

	public static boolean nullSafeEquals(final BigDecimal o1, final BigDecimal o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}

		return o1.compareTo(o2) == 0;
	}

}

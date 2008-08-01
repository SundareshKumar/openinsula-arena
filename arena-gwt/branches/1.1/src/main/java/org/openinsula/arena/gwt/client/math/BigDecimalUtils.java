package org.openinsula.arena.gwt.client.math;

import java.math.BigDecimal;

public abstract class BigDecimalUtils {

	public static final BigDecimal ZERO = new BigDecimal("0");

	public static boolean isZero(final BigDecimal value) {
		return nullSafeEquals(value, ZERO);
	}

	public static boolean isNegative(final BigDecimal value) {
		if (value == null) {
			return false;
		}

		return value.compareTo(ZERO) < 0;
	}

	public static boolean isPositive(final BigDecimal value) {
		if (value == null) {
			return false;
		}

		return value.compareTo(ZERO) > 0;
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

	public static BigDecimal newBigDecimal(final String value) throws IllegalArgumentException, NumberFormatException {
		if (value == null) {
			throw new IllegalArgumentException("value must not be null");
		}

		String numbersAndDots = value.replaceAll("[^0-9.,]", "");

		int dotIndex = numbersAndDots.lastIndexOf('.');
		int commaIndex = numbersAndDots.lastIndexOf(',');

		int separatorIndex = dotIndex > commaIndex ? dotIndex : commaIndex;

		int scale = 0;

		if (separatorIndex != -1) {
			scale = numbersAndDots.length() - separatorIndex - 1;
		}

		String result = numbersAndDots.replaceAll("[^0-9]", "");

		if (scale > 0) {
			int length = result.length();
			separatorIndex = length - scale;

			result = new StringBuilder(length + scale)
			.append(result.substring(0, separatorIndex))
			.append('.')
			.append(result.substring(separatorIndex))
			.toString();
		}

		return new BigDecimal(result);
	}

}

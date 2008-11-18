package org.openinsula.arena.gwt.client.util;

import java.util.Date;

public class GwtDateUtils {

	private final static long MILLISECONDS_BY_SECOND = 1000;

	private final static long SECONDS_BY_MINUTE = 60;

	private final static long MINUTES_BY_HOUR = 60;

	private final static long HOUS_BY_DAY = 24;

	public enum DateField {
		HOUR,
		DATE;
	}

	public static Date add(Date baseDate, DateField field, int amount) {
		long time = baseDate.getTime();
		time = time + totalMillisecondsForField(field, amount);

		return new Date(time);
	}

	private static long totalMillisecondsForField(DateField field, int amount) {
		switch (field) {
		case HOUR :
			return amount * MILLISECONDS_BY_SECOND * SECONDS_BY_MINUTE * MINUTES_BY_HOUR;
		case DATE :
			return amount * MILLISECONDS_BY_SECOND * SECONDS_BY_MINUTE * MINUTES_BY_HOUR * HOUS_BY_DAY;
		default:
			throw new IllegalArgumentException("Invalid field " + field);
		}
	}

}

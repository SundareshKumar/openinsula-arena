/*
 *  (C) Copyright 2008 Insula T.I. Ltda.
 *
 *  This file is part of Arena Lang.
 *
 *  Arena Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang.time;

import java.util.Calendar;
import java.util.Date;

/**
 * A suite of utitilies that extends the ones provided by
 * {@link org.apache.commons.lang.time.DateUtils}.
 *
 * @author yanaga
 * @since 1.1
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private DateUtils() {
	}

	/**
	 * Calculates the difference in absolute days beetween two {@link Date} instances.
	 *
	 * <p>
	 * This method has built-in treatment for the one-hour difference provided by DST.
	 *
	 * @param begin The {@link Date} representing the initial date.
	 * @param end The {@link Date} representing the end date.
	 * @return The difference in days between the initial and end {@link Date}.
	 */
	public static long differenceInDays(final Date begin, final Date end) {
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);

		return differenceInDays(beginCalendar, endCalendar);
	}

	/**
	 * Calculates the difference in absolute days beetween two {@link Calendar} instances.
	 *
	 * <p>
	 * This method has built-in treatment for the one-hour difference provided by DST.
	 *
	 * @param begin The {@link Calendar} representing the initial date.
	 * @param end The {@link Calendar} representing the end date.
	 * @return The difference in days between the initial and end {@link Calendar}.
	 */
	public static long differenceInDays(final Calendar begin, final Calendar end) {
		Calendar endCalendar = lowerLimitForDay(end);
		Calendar beginCalendar = lowerLimitForDay(begin);

		long endMillis = endCalendar.getTimeInMillis()
				+ endCalendar.getTimeZone().getOffset(endCalendar.getTimeInMillis());
		long beginMillis = beginCalendar.getTimeInMillis()
				+ beginCalendar.getTimeZone().getOffset(beginCalendar.getTimeInMillis());

		return (endMillis - beginMillis) / MILLIS_PER_DAY;
	}

	/**
	 * Set all time related fields to its <b>minimum</b> values (00:00:00.000).
	 * <p>Calls {@link org.apache.commons.lang.time.DateUtils#truncate(Date, int)} using the
	 * {@link Calendar#DATE} field.
	 * @param date the date to work with
	 * @return the rounded date (a different object)
	 * @see
	 */
	public static Date lowerLimitForDay(final Date date) {
		return truncate(date, Calendar.DATE);
	}

	/**
	 * Set all time related fields to its <b>minimum</b> values (00:00:00.000).
	 * <p>Calls {@link org.apache.commons.lang.time.DateUtils#truncate(Calendar, int)} using the
	 * {@link Calendar#DATE} field.
	 * @param calendar the calendar to work with
	 * @return the rounded calendar (a different object)
	 */
	public static Calendar lowerLimitForDay(final Calendar calendar) {
		return truncate(calendar, Calendar.DATE);
	}

	/**
	 * Set all time related fields to its <b>maximum</b> values (23:59:59.999)
	 * @param date the date to work with
	 * @return the rounded date (a different object)
	 */
	public static Date upperLimitForDay(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return upperLimitForDay(calendar).getTime();
	}

	/**
	 * Set all time related fields to its <b>maximum</b> values (23:59:59.999)
	 * @param calendar the calendar to work with
	 * @return the rounded calendar (a different object)
	 */
	public static Calendar upperLimitForDay(final Calendar calendar) {
		Calendar result = (Calendar) calendar.clone();
		result.set(Calendar.HOUR_OF_DAY, 23);
		result.set(Calendar.MINUTE, 59);
		result.set(Calendar.SECOND, 59);
		result.set(Calendar.MILLISECOND, 999);

		return result;
	}

}

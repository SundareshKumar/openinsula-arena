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
		Calendar endCalendar = truncate(end, Calendar.DAY_OF_MONTH);
		Calendar beginCalendar = truncate(begin, Calendar.DAY_OF_MONTH);

		long endMillis = endCalendar.getTimeInMillis()
				+ endCalendar.getTimeZone().getOffset(endCalendar.getTimeInMillis());
		long beginMillis = beginCalendar.getTimeInMillis()
				+ beginCalendar.getTimeZone().getOffset(beginCalendar.getTimeInMillis());

		return (endMillis - beginMillis) / MILLIS_PER_DAY;
	}

}

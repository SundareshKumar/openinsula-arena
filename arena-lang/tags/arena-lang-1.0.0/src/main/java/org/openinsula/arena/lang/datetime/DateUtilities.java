/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena-Lang.
 *
 *  Arena-Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang.datetime;

import static java.util.Calendar.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Eduardo Rebola
 * 
 */
public abstract class DateUtilities {

	/**
	 * Change time related fields (HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND) to its
	 * maximum values.
	 * @param date 
	 * @return a copy of date parameter with time fields changed.
	 * @see DateUtilities#upperDateLimit(Calendar)
	 */
	public static Date upperDateLimit(final Date date) {
		return dateLimitImpl(date, true);
	}

	/**
	 * Change time related fields (HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND) to its
	 * maximum values.
	 * @param date 
	 * @return a copy of date parameter with time fields changed.
	 * @see DateUtilities#upperDateLimit(Date)
	 */
	public static Calendar upperDateLimit(final Calendar date) {
		return dateLimitImpl(date, true);
	}

	/**
	 * Change time related fields (HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND) to ZERO.
	 * @param date 
	 * @return a copy of date parameter with time fields changed.
	 * @see DateUtilities#lowerDateLimit(Calendar)
	 */
	public static Date lowerDateLimit(final Date date) {
		return dateLimitImpl(date, false);
	}

	/**
	 * Change time related fields (HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND) to ZERO.
	 * @param date 
	 * @return a copy of date parameter with time fields changed.
	 * @see DateUtilities#lowerDateLimit(Date)
	 */
	public static Calendar lowerDateLimit(final Calendar date) {
		return dateLimitImpl(date, false);
	}

	private static Date dateLimitImpl(final Date date, final boolean upper) {
		if (date == null) {
			return null;
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return applyValues(calendar, upper).getTime();
	}
	
	private static Calendar dateLimitImpl(final Calendar date, final boolean upper) {
		if (date == null) {
			return null;
		}

		return applyValues((Calendar) date.clone(), upper);
	}
	
	private static Calendar applyValues(final Calendar safeCopy, final boolean upper) {
		safeCopy.set(HOUR_OF_DAY, upper ? 23 : 0);
		safeCopy.set(MINUTE, upper ? 59 : 0);
		safeCopy.set(SECOND, upper ? 59 : 0);
		safeCopy.set(MILLISECOND, upper ? 999 : 0);

		return safeCopy;
	}

}

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

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateUtilsTestCase {

	// private void assertEquals(Date expected, Date actual) {
	// assertEquals(2005, calendar.get(Calendar.YEAR));
	// assertEquals(Calendar.JULY, calendar.get(Calendar.MONTH));
	// assertEquals(27, calendar.get(Calendar.DAY_OF_MONTH));
	// assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
	// assertEquals(0, calendar.get(Calendar.MINUTE));
	// assertEquals(0, calendar.get(Calendar.SECOND));
	// assertEquals(0, calendar.get(Calendar.MILLISECOND));
	// }
	//
	// private void assertEquals(Calendar expected, Calendar actual) {
	// assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
	// assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
	// assertEquals(expected.get(Calendar.DAY_OF_MONTH),
	// actual.get(Calendar.DAY_OF_MONTH));
	// assertEquals(expected.get(Calendar.HOUR_OF_DAY),
	// actual.get(Calendar.HOUR_OF_DAY));
	// assertEquals(expected.get(Calendar.MINUTE), actual.get(Calendar.MINUTE));
	// assertEquals(expected.get(Calendar.DAY_OF_MONTH),
	// actual.get(Calendar.SECOND));
	// assertEquals(expected.get(Calendar.MILLISECOND),
	// actual.get(Calendar.MILLISECOND));
	// }

	@Test
	public void testDifferenceInDays() {
		Calendar start = new GregorianCalendar(2004, Calendar.APRIL, 1, 6, 0, 0);
		Calendar end = Calendar.getInstance();

		end.set(2004, Calendar.APRIL, 2, 6, 0, 0);
		assertEquals(1, DateUtils.differenceInDays(start, end));

		end.set(2004, Calendar.APRIL, 3, 6, 0, 0);
		assertEquals(2, DateUtils.differenceInDays(start, end));

		end.set(2004, Calendar.APRIL, 4, 6, 12, 0);
		assertEquals(3, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13);
		end = new GregorianCalendar(2007, 9, 11);
		assertEquals(-2, DateUtils.differenceInDays(start, end));
		assertEquals(-2, DateUtils.differenceInDays(start.getTime(), end.getTime()));

		start = new GregorianCalendar(2007, 9, 13, 14, 33, 02);
		end = new GregorianCalendar(2007, 9, 11, 01, 32, 33);
		assertEquals(-2, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13);
		end = new GregorianCalendar(2007, 9, 12);
		assertEquals(-1, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13, 0, 0, 0);
		end = new GregorianCalendar(2007, 9, 12, 23, 0);
		assertEquals(-1, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13);
		end = new GregorianCalendar(2007, 9, 13);
		assertEquals(0, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13);
		end = new GregorianCalendar(2007, 9, 14);
		assertEquals(1, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13, 0, 0, 0);
		end = new GregorianCalendar(2007, 9, 14, 23, 59, 59);
		assertEquals(1, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, 9, 13);
		end = new GregorianCalendar(2007, 9, 15);
		assertEquals(2, DateUtils.differenceInDays(start, end));

		// testing time-saving in Brazil

		start = new GregorianCalendar(2007, Calendar.OCTOBER, 22);
		end = new GregorianCalendar(2007, Calendar.NOVEMBER, 16);
		assertEquals(25, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, Calendar.OCTOBER, 21);
		end = new GregorianCalendar(2007, Calendar.NOVEMBER, 16);
		assertEquals(26, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(2007, Calendar.OCTOBER, 20);
		end = new GregorianCalendar(2007, Calendar.NOVEMBER, 16);
		assertEquals(27, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
		end = new GregorianCalendar(2008, Calendar.MAY, 7);
		assertEquals(3865, DateUtils.differenceInDays(start, end));

		end = new GregorianCalendar(2008, Calendar.FEBRUARY, 10);
		assertEquals(3778, DateUtils.differenceInDays(start, end));

		end = new GregorianCalendar(2007, Calendar.DECEMBER, 10);
		assertEquals(3716, DateUtils.differenceInDays(start, end));
	}

	@Test
	public void testLowerLimitForDay() {
		Date timeSaving = new GregorianCalendar(2007, Calendar.OCTOBER, 14, 23, 59, 59).getTime();
		Date expected = new GregorianCalendar(2007, Calendar.OCTOBER, 14).getTime();
		assertEquals(expected, DateUtils.lowerLimitForDay(timeSaving));

		timeSaving = new GregorianCalendar(2008, Calendar.FEBRUARY, 16, 23, 59, 59).getTime();
		expected = new GregorianCalendar(2008, Calendar.FEBRUARY, 16).getTime();
		assertEquals(expected, DateUtils.lowerLimitForDay(timeSaving));
	}

	@Test
	public void testUpperLimitForDay() {
		Calendar timeSaving = new GregorianCalendar(2007, Calendar.OCTOBER, 14);
		Calendar expected = new GregorianCalendar(2007, Calendar.OCTOBER, 14, 23, 59, 59);
		expected.set(Calendar.MILLISECOND, 999);

		assertEquals(expected, DateUtils.upperLimitForDay(timeSaving));

		timeSaving = new GregorianCalendar(2008, Calendar.FEBRUARY, 16);
		expected = new GregorianCalendar(2008, Calendar.FEBRUARY, 16, 23, 59, 59);
		expected.set(Calendar.MILLISECOND, 999);

		assertEquals(expected, DateUtils.upperLimitForDay(timeSaving));
	}
}

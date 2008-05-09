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

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.lang.time.DateUtils;

public class DateUtilsTestCase {

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

		start = new GregorianCalendar(2007, GregorianCalendar.OCTOBER, 22);
		end = new GregorianCalendar(2007, GregorianCalendar.NOVEMBER, 16);
		assertEquals(25, DateUtils.differenceInDays(start, end));
		
		start = new GregorianCalendar(2007, GregorianCalendar.OCTOBER, 21);
		end = new GregorianCalendar(2007, GregorianCalendar.NOVEMBER, 16);
		assertEquals(26, DateUtils.differenceInDays(start, end));
		
		start = new GregorianCalendar(2007, GregorianCalendar.OCTOBER, 20);
		end = new GregorianCalendar(2007, GregorianCalendar.NOVEMBER, 16);
		assertEquals(27, DateUtils.differenceInDays(start, end));

		start = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
		end = new GregorianCalendar(2008, Calendar.MAY, 7);
		assertEquals(3865, DateUtils.differenceInDays(start, end));

		end = new GregorianCalendar(2008, Calendar.FEBRUARY, 10);
		assertEquals(3778, DateUtils.differenceInDays(start, end));

		end = new GregorianCalendar(2007, Calendar.DECEMBER, 10);
		assertEquals(3716, DateUtils.differenceInDays(start, end));
	}
}

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

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

/**
 * @author Eduardo Rebola
 *
 */
public class DateUtilitiesTest {

	@Test
	public void testUpperDateLimitWithDate() {
		// time fields are ZERO
		Calendar now = new GregorianCalendar(2008, Calendar.MARCH, 1);
		
		// time fields contains MAXIMUM values
		now.add(Calendar.MILLISECOND, -1);
		
		// time fields are ZERO
		Date before = DateBuilder.february(29, 2008);
		
		Date before_ = DateUtilities.upperDateLimit(before);
		
		assertNotSame(before, before_);
		assertFalse(before.equals(before_));
		
		assertEquals(before_, now.getTime());
	}

	@Test
	public void testUpperDateLimitWithCalendar() {
		// time fields are ZERO
		Calendar now = new GregorianCalendar(2008, Calendar.MARCH, 1);
		
		// time fields contains MAXIMUM values
		now.add(Calendar.MILLISECOND, -1);
		
		// time fields are ZERO
		Calendar before = new GregorianCalendar(2008, Calendar.FEBRUARY, 29);
		
		Calendar before_ = DateUtilities.upperDateLimit(before);
		
		assertNotSame(before, before_);
		assertFalse(before.equals(before_));
		
		assertEquals(before_, now);
	}

	@Test
	public void testLowerDateLimitWithDate() {
		Date now = DateBuilder.january(2, 2008);
		
		// time fields contains MAXIMUM values
		now = DateUtils.addMilliseconds(now, -1);

		// time fields are ZERO
		Date yesterday = DateBuilder.january(1, 2008);
		
		Date now_ = DateUtilities.lowerDateLimit(now);

		assertNotSame(now, now_);
		assertFalse(now.equals(now_));
		assertEquals(yesterday, now_);
	}

	@Test
	public void testLowerDateLimitWithCalendar() {
		Calendar now = new GregorianCalendar(2008, Calendar.JANUARY, 2);
		
		// time fields contains MAXIMUM values		
		now.add(Calendar.MILLISECOND, -1);
		
		// time fields are ZERO
		Calendar yesterday = new GregorianCalendar(2008, Calendar.JANUARY, 1);
		
		Calendar now_ = DateUtilities.lowerDateLimit(now);

		assertNotSame(now, now_);
		assertFalse(now.equals(now_));
		assertEquals(yesterday, now_);
	}

}

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
package org.openinsula.arena.lang.time;

import static java.util.Calendar.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateBuilderTestCase {

	private Date createDate(final int year, final int month, final int dayOfMonth) {
		return new GregorianCalendar(year, month, dayOfMonth).getTime();
	}

	private Date createDate(final int year, final int month, final int dayOfMonth, final int hour, final int minute, final int second) {
		return new GregorianCalendar(year, month, dayOfMonth, hour, minute, second).getTime();
	}

	@Test
	public void testJanuary() {
		Date actual = DateBuilder.january(1, 2008);
		Date expected = createDate(2008, JANUARY, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.january(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, JANUARY, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testFebruary() {
		Date actual = DateBuilder.february(1, 2008);
		Date expected = createDate(2008, FEBRUARY, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.february(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, FEBRUARY, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testMarch() {
		Date actual = DateBuilder.march(1, 2008);
		Date expected = createDate(2008, MARCH, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.march(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, MARCH, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testApril() {
		Date actual = DateBuilder.april(1, 2008);
		Date expected = createDate(2008, APRIL, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.april(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, APRIL, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testMay() {
		Date actual = DateBuilder.may(1, 2008);
		Date expected = createDate(2008, MAY, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.may(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, MAY, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testJune() {
		Date actual = DateBuilder.june(1, 2008);
		Date expected = createDate(2008, JUNE, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.june(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, JUNE, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testJuly() {
		Date actual = DateBuilder.july(1, 2008);
		Date expected = createDate(2008, JULY, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.july(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, JULY, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testAugust() {
		Date actual = DateBuilder.august(1, 2008);
		Date expected = createDate(2008, AUGUST, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.august(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, AUGUST, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testSeptember() {
		Date actual = DateBuilder.september(1, 2008);
		Date expected = createDate(2008, SEPTEMBER, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.september(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, SEPTEMBER, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testOctober() {
		Date actual = DateBuilder.october(1, 2008);
		Date expected = createDate(2008, OCTOBER, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.october(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, OCTOBER, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testNovember() {
		Date actual = DateBuilder.november(1, 2008);
		Date expected = createDate(2008, NOVEMBER, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.november(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, NOVEMBER, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

	@Test
	public void testDecember() {
		Date actual = DateBuilder.december(1, 2008);
		Date expected = createDate(2008, DECEMBER, 1);
		assertEquals(expected, actual);

		actual = DateBuilder.december(1, 2008, 23, 59, 59);
		assertFalse(expected.equals(actual));

		expected = createDate(2008, DECEMBER, 1, 23, 59, 59);
		assertEquals(expected, actual);
	}

}

/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena I/O.
 *
 *  Arena I/O is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena I/O is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena I/O.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.io.textfile.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class FieldTestCase {
	@Test
	public void testAlphanumericFormatValue() {
		StringField field = new StringField(5);
		assertEquals("a    ", field.formatValue("a"));
		assertEquals("ab   ", field.formatValue("ab"));
		assertEquals("abc  ", field.formatValue("abc"));
		assertEquals("abcd ", field.formatValue("abcd"));
		assertEquals("abcde", field.formatValue("abcde"));
		assertEquals("abcde", field.formatValue("abcdefg"));
		assertEquals("abcde", field.formatValue("abcdefghijklmn"));
		assertEquals("     ", field.formatValue(null));
		assertEquals("     ", field.formatValue(""));

		field = new StringField(5, true);
		assertEquals("    a", field.formatValue("a"));
		assertEquals("   ab", field.formatValue("ab"));
		assertEquals("  abc", field.formatValue("abc"));
		assertEquals(" abcd", field.formatValue("abcd"));
		assertEquals("abcde", field.formatValue("abcde"));
		assertEquals("abcde", field.formatValue("abcdefg"));
		assertEquals("abcde", field.formatValue("abcdefghijklmn"));
		assertEquals("     ", field.formatValue(null));
		assertEquals("     ", field.formatValue(""));
	}

	@Test
	public void testNumericFormatValue() {
		NumericField field = new NumericField(5);
		assertEquals("00000", field.formatValue(0));
		assertEquals("00000", field.formatValue(null));
		assertEquals("00001", field.formatValue(1));
		assertEquals("00123", field.formatValue(123L));
		assertEquals("12345", field.formatValue(12345));

		assertEquals("00123", field.formatValue(new BigDecimal("1.23")));
		assertEquals("00123", field.formatValue(new BigDecimal("123")));
		assertEquals("00123", field.formatValue(new BigDecimal("12.3")));
		assertEquals("01230", field.formatValue(new BigDecimal("123.0")));
		try {
			field.formatValue(123456);
			fail();
		}
		catch (Exception ex) {
		}

		try {
			field.formatValue(12345678L);
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testDateFormatValue() {
		DateField field = new DateField(8, "ddMMyyyy");

		assertEquals("09072006", field.formatValue(new GregorianCalendar(2006, Calendar.JULY, 9).getTime()));
		assertEquals("22122006", field.formatValue(new GregorianCalendar(2006, Calendar.DECEMBER, 22).getTime()));
		assertEquals("00000000", field.formatValue(null));

		field = new DateField(6, "ddMMyyyy");
		assertEquals("000000", field.formatValue(null));
		try {
			field.formatValue(new GregorianCalendar(2006, Calendar.JULY, 9).getTime());
			fail();
		}
		catch (Exception ex) {
		}

		field = new DateField(8, "ddMMyyyy").setFillCharacter(' ');
		assertEquals("        ", field.formatValue(null));
	}
}

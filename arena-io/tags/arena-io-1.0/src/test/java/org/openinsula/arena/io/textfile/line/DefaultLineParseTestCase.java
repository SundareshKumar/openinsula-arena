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
package org.openinsula.arena.io.textfile.line;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class DefaultLineParseTestCase {
	@Test
	public void testLineParseOk() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(2));

		Line line = lineFactory.parseLine("12345          0907200602");
		assertEquals(12345, line.getValue(0));
		assertNull(line.getValue(1));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 9).getTime(), line.getValue(2));
		assertEquals(2, line.getValue(3));

		line = lineFactory.parseLine("00321EDSON     0000000044");
		assertEquals(321, line.getValue(0));
		assertEquals("EDSON", line.getValue(1));
		assertNull(line.getValue(2));
		assertEquals(44, line.getValue(3));

		line = lineFactory.parseLine("00321EDSON     0000000044");
		assertEquals(321, line.getValue(0));
		assertEquals("EDSON", line.getValue(1));
		assertNull(line.getValue(2));
		assertEquals(44, line.getValue(3));

		line = lineFactory.parseLine("00000ED  Y     0000000000");
		assertEquals(0, line.getValue(0));
		assertEquals("ED  Y", line.getValue(1));
		assertNull(line.getValue(2));
		assertEquals(0, line.getValue(3));
	}

	@Test
	public void testWrongStringSize() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(2));

		try {
			lineFactory.parseLine("a2345          0907200602aasdfa");
			fail();
		}
		catch (Exception ex) {
		}

		try {
			lineFactory.parseLine(null);
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testLineParseFailure() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(2));

		try {
			lineFactory.parseLine("a2345          0907200602");
			fail();
		}
		catch (Exception ex) {
		}

		try {
			lineFactory.parseLine("a2345          2525200602");
			fail();
		}
		catch (Exception ex) {
		}

		try {
			lineFactory.parseLine(" 2345          090720060b");
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testLineGetValue() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(20));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(6));

		Line line = lineFactory.createLine();

		line.setValue(0, 123123123L);
		line.setValue(1, "abc");
		line.setValue(2, new GregorianCalendar(1978, Calendar.DECEMBER, 4).getTime());
		line.setValue(3, 45);

		assertEquals(123123123L, line.getValue(0));
		assertEquals("abc", line.getValue(1));
		assertEquals(new GregorianCalendar(1978, Calendar.DECEMBER, 4).getTime(), line.getValue(2));
		assertEquals(45, line.getValue(3));

		assertEquals(123123123L, line.getLongValue(0));
		assertEquals("abc", line.getStringValue(1));
		assertEquals(new GregorianCalendar(1978, Calendar.DECEMBER, 4).getTime(), line.getDateValue(2));
		assertEquals(new BigDecimal("0.45"), line.getBigDecimalValue(3));
	}
}

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
package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoTLineFactoryTestCase {
	protected SegmentoTLineFactory lineFactory = new SegmentoTLineFactory();

	@Test
	public void testSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse1() {
		String s = "0010001300001T 060035220000000472298 11880543634095478   1055821-1       29102005000000000055833237028480502898-5                 0900000000000000000000000000000000000000000000000000000   000000000000000000000000000        110191188054     ";
		Line line = lineFactory.parseLine(s);

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(3, line.getValue(2));
		assertEquals(1, line.getValue(3));
		assertEquals("T", line.getValue(4));
		assertNull(line.getValue(5));
		assertEquals(6, line.getValue(6));
		assertEquals(352, line.getValue(7));
		assertEquals(2, line.getValue(8));
		assertEquals(47229, line.getValue(9));
		assertEquals(8, line.getValue(10));
		assertNull(line.getValue(11));
		assertEquals("11880543634095478", line.getValue(12));
		assertEquals(1, line.getValue(13));
		assertEquals("055821-1", line.getValue(14));
		assertEquals(new GregorianCalendar(2005, Calendar.OCTOBER, 29).getTime(), line.getValue(15));
	}

}

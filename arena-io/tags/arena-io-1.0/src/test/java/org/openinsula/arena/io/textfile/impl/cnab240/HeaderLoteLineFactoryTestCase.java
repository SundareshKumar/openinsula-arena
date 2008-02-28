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
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class HeaderLoteLineFactoryTestCase {
	private LineFactory lineFactory = new HeaderLoteLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "00100011T0100020 20034598500001400011880540014       0035220000000472298 COOPER ECON CRED MUTUO PEQ EMP                                                                                000000003110200500000000  000000000                      ";
		Line line = lineFactory.parseLine(s);

		assertTrue(lineFactory.matches(s));

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(1, line.getValue(2));
		assertEquals("T", line.getValue(3));
		assertEquals(1, line.getValue(4));
		assertEquals(00, line.getValue(5));
		assertEquals(20, line.getValue(6));
		assertNull(line.getValue(7));
		assertEquals(2, line.getValue(8));
		assertEquals(3459850000140L, line.getValue(9));
		assertEquals("0011880540014", line.getValue(10));
		assertEquals(352, line.getValue(11));
		assertEquals("2", line.getValue(12));
		assertEquals(47229, line.getValue(13));
		assertEquals("8", line.getValue(14));
		assertNull(line.getValue(15));
		assertEquals("COOPER ECON CRED MUTUO PEQ EMP", line.getValue(16));
		assertNull(line.getValue(17));
		assertNull(line.getValue(18));
	}

}

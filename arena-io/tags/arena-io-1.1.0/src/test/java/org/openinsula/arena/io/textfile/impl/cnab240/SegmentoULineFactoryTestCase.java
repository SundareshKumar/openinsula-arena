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

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoULineFactoryTestCase {
	protected SegmentoULineFactory lineFactory = new SegmentoULineFactory();

	@Test
	public void testSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse1() {
		String s = "0010001300002U 060000000000000000000000000000000000000000000000000000000000000000000000558330000000000558330000000000000000000000000000003110200501112005            000000000000000                              000                           ";
		Line line = lineFactory.parseLine(s);

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(3, line.getValue(2));
		assertEquals(2, line.getValue(3));
		assertEquals("U", line.getValue(4));
		assertNull(line.getValue(5));
		assertEquals(6, line.getValue(6));
		assertEquals(0, line.getValue(7));
		assertEquals(0, line.getValue(8));
		assertEquals(0, line.getValue(9));
		assertEquals(0, line.getValue(10));
		assertEquals(55833, line.getValue(11));
		assertEquals(55833, line.getValue(12));
		assertEquals(0, line.getValue(13));
		assertEquals(0, line.getValue(13));
	}

}

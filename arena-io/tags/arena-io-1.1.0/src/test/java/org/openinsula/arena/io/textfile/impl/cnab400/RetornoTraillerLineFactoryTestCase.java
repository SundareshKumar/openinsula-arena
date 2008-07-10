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
package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class RetornoTraillerLineFactoryTestCase {
	private AbstractLineFactory lineFactory = new RetornoTraillerLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(400, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "9201237          000000880000001806697000000642          00001000000253578000000104084000010000001020000000000000000000000000000000000000000000000000000000000000000000000000000000000000000                                                                                                                                                                              00000000000000000000000         000008";
		Line line = lineFactory.parseLine(s);

		assertEquals(9, line.getValue(0));
		assertEquals(2, line.getValue(1));
		assertEquals(1, line.getValue(2));
		assertEquals(237, line.getValue(3));
		assertEquals(88, line.getValue(5));
		assertEquals(18066970, line.getValue(6));
	}
}

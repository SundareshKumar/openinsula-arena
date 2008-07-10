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

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoRLineFactoryTestCase {
	protected SegmentoRLineFactory lineFactory = new SegmentoRLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testToString() {
		String s = "0010001300003R 01000000000000000000000000000000000000000000000000000000000000000000000000                                                                                          0000000000000000000000000000000000000000000000000000000000000";
		Line line = lineFactory.createLine();

		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 3);
		line.setValue(3, 3);
		line.setValue(4, "R");
		line.setValue(6, 1);
		assertEquals(s, line.toString());
	}
}

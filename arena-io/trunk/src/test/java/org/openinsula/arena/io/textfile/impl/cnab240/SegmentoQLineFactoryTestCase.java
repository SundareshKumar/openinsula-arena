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
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoQLineFactoryTestCase {
	private SegmentoQLineFactory lineFactory = new SegmentoQLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "0010001300002Q 012006273071000144DANIEL BORGES DOS SANTOS VESTUARIO      RUA JOAO AUDI, 53                                      18520000CERQUILHO      SP2097383772000191J I RIBEIRO E CIA LTDA                  0000000000000000000000012345678";

		assertTrue(lineFactory.doMatches(s));
	}

	@Test
	public void testToString() {
		String s = "0010001300002Q 012006273071000144DANIEL BORGES DOS SANTOS VESTUARIO      RUA JOAO AUDI, 53                                      18520000CERQUILHO      SP2097383772000191J I RIBEIRO E CIA LTDA                  0000000000000000000000012345678";
		Line line = lineFactory.createLine();

		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 3);
		line.setValue(3, 2);
		line.setValue(4, "Q");
		line.setValue(6, 1);
		line.setValue(7, 2);
		line.setValue(8, 6273071000144L);
		line.setValue(9, "DANIEL BORGES DOS SANTOS VESTUARIO");
		line.setValue(10, "RUA JOAO AUDI, 53");
		line.setValue(12, 18520);
		line.setValue(14, "CERQUILHO");
		line.setValue(15, "SP");
		line.setValue(16, 2);
		line.setValue(17, 97383772000191L);
		line.setValue(18, "J I RIBEIRO E CIA LTDA");
		line.setValue(19, 0);
		line.setValue(21, "12345678");

		assertEquals(s, line.toString());
	}
}

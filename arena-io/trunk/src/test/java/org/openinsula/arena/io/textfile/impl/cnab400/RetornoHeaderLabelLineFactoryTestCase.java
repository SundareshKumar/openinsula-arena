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

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class RetornoHeaderLabelLineFactoryTestCase {
	private AbstractLineFactory lineFactory = new RetornoHeaderLabelLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(400, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "02RETORNO01COBRANCA       00000000000000208082COOP DE ECONOMIA E CREDITO MUT237BRADESCO       0707060160000000642                                                                                                                                                                                                                                                                          100706         000001";
		Line line = lineFactory.parseLine(s);

		assertEquals(0, line.getValue(0));
		assertEquals(2, line.getValue(1));
		assertEquals("RETORNO", line.getValue(2));
		assertEquals(1, line.getValue(3));
		assertEquals("COBRANCA", line.getValue(4));
		assertEquals(208082, line.getValue(5));
		assertEquals("COOP DE ECONOMIA E CREDITO MUT", line.getValue(6));
		assertEquals(237, line.getValue(7));
		assertEquals("BRADESCO", line.getValue(8));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 7).getTime(), line.getValue(9));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 10).getTime(), line.getValue(13));
		assertEquals(1, line.getValue(15));
	}
}

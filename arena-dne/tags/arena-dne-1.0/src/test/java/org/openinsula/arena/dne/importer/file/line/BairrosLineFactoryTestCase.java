/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.importer.file.line;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class BairrosLineFactoryTestCase {
	private LineFactory lineFactory = new BairrosDadosLineFactory();

	@Test
	public void testMatches() {
		String s = "DAC00012400000016RIO BRANCO                                                              0028000000001ABRAHAO ALAB                                                            A ALAB                               ";
		assertTrue(lineFactory.matches(s));
	}

	@Test
	public void testParseLine() {
		String s = "DAC00012400000016RIO BRANCO                                                              0028000000001ABRAHAO ALAB                                                            A ALAB                               ";
		Line line = lineFactory.parseLine(s);
		assertNotNull(line);

		assertEquals(line.getValue(7), "ABRAHAO ALAB");
	}

}

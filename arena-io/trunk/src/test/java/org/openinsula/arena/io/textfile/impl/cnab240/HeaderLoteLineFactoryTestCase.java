package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.HeaderLoteLineFactory;
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

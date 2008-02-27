package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoULineFactory;
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

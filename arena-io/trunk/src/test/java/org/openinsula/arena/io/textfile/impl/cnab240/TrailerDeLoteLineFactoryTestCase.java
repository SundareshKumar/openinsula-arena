package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.TrailerDeLoteLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class TrailerDeLoteLineFactoryTestCase {

	protected TrailerDeLoteLineFactory lineFactory = new TrailerDeLoteLineFactory();

	@Test
	public void testSegmentoT() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "00100015         0000050000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000                                                                                                                     ";
		Line line = lineFactory.parseLine(s);

		assertTrue(lineFactory.doMatches(s));

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(5, line.getValue(2));
		assertNull(line.getValue(3));
		assertEquals(5, line.getValue(4));
		assertEquals(0, line.getValue(5));
		assertEquals(0, line.getValue(6));
		assertEquals(0, line.getValue(7));
		assertEquals(0, line.getValue(8));
		assertEquals(0, line.getValue(9));
		assertEquals(0, line.getValue(10));
		assertEquals(0, line.getValue(11));
		assertEquals(0, line.getValue(12));
		assertEquals(0, line.getValue(13));
		assertNull(line.getValue(14));

	}

	@Test
	public void testToString() {
		String s = "00100015         0000050000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000                                                                                                                     ";

		Line line = lineFactory.createLine();
		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 5);
		line.setValue(4, 5);

		assertEquals(s, line.toString());
	}
}

package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento88ECFLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento88ECFLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento88ECFLineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento88ECFLineFactory().doMatches("88ECF"));
	}
}

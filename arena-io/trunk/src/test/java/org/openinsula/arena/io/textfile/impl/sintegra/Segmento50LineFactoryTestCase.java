package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento50LineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento50LineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento50LineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento50LineFactory().doMatches("50"));
	}
}

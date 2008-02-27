package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento53LineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento53LineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento53LineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento53LineFactory().doMatches("53"));
	}
}

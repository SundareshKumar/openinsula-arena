package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento90LineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento90LineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento90LineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento90LineFactory().doMatches("90"));
	}
}

package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento11LineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento11LineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento11LineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento11LineFactory().doMatches("11"));
	}
}

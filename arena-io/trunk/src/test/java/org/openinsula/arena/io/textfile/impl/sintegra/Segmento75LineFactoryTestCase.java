package org.openinsula.arena.io.textfile.impl.sintegra;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.sintegra.Segmento75LineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class Segmento75LineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new Segmento75LineFactory();
		assertEquals(126, lineFactory.getTotalSize());
	}

	@Test
	public void testDoMatch() {
		assertTrue(new Segmento75LineFactory().doMatches("75"));
	}
}

package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoSTipoImpressao1ou2LineFactory;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoSTipoImpressao3LineFactory;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class SegmentoSLineFactoryTestCase {
	@Test
	public void testTipoImpressao1ou2() {
		LineFactory lineFactory = new SegmentoSTipoImpressao1ou2LineFactory();
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testTipoImpressao3() {
		LineFactory lineFactory = new SegmentoSTipoImpressao3LineFactory();
		assertEquals(240, lineFactory.getTotalSize());
	}
}

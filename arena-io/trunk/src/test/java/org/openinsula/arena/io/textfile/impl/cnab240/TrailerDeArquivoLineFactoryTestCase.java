package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.TrailerDeArquivoLineFactory;

public class TrailerDeArquivoLineFactoryTestCase {
	@Test
	public void testSegmentoT() {
		TrailerDeArquivoLineFactory lineFactory = new TrailerDeArquivoLineFactory();
		assertEquals(240, lineFactory.getTotalSize());
	}
}

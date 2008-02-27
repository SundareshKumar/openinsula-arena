package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RetornoTransacaoRateioDeCreditoLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RetornoTransacaoRateioDeCreditoLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RetornoTransacaoRateioDeCreditoLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

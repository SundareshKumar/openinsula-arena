package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RemessaTransacaoRateioDeCreditoLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoRateioDeCreditoLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RemessaTransacaoRateioDeCreditoLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RemessaTransacaoDetalheLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoDetalheLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RemessaTransacaoDetalheLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

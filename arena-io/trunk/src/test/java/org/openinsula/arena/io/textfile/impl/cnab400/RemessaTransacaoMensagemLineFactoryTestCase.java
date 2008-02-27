package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RemessaTransacaoMensagemLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTransacaoMensagemLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RemessaTransacaoMensagemLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

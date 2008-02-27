package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RemessaTraillerLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaTraillerLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RemessaTraillerLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

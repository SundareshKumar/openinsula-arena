package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RemessaHeaderLabelLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;

public class RemessaHeaderLabelLineFactoryTestCase {
	@Test
	public void testLineSize() {
		AbstractLineFactory lineFactory = new RemessaHeaderLabelLineFactory();
		assertEquals(400, lineFactory.getTotalSize());
	}
}

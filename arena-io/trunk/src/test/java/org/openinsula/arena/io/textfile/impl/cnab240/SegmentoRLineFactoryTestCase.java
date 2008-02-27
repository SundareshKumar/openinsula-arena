package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoRLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoRLineFactoryTestCase {
	protected SegmentoRLineFactory lineFactory = new SegmentoRLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testToString() {
		String s = "0010001300003R 01000000000000000000000000000000000000000000000000000000000000000000000000                                                                                          0000000000000000000000000000000000000000000000000000000000000";
		Line line = lineFactory.createLine();

		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 3);
		line.setValue(3, 3);
		line.setValue(4, "R");
		line.setValue(6, 1);
		assertEquals(s, line.toString());
	}
}

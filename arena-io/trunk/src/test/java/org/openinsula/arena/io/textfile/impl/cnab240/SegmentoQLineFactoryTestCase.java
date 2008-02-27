package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoQLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoQLineFactoryTestCase {
	private SegmentoQLineFactory lineFactory = new SegmentoQLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "0010001300002Q 012006273071000144DANIEL BORGES DOS SANTOS VESTUARIO      RUA JOAO AUDI, 53                                      18520000CERQUILHO      SP2097383772000191J I RIBEIRO E CIA LTDA                  0000000000000000000000012345678";

		assertTrue(lineFactory.doMatches(s));
	}

	@Test
	public void testToString() {
		String s = "0010001300002Q 012006273071000144DANIEL BORGES DOS SANTOS VESTUARIO      RUA JOAO AUDI, 53                                      18520000CERQUILHO      SP2097383772000191J I RIBEIRO E CIA LTDA                  0000000000000000000000012345678";
		Line line = lineFactory.createLine();

		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 3);
		line.setValue(3, 2);
		line.setValue(4, "Q");
		line.setValue(6, 1);
		line.setValue(7, 2);
		line.setValue(8, 6273071000144L);
		line.setValue(9, "DANIEL BORGES DOS SANTOS VESTUARIO");
		line.setValue(10, "RUA JOAO AUDI, 53");
		line.setValue(12, 18520);
		line.setValue(14, "CERQUILHO");
		line.setValue(15, "SP");
		line.setValue(16, 2);
		line.setValue(17, 97383772000191L);
		line.setValue(18, "J I RIBEIRO E CIA LTDA");
		line.setValue(19, 0);
		line.setValue(21, "12345678");

		assertEquals(s, line.toString());
	}
}

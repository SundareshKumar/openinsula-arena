package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RetornoHeaderLabelLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class RetornoHeaderLabelLineFactoryTestCase {
	private AbstractLineFactory lineFactory = new RetornoHeaderLabelLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(400, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "02RETORNO01COBRANCA       00000000000000208082COOP DE ECONOMIA E CREDITO MUT237BRADESCO       0707060160000000642                                                                                                                                                                                                                                                                          100706         000001";
		Line line = lineFactory.parseLine(s);

		assertEquals(0, line.getValue(0));
		assertEquals(2, line.getValue(1));
		assertEquals("RETORNO", line.getValue(2));
		assertEquals(1, line.getValue(3));
		assertEquals("COBRANCA", line.getValue(4));
		assertEquals(208082, line.getValue(5));
		assertEquals("COOP DE ECONOMIA E CREDITO MUT", line.getValue(6));
		assertEquals(237, line.getValue(7));
		assertEquals("BRADESCO", line.getValue(8));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 7).getTime(), line.getValue(9));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 10).getTime(), line.getValue(13));
		assertEquals(1, line.getValue(15));
	}
}

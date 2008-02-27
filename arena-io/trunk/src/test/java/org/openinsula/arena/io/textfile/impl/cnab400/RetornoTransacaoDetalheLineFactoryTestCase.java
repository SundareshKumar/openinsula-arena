package org.openinsula.arena.io.textfile.impl.cnab400;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab400.RetornoTransacaoDetalheLineFactory;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class RetornoTransacaoDetalheLineFactoryTestCase {
	private AbstractLineFactory lineFactory = new RetornoTransacaoDetalheLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(400, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "1020487639300015200000090350901634127434320060707001505       000000000601000000620000000000000000000000000902070706387       00000000060100000062280706000000025357823704438  000000000018500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000                          0000000000                                                                  000002";
		Line line = lineFactory.parseLine(s);

		assertEquals(1, line.getValue(0));
		assertEquals(2, line.getValue(1));
		assertEquals(4876393000152L, line.getValue(2));
		assertEquals("00090350901634127", line.getValue(4));
		assertEquals("434320060707001505", line.getValue(5));
		assertEquals("060100000062", line.getValue(7));
		assertEquals(9, line.getValue(12));
		assertEquals(2, line.getValue(13));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 7).getTime(), line.getValue(14));
		assertEquals("387", line.getValue(15));
		assertEquals("00000000060100000062", line.getValue(16));
		assertEquals(new GregorianCalendar(2006, Calendar.JULY, 28).getTime(), line.getValue(17));
	}
}

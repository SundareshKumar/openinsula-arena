package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.HeaderArquivoLineFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class HeaderArquivoLineFactoryTestCase {
	private LineFactory lineFactory = new HeaderArquivoLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "00100000         2034598500001400011880540014       0035220000000472298 COOPER ECON CRED MUTUO PEQ EMPBANCO DO BRASIL                         23110200503043100005403000000031676284PROCESSAMEN                                  000            ";
		Line line = lineFactory.parseLine(s);

		assertTrue(lineFactory.matches(s));

		assertEquals(1, line.getValue(0));
		assertEquals(0000, line.getValue(1));
		assertEquals(0, line.getValue(2));
		assertNull(line.getValue(3));
		assertEquals(2, line.getValue(4));
		assertEquals(3459850000140L, line.getValue(5));
		assertEquals("0011880540014", line.getValue(6));
		assertEquals(352, line.getValue(7));
		assertEquals("2", line.getValue(8));
		assertEquals(47229, line.getValue(9));
		assertEquals("8", line.getValue(10));
		assertNull(line.getValue(11));
		assertEquals("COOPER ECON CRED MUTUO PEQ EMP", line.getValue(12));
		assertEquals("BANCO DO BRASIL", line.getValue(13));
		assertNull(line.getValue(14));
		assertEquals(2, line.getValue(15));
		assertEquals(new GregorianCalendar(2005, Calendar.OCTOBER, 31, 3, 4, 31).getTime(), line.getValue(16));
		assertEquals(54, line.getValue(17));
		assertEquals(30, line.getValue(18));
		assertEquals(00000, line.getValue(19));
		assertEquals("031676284PROCESSAMEN", line.getValue(20));
		assertNull(line.getValue(21));
		assertNull(line.getValue(22));
		assertNull(line.getValue(23));
		assertEquals(000, line.getValue(24));
		assertNull(line.getValue(25));
		assertNull(line.getValue(26));
	}
}

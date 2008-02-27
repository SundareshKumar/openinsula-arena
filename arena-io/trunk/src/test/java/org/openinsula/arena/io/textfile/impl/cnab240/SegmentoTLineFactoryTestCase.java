package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoTLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoTLineFactoryTestCase {
	protected SegmentoTLineFactory lineFactory = new SegmentoTLineFactory();

	@Test
	public void testSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse1() {
		String s = "0010001300001T 060035220000000472298 11880543634095478   1055821-1       29102005000000000055833237028480502898-5                 0900000000000000000000000000000000000000000000000000000   000000000000000000000000000        110191188054     ";
		Line line = lineFactory.parseLine(s);

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(3, line.getValue(2));
		assertEquals(1, line.getValue(3));
		assertEquals("T", line.getValue(4));
		assertNull(line.getValue(5));
		assertEquals(6, line.getValue(6));
		assertEquals(352, line.getValue(7));
		assertEquals(2, line.getValue(8));
		assertEquals(47229, line.getValue(9));
		assertEquals(8, line.getValue(10));
		assertNull(line.getValue(11));
		assertEquals("11880543634095478", line.getValue(12));
		assertEquals(1, line.getValue(13));
		assertEquals("055821-1", line.getValue(14));
		assertEquals(new GregorianCalendar(2005, Calendar.OCTOBER, 29).getTime(), line.getValue(15));
	}

}

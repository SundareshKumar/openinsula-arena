package org.openinsula.arena.io.textfile.impl.cnab240;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.impl.cnab240.SegmentoPLineFactory;
import org.openinsula.arena.io.textfile.line.Line;

public class SegmentoPLineFactoryTestCase {
	protected SegmentoPLineFactory lineFactory = new SegmentoPLineFactory();

	@Test
	public void testLineSize() {
		assertEquals(240, lineFactory.getTotalSize());
	}

	@Test
	public void testParse() {
		String s = "0010001300001P 010035220000000472298 00000000000000000   11111012448-1       1211200500000000003718800000002N13092005100000000000000000000111000000000000000000000000000000000000000000000000000000502305-1                 1062000090017603264 ";
		Line line = lineFactory.parseLine(s);

		assertTrue(lineFactory.doMatches(s));

		assertEquals(1, line.getValue(0));
		assertEquals(1, line.getValue(1));
		assertEquals(3, line.getValue(2));
		assertEquals(1, line.getValue(3));
		assertEquals("P", line.getValue(4));
		assertNull(line.getValue(5));
		assertEquals(1, line.getValue(6));
		assertEquals(352, line.getValue(7));
		assertEquals("2", line.getValue(8));
		assertEquals(47229, line.getValue(9));
		assertEquals("8", line.getValue(10));
	}

	@Test
	public void testToString() {
		String s = "0010001300001P 010035220000000472298 00000000000000000   11111012448-1       1211200500000000003718800000002N13092005100000000000000000000111000000000000000000000000000000000000000000000000000000502305-1                 1062000090017603264 ";

		Line line = lineFactory.createLine();
		line.setValue(0, 1);
		line.setValue(1, 1);
		line.setValue(2, 3);
		line.setValue(3, 1);
		line.setValue(4, "P");
		line.setValue(6, 1);
		line.setValue(7, 352);
		line.setValue(8, "2");
		line.setValue(9, 47229);
		line.setValue(10, "8");
		line.setValue(12, "00000000000000000");
		line.setValue(13, 1);
		line.setValue(14, 1);
		line.setValue(15, 1);
		line.setValue(16, 1);
		line.setValue(17, 1);
		line.setValue(18, "012448-1");
		line.setValue(19, new GregorianCalendar(2005, Calendar.NOVEMBER, 12).getTime());
		line.setValue(20, 37188);
		line.setValue(22, "0");
		line.setValue(23, 2);
		line.setValue(24, "N");
		line.setValue(25, new GregorianCalendar(2005, Calendar.SEPTEMBER, 13).getTime());
		line.setValue(26, 1);
		line.setValue(28, 111);
		line.setValue(34, "502305-1");
		line.setValue(35, 1);
		line.setValue(36, 6);
		line.setValue(37, 2);
		line.setValue(39, 9);
		line.setValue(40, 17603264);

		assertEquals(s, line.toString());
	}
}

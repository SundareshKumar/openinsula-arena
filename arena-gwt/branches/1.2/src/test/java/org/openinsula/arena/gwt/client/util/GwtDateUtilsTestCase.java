package org.openinsula.arena.gwt.client.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Test;
import org.openinsula.arena.gwt.client.util.GwtDateUtils.DateField;


public class GwtDateUtilsTestCase extends TestCase {

	@Test
	public void testAddDay() {
		Date date = new GregorianCalendar(2008, Calendar.JANUARY, 10).getTime();
		date = GwtDateUtils.add(date, DateField.DATE, 10);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(20, calendar.get(Calendar.DATE));

		date = GwtDateUtils.add(date, DateField.DATE, 20);
		calendar.setTime(date);
		assertEquals(9, calendar.get(Calendar.DATE));
		assertEquals(Calendar.FEBRUARY, calendar.get(Calendar.MONTH));
	}

	@Test
	public void testLowerLimit() {
		Date date = new Date();
		date = GwtDateUtils.lowerLimit(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, calendar.get(Calendar.MINUTE));
		assertEquals(0, calendar.get(Calendar.SECOND));
	}

	@Test
	public void testUpperLimit() {
		Date date = new Date();
		date = GwtDateUtils.upperLimit(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		assertEquals(23, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(59, calendar.get(Calendar.MINUTE));
		assertEquals(59, calendar.get(Calendar.SECOND));
	}

	@Test
	public void testDifference() {
		Date dataInicial = new GregorianCalendar(2008, Calendar.DECEMBER, 10).getTime();
		Date dataFinal = new GregorianCalendar(2008, Calendar.DECEMBER, 20).getTime();

		assertEquals(10L, GwtDateUtils.difference(dataInicial, dataFinal, DateField.DATE));
		assertEquals(240L, GwtDateUtils.difference(dataInicial, dataFinal, DateField.HOUR));
	}

	@Test
	public void testSubtractDays() {
		Date data = new GregorianCalendar(2009, Calendar.JULY, 10).getTime();
		Date dataExperada = new GregorianCalendar(2009, Calendar.JULY, 5).getTime();

		assertEquals(dataExperada, GwtDateUtils.subtractDays(data, 5));
		assertNotSame(dataExperada, GwtDateUtils.subtractDays(data, 2));
	}

}


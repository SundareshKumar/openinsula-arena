package org.openinsula.arena.gwt.client.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.gwt.client.util.GwtDateUtils.DateField;


public class GwtDateUtilsTestCase {

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

}


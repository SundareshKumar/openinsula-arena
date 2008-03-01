package org.openinsula.arena.lang.datetime;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateBuilderTest {

	@Test
	public void testMethods() {
		int[][] testValues = { { 2007, 1, 11, 30, 25 }, { 2007, 1, 0, 0, 0 } };

		Calendar expected = new GregorianCalendar();
		expected.set(Calendar.MILLISECOND, 0);

		for (int i = 0; i < testValues.length; i++) {
			expected.set(Calendar.YEAR, testValues[i][0]);
			expected.set(Calendar.DAY_OF_MONTH, testValues[i][1]);
			expected.set(Calendar.HOUR_OF_DAY, testValues[i][2]);
			expected.set(Calendar.MINUTE, testValues[i][3]);
			expected.set(Calendar.SECOND, testValues[i][4]);
			
			for (int month = 0; month < 12; month++) {
				expected.set(Calendar.MONTH, month);
				Date actual = runMethod(getMethod(month, true), testValues[i][1], testValues[i][0],
						testValues[i][2], testValues[i][3], testValues[i][4]);
				
				assertEquals(expected.getTime(), actual);
			}
		}
		
	}

	private Method getMethod(final int month, final boolean full) {
		String[] methodNames = { "january", "february", "march", "april",
			"may", "june", "july", "august", "september", "october",
			"november", "december" };
		
		Class<?>[] parameterTypes = full ? 
				new Class<?>[] { int.class, int.class, int.class, int.class, int.class } : 
					new Class<?>[] { int.class, int.class};
		
		try {
			Method method = DateBuilder.class.getMethod(methodNames[month], parameterTypes);
			return method;
			
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		
	}
	
	private Date runMethod(final Method method, final Object ... values) {
		try {
			return (Date) method.invoke(null, values);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

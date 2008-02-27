package org.openinsula.arena.io.textfile.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;

public class FieldTestCase {
	@Test
	public void testAlphanumericFormatValue() {
		StringField field = new StringField(5);
		assertEquals("a    ", field.formatValue("a"));
		assertEquals("ab   ", field.formatValue("ab"));
		assertEquals("abc  ", field.formatValue("abc"));
		assertEquals("abcd ", field.formatValue("abcd"));
		assertEquals("abcde", field.formatValue("abcde"));
		assertEquals("abcde", field.formatValue("abcdefg"));
		assertEquals("abcde", field.formatValue("abcdefghijklmn"));
		assertEquals("     ", field.formatValue(null));
		assertEquals("     ", field.formatValue(""));

		field = new StringField(5, true);
		assertEquals("    a", field.formatValue("a"));
		assertEquals("   ab", field.formatValue("ab"));
		assertEquals("  abc", field.formatValue("abc"));
		assertEquals(" abcd", field.formatValue("abcd"));
		assertEquals("abcde", field.formatValue("abcde"));
		assertEquals("abcde", field.formatValue("abcdefg"));
		assertEquals("abcde", field.formatValue("abcdefghijklmn"));
		assertEquals("     ", field.formatValue(null));
		assertEquals("     ", field.formatValue(""));
	}

	@Test
	public void testNumericFormatValue() {
		NumericField field = new NumericField(5);
		assertEquals("00000", field.formatValue(0));
		assertEquals("00000", field.formatValue(null));
		assertEquals("00001", field.formatValue(1));
		assertEquals("00123", field.formatValue(123L));
		assertEquals("12345", field.formatValue(12345));

		assertEquals("00123", field.formatValue(new BigDecimal("1.23")));
		assertEquals("00123", field.formatValue(new BigDecimal("123")));
		assertEquals("00123", field.formatValue(new BigDecimal("12.3")));
		assertEquals("01230", field.formatValue(new BigDecimal("123.0")));
		try {
			field.formatValue(123456);
			fail();
		}
		catch (Exception ex) {
		}

		try {
			field.formatValue(12345678L);
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testDateFormatValue() {
		DateField field = new DateField(8, "ddMMyyyy");

		assertEquals("09072006", field.formatValue(new GregorianCalendar(2006, Calendar.JULY, 9).getTime()));
		assertEquals("22122006", field.formatValue(new GregorianCalendar(2006, Calendar.DECEMBER, 22).getTime()));
		assertEquals("00000000", field.formatValue(null));

		field = new DateField(6, "ddMMyyyy");
		assertEquals("000000", field.formatValue(null));
		try {
			field.formatValue(new GregorianCalendar(2006, Calendar.JULY, 9).getTime());
			fail();
		}
		catch (Exception ex) {
		}

		field = new DateField(8, "ddMMyyyy").setFillCharacter(' ');
		assertEquals("        ", field.formatValue(null));
	}
}

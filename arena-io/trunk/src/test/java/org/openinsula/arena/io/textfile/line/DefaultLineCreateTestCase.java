package org.openinsula.arena.io.textfile.line;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openinsula.arena.io.textfile.field.DateField;
import org.openinsula.arena.io.textfile.field.NumericField;
import org.openinsula.arena.io.textfile.field.StringField;
import org.openinsula.arena.io.textfile.line.AbstractLineFactory;
import org.openinsula.arena.io.textfile.line.Line;
import org.openinsula.arena.io.textfile.line.LineFactory;

public class DefaultLineCreateTestCase {
	@Test
	public void testInvalidValue() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(2));

		Line line = lineFactory.createLine();
		try {
			line.setValue(0, "abc");
			fail();
		}
		catch (Exception ex) {
		}

		try {
			line.setValue(1, 123);
			fail();
		}
		catch (Exception ex) {
		}

		try {
			line.setValue(2, "abc");
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testNonExistingIndex() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		Line line = lineFactory.createLine();

		try {
			line.setValue(4, 123);
			fail();
		}
		catch (Exception ex) {
		}

		try {
			line.setValue(-1, 123);
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testAssignValueTwice() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));

		Line line = lineFactory.createLine();
		line.setValue(0, 321);
		try {
			line.setValue(0, 123);
			fail();
		}
		catch (Exception ex) {
		}
	}

	@Test
	public void testLineFactoryToString() {
		LineFactory lineFactory = new AbstractLineFactory() {
			@Override
			public boolean doMatches(String s) {
				return false;
			}
		};

		lineFactory.addField(new NumericField(5));
		lineFactory.addField(new StringField(10));
		lineFactory.addField(new DateField(8, "ddMMyyyy"));
		lineFactory.addField(new NumericField(2));

		Line line = lineFactory.createLine();
		line.setValue(0, 12345);
		line.setValue(2, new GregorianCalendar(2006, Calendar.JULY, 9).getTime());
		line.setValue(3, 2);

		assertEquals("12345          0907200602", line.toString());

		line = lineFactory.createLine();
		line.setValue(0, 321);
		line.setValue(1, "EDSON");
		line.setValue(3, 44);

		assertEquals("00321EDSON     0000000044", line.toString());
	}

}

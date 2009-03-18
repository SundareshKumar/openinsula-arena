package org.openinsula.arena.tiss.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TissTypeConverterUtils {

	public static XMLGregorianCalendar getAsData(Date date) {
		try {
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			
			XMLGregorianCalendar gregorianCalendar = datatypeFactory.newXMLGregorianCalendar();
			gregorianCalendar.setYear(calendar.get(Calendar.YEAR));
			gregorianCalendar.setMonth(calendar.get(Calendar.MONTH) + 1);
			gregorianCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			
			return gregorianCalendar;
		}
		catch (DatatypeConfigurationException ex) {
			throw new IllegalArgumentException("", ex);
		}
	}

	public static XMLGregorianCalendar getAsTime(Date date) {
		try {
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			
			XMLGregorianCalendar gregorianCalendar = datatypeFactory.newXMLGregorianCalendar();
			gregorianCalendar.setHour(calendar.get(Calendar.HOUR_OF_DAY));
			gregorianCalendar.setMinute(calendar.get(Calendar.MINUTE));
			gregorianCalendar.setSecond(calendar.get(Calendar.SECOND));
			
			return gregorianCalendar;
		}
		catch (DatatypeConfigurationException ex) {
			throw new IllegalArgumentException("", ex);
		}
	}

}

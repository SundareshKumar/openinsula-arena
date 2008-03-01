package org.openinsula.arena.lang.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateBuilder {

	public static Date now() {
		return new Date();
	}
	
	public static Date january(final int dayOfMonth, final int year) {
		return january(dayOfMonth, year, 0, 0, 0);
	}

	public static Date january(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.JANUARY, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date february(final int dayOfMonth, final int year) {
		return february(dayOfMonth, year, 0, 0, 0);
	}

	public static Date february(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.FEBRUARY, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date march(final int dayOfMonth, final int year) {
		return march(dayOfMonth, year, 0, 0, 0);
	}

	public static Date march(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {
		
		return dateTimeCreator(year, Calendar.MARCH, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date april(final int dayOfMonth, final int year) {
		return april(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date april(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {
		
		return dateTimeCreator(year, Calendar.APRIL, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date may(final int dayOfMonth, final int year) {
		return may(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date may(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {
		
		return dateTimeCreator(year, Calendar.MAY, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date june(final int dayOfMonth, final int year) {
		return june(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date june(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {
		
		return dateTimeCreator(year, Calendar.JUNE, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date july(final int dayOfMonth, final int year) {
		return july(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date july(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.JULY, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date august(final int dayOfMonth, final int year) {
		return august(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date august(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.AUGUST, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date september(final int dayOfMonth, final int year) {
		return september(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date september(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.SEPTEMBER, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date october(final int dayOfMonth, final int year) {
		return october(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date october(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.OCTOBER, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date november(final int dayOfMonth, final int year) {
		return november(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date november(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.NOVEMBER, dayOfMonth, hourOfDay,
				minute, second);
	}

	public static Date december(final int dayOfMonth, final int year) {
		return december(year, dayOfMonth, 0, 0, 0);
	}
	
	public static Date december(final int dayOfMonth, final int year,
			final int hourOfDay, final int minute, final int second) {

		return dateTimeCreator(year, Calendar.DECEMBER, dayOfMonth, hourOfDay,
				minute, second);
	}

	private static Date dateTimeCreator(final int year, final int month,
			final int dayOfMonth, final int hourOfDay, final int minute,
			final int second) {

		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay,
				minute, second);
		
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

}

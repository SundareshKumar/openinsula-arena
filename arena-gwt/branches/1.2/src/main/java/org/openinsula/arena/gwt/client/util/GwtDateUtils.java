package org.openinsula.arena.gwt.client.util;

import java.util.Date;

public class GwtDateUtils {

	private GwtDateUtils() {
	}

	public enum DateField {
		SECOND(1000),
		MINUTE(60 * 1000),
		HOUR(60 * 60 * 1000),
		DATE(24 * 60 * 60 * 1000),
		MONTH(30 * 24 * 60 * 60 * 1000),
		YEAR(365 * 24 * 60 * 60 * 1000);

		private long milliseconds;

		private DateField(long milliseconds) {
			this.milliseconds = milliseconds;
		}

		public long getMilliseconds() {
			return milliseconds;
		}
	}

	@SuppressWarnings("deprecation")
	public static Date add(Date baseDate, DateField field, int amount) {
		Date date = new Date(baseDate.getTime());
		switch (field) {
		case YEAR:
			int year = date.getYear();
			date.setYear(year + amount);
			return date;
		case MONTH:
			int month = date.getMonth();
			date.setMonth(month + amount);
			return date;
		case DATE:
			int day = date.getDate();
			date.setDate(day + amount);
			return date;
		case HOUR:
			int hour = date.getHours();
			date.setHours(hour + amount);
			return date;
		case MINUTE:
			int min = date.getMinutes();
			date.setMinutes(min + amount);
			return date;
		case SECOND:
			int sec = date.getSeconds();
			date.setSeconds(sec + amount);
			return date;
		}
		return null;
	}

	public static Date lowerLimit(Date date) {
		return updateTime(date, 0, 0, 0);
	}

	public static Date upperLimit(Date date) {
		return updateTime(date, 23, 59, 59);
	}

	@SuppressWarnings("deprecation")
	private static Date updateTime(Date date, int hour, int min, int sec) {
		return new Date(date.getYear(), date.getMonth(), date.getDate(), hour, min, sec);
	}

	public static long difference(Date dataInicial, Date dataFinal, DateField timeUnit) {
		long diff = dataFinal.getTime() - dataInicial.getTime();
		return diff / timeUnit.getMilliseconds();
	}

}

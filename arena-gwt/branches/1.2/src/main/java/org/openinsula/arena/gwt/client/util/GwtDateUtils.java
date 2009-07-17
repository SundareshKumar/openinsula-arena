package org.openinsula.arena.gwt.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class GwtDateUtils {

	private GwtDateUtils() {
	}

	public enum DateField {
		SECOND(1000), MINUTE(60 * 1000), HOUR(60 * 60 * 1000), DATE(24 * 60 * 60 * 1000), MONTH(30 * 24 * 60 * 60
				* 1000), YEAR(365 * 24 * 60 * 60 * 1000);

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

	/**
	 *
	 * @param date
	 * @return <code>-1</code> if the date is <code>null</code> or a
	 * <code>int</code> between 1 and 31 depending on the month and year
	 */
	public static int getDay(Date date) {
		if (date == null) {
			return -1;
		}

		return Integer.valueOf(DateTimeFormat.getFormat("dd").format(date));
	}

	/**
	 *
	 * @param date
	 * @return <code>-1</code> if the date is <code>null</code> or a
	 * <code>int</code> between 1 and 12
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return -1;
		}

		return Integer.valueOf(DateTimeFormat.getFormat("MM").format(date));
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}

		return Integer.valueOf(DateTimeFormat.getFormat("yyyy").format(date));
	}

	/**
	 * Metodo para subtrair uma data dentro do client do GWT
	 * @param data Recebe uma data
	 * @return Retorna uma data subtraida
	 * @author anderson
	 */
	public static Date subtractDays(Date data, int quantidadeDia) {
		data = GwtDateUtils.lowerLimit(data);

		long milisegundos = data.getTime() - (1000 * 60 * 60 * (24 * quantidadeDia));
		data.setTime(milisegundos);

		return data;
	}

	/**
	 * Metodo para somar dias em uma data dentro do client do GWT
	 * @param data Recebe uma data
	 * @return Retorna uma data somada
	 * @author anderson
	 */
	public static Date addDays(Date data, int quantidadeDia) {
		data = GwtDateUtils.lowerLimit(data);

		long milisegundos = data.getTime() + (1000 * 60 * 60 * (24 * quantidadeDia));
		data.setTime(milisegundos);

		return data;
	}
}

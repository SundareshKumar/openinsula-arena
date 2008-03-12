/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que verifica se uma determinada data é um feriado nacional estático:
 * <ul>
 * <li>1 de Janeiro - Confraternização Universal</li>
 * <li>21 de Abril - Tiradentes</li>
 * <li>1 de Maio - Dia do Trabalho</li>
 * <li>7 de Setembro - Independência do Brasil</li>
 * <li>12 de Outubro - Dia da Padroeira do Brasil</li>
 * <li>2 de Novembro - Finados</li>
 * <li>15 de Novembro - Proclamação da República</li>
 * <li>25 de Dezembro - Natal</li>
 * </ul>
 * 
 * <p>
 * Ou um feriado nacional móvel:
 * <ul>
 * <li>Carnaval</li>
 * <li>Sexta-feira Santa
 * <li>
 * <li>Corpus Christi</li>
 * </ul>
 * 
 * @author yanaga
 * @since 1.0
 */
public class FeriadoNacionalManager {
	private static final Set<FeriadoEstatico> feriadoEstaticoList = new HashSet<FeriadoEstatico>();

	static {
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.JANUARY, 1));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.APRIL, 21));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.MAY, 1));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.SEPTEMBER, 7));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.OCTOBER, 12));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.NOVEMBER, 2));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.NOVEMBER, 15));
		feriadoEstaticoList.add(new FeriadoEstatico(Calendar.DECEMBER, 25));
	}

	/**
	 * Método que verifica se a data não é um final de semana ou um feriado
	 * nacional estático ou móvel.
	 * @param data A data a ser verificada.
	 * @return <strong>true</strong> se for um dia útil, e <strong>false</strong>
	 * caso contrário.
	 */
	public boolean isDiaUtil(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);

		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case Calendar.SATURDAY:
		case Calendar.SUNDAY:
			return false;
		default:
			return !isFeriadoNacional(data);
		}
	}

	/**
	 * Metodo que verifica se é um feriado nacional estático ou móvel.
	 * @param data A data a ser verificada.
	 * @return <strong>true</strong> se for um feriado nacional, e
	 * <strong>false</strong> caso contrario.
	 */
	public boolean isFeriadoNacional(Date data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);

		for (FeriadoEstatico feriadoEstatico : feriadoEstaticoList) {
			if (feriadoEstatico.getMes() == calendar.get(Calendar.MONTH)
					&& feriadoEstatico.getDia() == calendar.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}

		Calendar pascoa = getPascoa(calendar.get(Calendar.YEAR));

		Calendar sextaFeiraSanta = (Calendar) pascoa.clone();
		sextaFeiraSanta.add(Calendar.DAY_OF_YEAR, -2);

		if (sextaFeiraSanta.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& sextaFeiraSanta.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}

		Calendar corpusChristi = (Calendar) pascoa.clone();
		corpusChristi.add(Calendar.DAY_OF_YEAR, 60);

		if (corpusChristi.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& corpusChristi.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}

		Calendar carnaval = (Calendar) pascoa.clone();
		carnaval.add(Calendar.DAY_OF_YEAR, -47);

		if (carnaval.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& carnaval.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}

		return false;
	}

	/**
	 * Cálculo para obter o dia da páscoa, válido para qualquer ano após o
	 * estabelecimento do Calendário Gregoriano (ou seja, a partir de 1583), de
	 * autoria do astrônomo frances Jean Baptiste Joseph Delambre (1749, 1822).
	 * @param year O ano para o cálculo da páscoa.
	 * @return O dia da páscoa do respectivo ano.
	 */
	protected Calendar getPascoa(int year) {
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int p = (h + l - 7 * m + 114) % 31;

		int month = (h + l - 7 * m + 114) / 31;
		int day = p + 1;

		return new GregorianCalendar(year, month - 1, day);
	}

	/**
	 * Inner class utilizada para armazenar o mês e o dia de Feriados Nacionais
	 * estáticos.
	 * @author yanaga
	 */
	private static class FeriadoEstatico {
		private int mes;

		private int dia;

		public FeriadoEstatico(int mes, int dia) {
			this.mes = mes;
			this.dia = dia;
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public int getMes() {
			return mes;
		}

		public void setMes(int mes) {
			this.mes = mes;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof FeriadoEstatico) {
				FeriadoEstatico other = (FeriadoEstatico) obj;
				return this.mes == other.mes && this.dia == other.dia;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Integer.toString(mes).hashCode() + Integer.toString(dia).hashCode();
		}
	}
}

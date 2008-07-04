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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class FeriadoNacionalManagerTestCase {
	private FeriadoNacionalManager feriadoNacionalManager = new FeriadoNacionalManager();

	@Test
	public void testDiaUtil() {
		assertTrue(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.JANUARY, 18).getTime()));
		assertTrue(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.JANUARY, 2).getTime()));
		assertTrue(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.JULY, 26).getTime()));

		assertFalse(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.JULY, 8).getTime()));
		assertFalse(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.SEPTEMBER, 24).getTime()));
		assertFalse(feriadoNacionalManager.isDiaUtil(new GregorianCalendar(2006, Calendar.OCTOBER, 29).getTime()));
	}

	@Test
	public void testFeriadosEstaticos() {
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.SEPTEMBER, 7)
				.getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2007, Calendar.SEPTEMBER, 7)
				.getTime()));
		assertTrue(feriadoNacionalManager
				.isFeriadoNacional(new GregorianCalendar(2008, Calendar.OCTOBER, 12).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2009, Calendar.DECEMBER, 25)
				.getTime()));
		assertTrue(feriadoNacionalManager
				.isFeriadoNacional(new GregorianCalendar(2006, Calendar.NOVEMBER, 2).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.MAY, 1).getTime()));

		assertFalse(feriadoNacionalManager
				.isFeriadoNacional(new GregorianCalendar(2006, Calendar.JANUARY, 2).getTime()));
		assertFalse(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.FEBRUARY, 10)
				.getTime()));
	}

	@Test
	public void testSextaFeiraSanta() {
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2000, Calendar.APRIL, 21).getTime()));

		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2001, Calendar.APRIL, 13).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2002, Calendar.MARCH, 29).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2003, Calendar.APRIL, 18).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2004, Calendar.APRIL, 9).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2005, Calendar.MARCH, 25).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.APRIL, 14).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2007, Calendar.APRIL, 6).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2008, Calendar.MARCH, 21).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2009, Calendar.APRIL, 10).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2010, Calendar.APRIL, 2).getTime()));

		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2011, Calendar.APRIL, 22).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2012, Calendar.APRIL, 6).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2013, Calendar.MARCH, 29).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2014, Calendar.APRIL, 18).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2015, Calendar.APRIL, 3).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2016, Calendar.MARCH, 25).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2017, Calendar.APRIL, 14).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2018, Calendar.MARCH, 30).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2019, Calendar.APRIL, 19).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2020, Calendar.APRIL, 10).getTime()));
	}

	@Test
	public void testCorpusChristi() {
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2000, Calendar.JUNE, 22).getTime()));

		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2005, Calendar.MAY, 26).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.JUNE, 15).getTime()));
	}

	@Test
	public void testCarnaval() {
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2000, Calendar.MARCH, 7).getTime()));

		assertTrue(feriadoNacionalManager
				.isFeriadoNacional(new GregorianCalendar(2005, Calendar.FEBRUARY, 8).getTime()));
		assertTrue(feriadoNacionalManager.isFeriadoNacional(new GregorianCalendar(2006, Calendar.FEBRUARY, 28)
				.getTime()));
	}

}

/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena-Lang.
 *
 *  Arena-Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.Test;
import org.openinsula.arena.lang.util.OtherLocales;

public class MoneyTestCase {
	
	@Test
	public void testNew() {
		Money money = new Money();
		assertEquals(new Money("0.00"), money);
	}
	
	@Test
	public void testeAdd() {
		Money money = new Money(5).from(OtherLocales.BRAZIL);
		money.add(0.259);

		assertEquals(new Money(5.259).from(OtherLocales.BRAZIL), money);
		assertEquals("R$ 5,26", money.toString());
		
		money.add(new Money(new BigDecimal("0.005")));
		assertEquals("R$ 5,26", money.toString());
	}

	@Test
	public void testeConvert() {
		Money dollar = new Money(10000).from(Locale.US);
		Money real = new Money(1.8).from(OtherLocales.BRAZIL);

		real = dollar.convertTo(real);
		assertNotSame(real, dollar);
		assertEquals(new Money(18000).from(OtherLocales.BRAZIL), real);
	}

	@Test
	public void testeDivide() {
		Money a = new Money(31.93);
		Money b = new Money(1596.36);
		
		assertEquals(new Money(0.02), a.divide(b));
	}
	
}

package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.Test;
import org.openinsula.arena.lang.util.OtherLocales;

public class MoneyTest {
	
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

}

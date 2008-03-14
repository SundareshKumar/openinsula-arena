package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

public class DecimalUtilsTest {

	@Test
	public void testNullSafeCopyDecimal() {
		Decimal a = null;
		assertNull(DecimalUtils.nullSafeCopy(a));
		
		a = new Decimal();
		Decimal b = DecimalUtils.nullSafeCopy(a);
		
		assertNotSame(b, a);
		assertEquals(a, b);
	}

	@Test
	public void testNullSafeCopyMoney() {
		Money a = null;
		assertNull(DecimalUtils.nullSafeCopy(a));
		
		a = new Money();
		Money b = DecimalUtils.nullSafeCopy(a);
		assertNotSame(b, a);
		assertEquals(a, b);
		
		b.from(Locale.CHINA);
		Money c = DecimalUtils.nullSafeCopy(b);
		assertEquals(c, b);
		
	}

}

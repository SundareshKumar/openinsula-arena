package org.openinsula.arena.lang.numbers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class DecimalTest {

	@Test
	public void testConstructor() {
		Decimal zero = new Decimal();
		Decimal explicitZeroFromLong = new Decimal(0L);

		assertEquals(zero, explicitZeroFromLong);

		Decimal numberFromInt = new Decimal(10);
		Decimal numberFromString = new Decimal("10");
		Decimal numberFromDouble = new Decimal(10.0);
		Decimal numberFromBigDecimal = new Decimal(new BigDecimal(10));

		assertEquals(numberFromInt, numberFromString);
		assertEquals(numberFromInt, numberFromDouble);
		assertEquals(numberFromInt, numberFromBigDecimal);

		try {
			new Decimal((String) null);
			fail("NullPointerException expected");

		}
		catch (NullPointerException exc) {
		}
	}

	@Test
	public void testAmount() {
		Decimal a = new Decimal();
		Decimal b = new Decimal(1);
		Decimal c = a.amount(1);

		assertSame(a, c);
		assertEquals(a, b);

		c = a.amount(b);
		assertSame(a, c);
		assertEquals(a, b);
	}

	@Test
	public void testAdd() {
		Decimal a = new Decimal();
		Decimal b = new Decimal(1);

		Decimal c = a.add(b);
		assertSame(a, c);
		assertEquals(a, b);

		a.add("50.5");
		assertEquals(new Decimal(51.5), a);
	}

	@Test
	public void testSubtract() {
		Decimal a = new Decimal();
		Decimal b = new Decimal(1);
		Decimal c = b.subtract(b);

		assertSame(c, b);
		assertEquals(a, b);

		a.subtract(10);
		assertEquals(new Decimal(-10), a);
	}

	@Test
	public void testMultiply() {
		Decimal a = new Decimal(1);
		Decimal b = new Decimal(1.00000);

		Decimal c = a.multiply(b);
		assertSame(c, a);
		assertEquals(new Decimal(1), a);
	}

	@Test
	public void testDivide() {
		Decimal a = new Decimal(4);
		Decimal b = new Decimal(1.00000);

		Decimal c = a.divide(b);
		assertSame(c, a);
		assertEquals(new Decimal(4), a);

		b.divide(a);
		assertEquals(new Decimal(0.25), b);
	}

	@Test
	public void testIsGreaterThan() {
		Decimal a = new Decimal(1.00000);

		assertTrue(a.isGreaterThan(new Decimal()));
		assertTrue(a.isGreaterThan(-1));
		assertTrue(a.isGreaterThan(-0.99));
		assertTrue(a.isGreaterThan("0.5"));
		
		assertFalse(a.isGreaterThan("1.0"));
	}

	@Test
	public void testIsGreaterOrEqualsThan() {
		Decimal a = new Decimal(1.00000);

		assertTrue(a.isGreaterOrEqualsThan(new Decimal()));
		assertTrue(a.isGreaterOrEqualsThan(-1));
		assertTrue(a.isGreaterOrEqualsThan(-0.99));
		assertTrue(a.isGreaterOrEqualsThan("0.5"));
		
		assertTrue(a.isGreaterOrEqualsThan("1.0"));
		assertTrue(a.isGreaterOrEqualsThan(new BigDecimal(1)));
	}

	@Test
	public void testIsSmallerThan() {
		Decimal a = new Decimal(1.00000);

		assertFalse(a.isSmallerThan(new Decimal()));
		assertFalse(a.isSmallerThan(-1));
		assertFalse(a.isSmallerThan(-0.99));
		assertFalse(a.isSmallerThan("0.5"));
		
		assertTrue(a.isSmallerThan("1.000001"));
	}

	@Test
	public void testIsSmallerOrEqualsThan() {
		Decimal a = new Decimal(1.00000);

		assertFalse(a.isSmallerOrEqualsThan(new Decimal()));
		assertFalse(a.isSmallerOrEqualsThan(-1));
		assertFalse(a.isSmallerOrEqualsThan(-0.99));
		assertFalse(a.isSmallerOrEqualsThan("0.5"));
		
		assertTrue(a.isSmallerOrEqualsThan("1.00000"));
		assertTrue(a.isSmallerOrEqualsThan("1.000001"));
	}

	@Test
	public void testIsNegative() {
		Decimal a = new Decimal();
		assertFalse(a.isNegative());
		
		a.amount(1);
		assertFalse(a.isNegative());
		
		a.amount("-0.00000001");
		assertTrue(a.isNegative());
	}

	@Test
	public void testIsZero() {
		Decimal a = new Decimal();
		assertTrue(a.isZero());
		
		a.amount(1);
		assertFalse(a.isZero());
		
		a.amount("-0.00000001");
		assertFalse(a.isZero());
	}

	@Test
	public void testIsPositive() {
		Decimal a = new Decimal();
		assertFalse(a.isPositive());
		
		a.amount("0.0000001");
		assertTrue(a.isPositive());
		
		a.amount(1);
		assertTrue(a.isPositive());
		
		a.amount("-0.00000001");
		assertFalse(a.isPositive());
	}

	@Test
	public void testAbs() {
		Decimal a = new Decimal("-0.00000001");
		assertTrue(a.isNegative());
		
		Decimal b = a.abs();
		assertSame(b, a);
		assertFalse(a.isNegative());
		assertEquals(new Decimal("0.00000001"), a);
	}

	@Test
	public void testNegate() {
		Decimal a = new Decimal("-0.00000001");
		assertTrue(a.isNegative());
		
		Decimal b = a.negate();
		assertSame(b, a);
		assertFalse(a.isNegative());
		assertEquals(new Decimal("0.00000001"), a);
	}

	@Test
	public void testMaxBetween() {
		Decimal a = new Decimal(10);

		assertSame(a, a.maxBetween("1", "2.09", "3.00001", "9.9999999", "10.00"));
		assertEquals(new Decimal(20), a.maxBetween(1, 2, 3, 4, 10, 20));
	}

	@Test
	public void testMinBetween() {
		Decimal a = new Decimal(1);

		assertSame(a, a.minBetween("1", "2.09", "3.00001", "9.9999999", "10.00"));
		assertEquals(new Decimal(-2.00), a.minBetween(1, -2, 3, 4, 10, 20));
	}

	@Test
	public void testAddPercentage() {
		Decimal a = new Decimal(10);
		Decimal b = a.addPercentage(100);

		assertSame(a, b);
		assertEquals(new Decimal(20), a);

		b = a.addPercentage("50.50");
		assertEquals(new Decimal(new BigDecimal("30.10")), a);
	}

	@Test
	public void testGetPercentage() {
		Decimal a = new Decimal(100);

		Decimal b = a.getPercentage(5);

		assertNotSame(a, b);
		assertEquals(new Decimal(100), a);
		assertEquals(new Decimal(5), b);

		b = b.getPercentage("50");
		assertEquals(new Decimal(2.5), b);

		b = b.getPercentage(new BigDecimal("10.00"));
		assertEquals(new Decimal(new BigDecimal("0.25")), b);
	}

	@Test
	public void testToBigDecimal() {
		Decimal a = new Decimal();
		assertEquals(new BigDecimal("0.0"), a.toBigDecimal());

		a.amount(-10);
		assertEquals(new BigDecimal("-10.0"), a.toBigDecimal());
	}

	@Test
	public void testEqualsObject() {
		Decimal a = new Decimal();
		Decimal b = new Decimal(1.059);

		assertFalse(a.equals(b));

		a.add(b);
		assertTrue(a.equals(b));
		assertTrue(new Decimal("1.059").equals(a));
		assertTrue(new Decimal(1.059).equals(a));
		assertTrue(new Decimal(new BigDecimal(1.059)).equals(a));
		assertTrue(new Decimal(new BigDecimal("1.059")).equals(a));
	}

	@Test
	public void testToStringWithScale() {
		Decimal a = new Decimal("0.999");
		assertEquals("0.99", a.toString(2, RoundingMode.DOWN));
		assertEquals("1", a.toString(0, RoundingMode.UP));
	}
	
	@Test
	public void testToString() {
		Decimal a = new Decimal();
		assertEquals("0.0", a.toString());
	}

	@Test
	public void testCompareTo() {
		Decimal a = new Decimal();
		Decimal b = new Decimal();

		Decimal c = new Decimal(1.059);
		Decimal d = new Decimal(new BigDecimal("1.059"));

		assertTrue(a.compareTo(a) == 0);
		assertTrue(a.compareTo(b) == 0);
		assertTrue(a.compareTo(c) < 0);
		assertTrue(c.compareTo(d) == 0);
		assertTrue(c.compareTo(a) > 0);
	}

}

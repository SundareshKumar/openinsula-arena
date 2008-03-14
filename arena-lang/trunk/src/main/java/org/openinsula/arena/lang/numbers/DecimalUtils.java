package org.openinsula.arena.lang.numbers;

public abstract class DecimalUtils {

	public static Decimal nullSafeCopy(final Decimal decimal) {
		return decimal == null ? null : new Decimal(decimal);
	}
	
	public static Money nullSafeCopy(final Money money) {
		return money == null ? null : new Money(money);
	}
	
}

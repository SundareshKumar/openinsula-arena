package org.openinsula.arena.lang.numbers;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.util.ObjectUtils;

public class Money extends AbstractDecimal<Money> {
	private static final long serialVersionUID = 1L;
	
	private Locale locale = Locale.getDefault();
	
	public Money() {
		super();
	}

	public Money(final Money money) {
		super(money);
		this.locale = money.locale;
	}

	public Money(final Number amount) {
		super(amount);
	}

	public Money(final String amount) {
		super(amount);
	}

	public final Money from(final Locale locale) {
		this.locale = locale;
		return this;
	}

	public final Locale getLocale() {
		return locale;
	}

	public final Money convertTo(final Money cotation) {
		Money result = new Money(cotation);
		
		return result.amount(value.multiply(cotation.value));
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { value, locale });
	}

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj) && ObjectUtils.nullSafeEquals(locale, ((Money) obj).locale);
	}

	@Override
	public final String toString() {
		value = value.setScale(2, RoundingMode.HALF_EVEN);
		return NumberFormat.getCurrencyInstance(locale).format(value.doubleValue());
	}

}

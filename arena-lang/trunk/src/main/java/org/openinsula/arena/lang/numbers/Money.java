package org.openinsula.arena.lang.numbers;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.util.ObjectUtils;

public class Money extends AbstractDecimal<Money> {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SCALE = 2;
	
	public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
	
	private Locale locale = Locale.getDefault();
	
	public Money() {
		super("0.00");
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

	@SuppressWarnings("unchecked")
	@Override
	public Money divide(final AbstractDecimal decimal) {
		return super.divide(decimal, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	@Override
	public Money divide(final Number amount) {
		return super.divide(amount, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}
	
	@Override
	public Money divide(final String value) {
		return super.divide(value, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
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
		value = value.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
		return NumberFormat.getCurrencyInstance(locale).format(value.doubleValue());
	}

}

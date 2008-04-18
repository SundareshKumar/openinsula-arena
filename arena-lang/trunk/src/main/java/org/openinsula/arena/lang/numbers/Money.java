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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.util.ObjectUtils;

/**
 * A <b>Mutable</b> BigDecimal implementation for currency values.
 * By default, scale is 2 and RoundingMode is HALF_EVEN. 
 * @author Eduardo Rebola
 *
 */
public class Money extends AbstractDecimal<Money> {
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SCALE = 2;
	
	public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
	
	private Locale locale = Locale.getDefault();
	
	public Money() {
		this(false);
	}
	
	public Money(final boolean ignoreNumberFormatException) {
		super("0.00", ignoreNumberFormatException);
	}

	public Money(final Money money) {
		this(money, false);
	}
	
	public Money(final Money money, final boolean ignoreNumberFormatException) {
		super(money, ignoreNumberFormatException);
		this.locale = money.locale;
	}

	public Money(final Number amount) {
		this(amount, false);
	}
	
	public Money(final Number amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Money(final String amount) {
		this(amount, false);
	}
	
	public Money(final String amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
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
		BigDecimal copy = value.setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
		return NumberFormat.getCurrencyInstance(locale).format(copy.doubleValue());
	}

}

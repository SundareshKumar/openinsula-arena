package org.openinsula.arena.appengine.gwt.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

public class CurrencyConverter implements Converter<String, Double> {
	private final NumberFormat numberFormat;

	private boolean strictMode;

	public CurrencyConverter() {
		this(NumberFormat.getCurrencyFormat());
	}

	public CurrencyConverter(final NumberFormat numberFormat) {
		this.numberFormat = numberFormat;
	}

	public CurrencyConverter strict(final boolean value) {
		this.strictMode = value;
		return this;
	}

	@Override
	public Double convertForward(final String value) {
		try {
			return numberFormat.parse(value);
		}
		catch (RuntimeException e) {
			if (!strictMode) {
				return 0.0;
			}
			throw e;
		}
	}

	@Override
	public String convertReverse(final Double value) {
		try {
			return numberFormat.format(value);
		}
		catch (RuntimeException e) {
			if (!strictMode) {
				return null;
			}
			throw e;
		}
	}

}

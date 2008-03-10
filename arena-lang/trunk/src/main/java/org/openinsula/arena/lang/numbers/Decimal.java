package org.openinsula.arena.lang.numbers;

public class Decimal extends AbstractDecimal<Decimal> {
	private static final long serialVersionUID = 1L;
	
	public Decimal() {
		super();
	}
	
	public Decimal(final boolean ignoreNumberFormatException) {
		super(ignoreNumberFormatException);
	}

	public Decimal(final String amount) {
		super(amount);
	}
	
	public Decimal(final String amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Number amount) {
		amount(amount);
	}
	
	public Decimal(final Number amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Decimal decimal) {
		super(decimal);
	}
	
	public Decimal(final Decimal decimal, final boolean ignoreNumberFormatException) {
		super(decimal, ignoreNumberFormatException);
	}

}

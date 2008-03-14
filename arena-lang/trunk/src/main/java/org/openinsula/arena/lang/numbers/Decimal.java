package org.openinsula.arena.lang.numbers;

public class Decimal extends AbstractDecimal<Decimal> {
	private static final long serialVersionUID = 1L;
	
	public Decimal() {
		this(false);
	}
	
	public Decimal(final boolean ignoreNumberFormatException) {
		super(ignoreNumberFormatException);
	}

	public Decimal(final String amount) {
		this(amount, false);
	}
	
	public Decimal(final String amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Number amount) {
		this(amount, false);
	}
	
	public Decimal(final Number amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Decimal decimal) {
		this(decimal, false);
	}
	
	public Decimal(final Decimal decimal, final boolean ignoreNumberFormatException) {
		super(decimal, ignoreNumberFormatException);
	}

}

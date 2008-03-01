package org.openinsula.arena.lang.numbers;

public class Decimal extends AbstractDecimal<Decimal> { //extends Number implements Serializable, Comparable<Decimal> {
	private static final long serialVersionUID = 1L;

//	protected BigDecimal value;

	public Decimal() {
		amount(0.0);
	}

	public Decimal(final String amount) {
		amount(amount);
	}

	public Decimal(final Number amount) {
		amount(amount);
	}

	public Decimal(final Decimal decimal) {
		amount(decimal);
	}

//	public Decimal amount(final String value) {
//		this.value = new BigDecimal(value);
//		return this;
//	}
//
//	public Decimal amount(final Number value) {
//		return amount(String.valueOf(value.doubleValue()));
//	}
//
//	public Decimal amount(final Decimal decimal) {
//		this.value = decimal.value;
//		return this;
//	}
//
//	public Decimal add(final String value) {
//		this.value = this.value.add(new BigDecimal(value));
//		return this;
//	}
//
//	public Decimal add(final Number amount) {
//		return add(String.valueOf(amount.doubleValue()));
//	}
//
//	public Decimal add(final Decimal decimal) {
//		this.value = this.value.add(decimal.value);
//		return this;
//	}
//
//	public Decimal subtract(final String value) {
//		this.value = this.value.subtract(new BigDecimal(value));
//		return this;
//	}
//
//	public Decimal subtract(final Number amount) {
//		return subtract(String.valueOf(amount.doubleValue()));
//	}
//
//	public Decimal subtract(final Decimal decimal) {
//		this.value = this.value.subtract(decimal.value);
//		return this;
//	}
//
//	public Decimal multiply(final String value) {
//		this.value = this.value.multiply(new BigDecimal(value));
//		return this;
//	}
//
//	public Decimal multiply(final Number amount) {
//		return multiply(String.valueOf(amount.doubleValue()));
//	}
//
//	public Decimal multiply(final Decimal decimal) {
//		this.value = this.value.multiply(decimal.value);
//		return this;
//	}
//
//	public Decimal divide(final String value) {
//		this.value = this.value.divide(new BigDecimal(value));
//		return this;
//	}
//
//	public Decimal divide(final Number amount) {
//		return divide(String.valueOf(amount.doubleValue()));
//	}
//
//	public Decimal divide(final Decimal decimal) {
//		this.value = this.value.divide(decimal.value);
//		return this;
//	}
//
//	public Decimal getPercentage(final String percentage) {
//		return getPercentage(new Decimal(percentage));
//	}
//
//	public Decimal getPercentage(final Number percentage) {
//		return getPercentage(new Decimal(percentage));
//	}
//
//	public Decimal getPercentage(final Decimal percentage) {
//		return new Decimal(percentage).divide(100).multiply(this);
//	}
//
//	public Decimal addPercentage(final String percentage) {
//		return add(getPercentage(percentage));
//	}
//
//	public Decimal addPercentage(final Number percentage) {
//		return add(getPercentage(percentage));
//	}
//
//	public Decimal addPercentage(final Decimal percentage) {
//		return add(getPercentage(percentage));
//	}
//
//	public boolean isGreaterThan(final String value) {
//		return isGreaterThan(new Decimal(value));
//	}
//
//	public boolean isGreaterThan(final Number value) {
//		return isGreaterThan(new Decimal(value));
//	}
//
//	public boolean isGreaterThan(final Decimal value) {
//		return this.compareTo(value) > 0;
//	}
//
//	public boolean isGreaterOrEqualsThan(final String value) {
//		return isGreaterOrEqualsThan(new Decimal(value));
//	}
//
//	public boolean isGreaterOrEqualsThan(final Number value) {
//		return isGreaterOrEqualsThan(new Decimal(value));
//	}
//
//	public boolean isGreaterOrEqualsThan(final Decimal value) {
//		return this.compareTo(value) >= 0;
//	}
//
//	public boolean isSmallerThan(final String value) {
//		return isSmallerThan(new Decimal(value));
//	}
//
//	public boolean isSmallerThan(final Number value) {
//		return isSmallerThan(new Decimal(value));
//	}
//
//	public boolean isSmallerThan(final Decimal value) {
//		return this.compareTo(value) < 0;
//	}
//
//	public boolean isSmallerOrEqualsThan(final String value) {
//		return isSmallerOrEqualsThan(new Decimal(value));
//	}
//
//	public boolean isSmallerOrEqualsThan(final Number value) {
//		return isSmallerOrEqualsThan(new Decimal(value));
//	}
//
//	public boolean isSmallerOrEqualsThan(final Decimal value) {
//		return this.compareTo(value) <= 0;
//	}
//
//	public boolean isNegative() {
//		return value.signum() == -1;
//	}
//
//	public boolean isZero() {
//		return value.signum() == 0;
//	}
//
//	public boolean isPositive() {
//		return value.signum() == 1;
//	}
//
//	public Decimal abs() {
//		value = value.abs();
//		return this;
//	}
//
//	public Decimal negate() {
//		value = value.negate();
//		return this;
//	}
//
//	public Decimal maxBetween(final String... values) {
//		Decimal result = this;
//
//		for (String s : values) {
//			Decimal d = new Decimal(s);
//			
//			if (d.isGreaterThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//
//	public Decimal maxBetween(final Number... values) {
//		Decimal result = this;
//
//		for (Number n : values) {
//			Decimal d = new Decimal(n);
//			
//			if (d.isGreaterThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//
//	public Decimal maxBetween(final Decimal... values) {
//		Decimal result = this;
//
//		for (Decimal d : values) {
//			if (d.isGreaterThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//	
//	public Decimal minBetween(final String... values) {
//		Decimal result = this;
//
//		for (String s : values) {
//			Decimal d = new Decimal(s);
//			
//			if (d.isSmallerThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//	
//	public Decimal minBetween(final Number... values) {
//		Decimal result = this;
//
//		for (Number n : values) {
//			Decimal d = new Decimal(n);
//			
//			if (d.isSmallerThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//
//	public Decimal minBetween(final Decimal... values) {
//		Decimal result = this;
//
//		for (Decimal d : values) {
//			if (d.isSmallerThan(result)) {
//				result = d;
//			}
//		}
//
//		return result;
//	}
//
//	public BigDecimal toBigDecimal() {
//		return value;
//	}
//
//	public String toString(final int scale) {
//		BigDecimal copy = value.setScale(scale);
//		return copy.toString();
//	}
//	
//	public String toString(final int scale, final RoundingMode roundingMode) {
//		BigDecimal copy = value.setScale(scale, roundingMode);
//		return copy.toString();
//	}
//
//	// Object overrides
//
//	@Override
//	public int hashCode() {
//		return value.hashCode();
//	}
//
//	@Override
//	public boolean equals(final Object obj) {
//		if (this == obj) {
//			return true;
//		}
//
//		if (!(obj instanceof Decimal)) {
//			return false;
//		}
//
//		return value.compareTo(((Decimal) obj).value) == 0;
//	}
//
//	@Override
//	public String toString() {
//		return value.toString();
//	}
//
//	// java.util.Comparable implementation
//
//	@Override
//	public int compareTo(final Decimal decimal) {
//		return value.compareTo(decimal.value);
//	}
//
//	// java.lang.Number implementation (BigDecimal decorator)
//
//	@Override
//	public double doubleValue() {
//		return value.doubleValue();
//	}
//
//	@Override
//	public float floatValue() {
//		return value.floatValue();
//	}
//
//	@Override
//	public int intValue() {
//		return value.intValue();
//	}
//
//	@Override
//	public long longValue() {
//		return value.longValue();
//	}

}

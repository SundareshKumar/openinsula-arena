package org.openinsula.arena.lang.numbers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unchecked")
public class AbstractDecimal<T extends AbstractDecimal<?>> extends Number 
	implements Serializable, Comparable<T> {
	
	private static final long serialVersionUID = 1L;

	protected BigDecimal value;

	public AbstractDecimal() {
		amount(0.0);
	}

	public AbstractDecimal(final String amount) {
		amount(amount);
	}

	public AbstractDecimal(final Number amount) {
		amount(amount);
	}

	public AbstractDecimal(final AbstractDecimal decimal) {
		amount(decimal);
	}

	private T create(final String value) {
		try {
			return (T) getClass().newInstance().amount(value);
		}

		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private T create(final Number value) {
		try {
			return (T) getClass().newInstance().amount(value);
		}

		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private T create(final AbstractDecimal value) {
		try {
			return (T) getClass().newInstance().amount(value);
		}

		catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public T amount(final String value) {
		this.value = new BigDecimal(value);
		return (T) this;
	}

	public T amount(final Number value) {
		return amount(String.valueOf(value.doubleValue()));
	}

	public T amount(final AbstractDecimal decimal) {
		this.value = decimal.value;
		return (T) this;
	}

	public T add(final String value) {
		this.value = this.value.add(new BigDecimal(value));
		return (T) this;
	}

	public T add(final Number amount) {
		return add(String.valueOf(amount.doubleValue()));
	}

	public T add(final AbstractDecimal decimal) {
		this.value = this.value.add(decimal.value);
		return (T) this;
	}

	public T subtract(final String value) {
		this.value = this.value.subtract(new BigDecimal(value));
		return (T) this;
	}

	public T subtract(final Number amount) {
		return subtract(String.valueOf(amount.doubleValue()));
	}

	public T subtract(final AbstractDecimal decimal) {
		this.value = this.value.subtract(decimal.value);
		return (T) this;
	}

	public T multiply(final String value) {
		this.value = this.value.multiply(new BigDecimal(value));
		return (T) this;
	}

	public T multiply(final Number amount) {
		return multiply(String.valueOf(amount.doubleValue()));
	}

	public T multiply(final AbstractDecimal decimal) {
		this.value = this.value.multiply(decimal.value);
		return (T) this;
	}

	public T divide(final String value) {
		this.value = this.value.divide(new BigDecimal(value));
		return (T) this;
	}

	public T divide(final Number amount) {
		return divide(String.valueOf(amount.doubleValue()));
	}

	public T divide(final AbstractDecimal decimal) {
		this.value = this.value.divide(decimal.value);
		return (T) this;
	}

	public T getPercentage(final String percentage) {
		return getPercentage(create(percentage));
	}

	public T getPercentage(final Number percentage) {
		return getPercentage(create(percentage));
	}

	public T getPercentage(final AbstractDecimal percentage) {
		return (T) create(percentage).divide(100).multiply(this);
	}

	public T addPercentage(final String percentage) {
		return add(getPercentage(percentage));
	}

	public T addPercentage(final Number percentage) {
		return add(getPercentage(percentage));
	}

	public T addPercentage(final AbstractDecimal percentage) {
		return add(getPercentage(percentage));
	}

	public boolean isGreaterThan(final String value) {
		return isGreaterThan(create(value));
	}

	public boolean isGreaterThan(final Number value) {
		return isGreaterThan(create(value));
	}

	public boolean isGreaterThan(final AbstractDecimal value) {
		return this.compareTo((T) value) > 0;
	}

	public boolean isGreaterOrEqualsThan(final String value) {
		return isGreaterOrEqualsThan(create(value));
	}

	public boolean isGreaterOrEqualsThan(final Number value) {
		return isGreaterOrEqualsThan(create(value));
	}

	public boolean isGreaterOrEqualsThan(final AbstractDecimal value) {
		return this.compareTo((T) value) >= 0;
	}

	public boolean isSmallerThan(final String value) {
		return isSmallerThan(create(value));
	}

	public boolean isSmallerThan(final Number value) {
		return isSmallerThan(create(value));
	}

	public boolean isSmallerThan(final AbstractDecimal value) {
		return this.compareTo((T) value) < 0;
	}

	public boolean isSmallerOrEqualsThan(final String value) {
		return isSmallerOrEqualsThan(create(value));
	}

	public boolean isSmallerOrEqualsThan(final Number value) {
		return isSmallerOrEqualsThan(create(value));
	}

	public boolean isSmallerOrEqualsThan(final AbstractDecimal value) {
		return this.compareTo((T) value) <= 0;
	}

	public boolean isNegative() {
		return value.signum() == -1;
	}

	public boolean isZero() {
		return value.signum() == 0;
	}

	public boolean isPositive() {
		return value.signum() == 1;
	}

	public T abs() {
		value = value.abs();
		return (T) this;
	}

	public T negate() {
		value = value.negate();
		return (T) this;
	}

	public T maxBetween(final String... values) {
		AbstractDecimal result = this;

		for (String s : values) {
			AbstractDecimal d = create(s);

			if (d.isGreaterThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public T maxBetween(final Number... values) {
		AbstractDecimal result = this;

		for (Number n : values) {
			AbstractDecimal d = create(n);

			if (d.isGreaterThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public T maxBetween(final AbstractDecimal... values) {
		AbstractDecimal result = this;

		for (AbstractDecimal d : values) {
			if (d.isGreaterThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public T minBetween(final String... values) {
		AbstractDecimal result = this;

		for (String s : values) {
			AbstractDecimal d = create(s);

			if (d.isSmallerThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public T minBetween(final Number... values) {
		AbstractDecimal result = this;

		for (Number n : values) {
			AbstractDecimal d = create(n);

			if (d.isSmallerThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public T minBetween(final AbstractDecimal... values) {
		AbstractDecimal result = this;

		for (AbstractDecimal d : values) {
			if (d.isSmallerThan(result)) {
				result = d;
			}
		}

		return (T) result;
	}

	public BigDecimal toBigDecimal() {
		return value;
	}

	public String toString(final int scale) {
		BigDecimal copy = value.setScale(scale);
		return copy.toString();
	}

	public String toString(final int scale, final RoundingMode roundingMode) {
		BigDecimal copy = value.setScale(scale, roundingMode);
		return copy.toString();
	}

	// Object overrides

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AbstractDecimal)) {
			return false;
		}

		return value.compareTo(((AbstractDecimal) obj).value) == 0;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	// java.util.Comparable implementation

	@Override
	public int compareTo(final T other) {
		return value.compareTo(other.value);
	}

	// java.lang.Number implementation (BigDecimal decorator)

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

}

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

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.lang.util.FailSafeOperation;

/**
 * @author Eduardo R Danielli
 * 
 * @param <T> AbstractDecimal Type for typesafe chained methods
 */
@SuppressWarnings("unchecked")
public class AbstractDecimal<T extends AbstractDecimal<?>> extends Number implements Serializable, Comparable<T> {

	protected static final Log logger = LogFactory.getLog(AbstractDecimal.class);

	private static final long serialVersionUID = 1L;

	private boolean ignoreNumberFormatException;

	protected BigDecimal value;

	public AbstractDecimal() {
		this(false);
	}

	public AbstractDecimal(final boolean ignoreNumberFormatException) {
		this.ignoreNumberFormatException = ignoreNumberFormatException;

		amount(0.0);
	}

	public AbstractDecimal(final String amount) {
		this(amount, false);
	}

	public AbstractDecimal(final String amount, final boolean ignoreNumberFormatException) {
		this.ignoreNumberFormatException = ignoreNumberFormatException;

		amount(amount);
	}

	public AbstractDecimal(final Number amount) {
		this(amount, false);
	}

	public AbstractDecimal(final Number amount, final boolean ignoreNumberFormatException) {
		this.ignoreNumberFormatException = ignoreNumberFormatException;

		amount(amount);
	}

	public AbstractDecimal(final AbstractDecimal decimal) {
		this(decimal, decimal.ignoreNumberFormatException);
	}

	public AbstractDecimal(final AbstractDecimal decimal, final boolean ignoreNumberFormatException) {
		this.ignoreNumberFormatException = ignoreNumberFormatException;

		amount(decimal);
	}

	public final T ignoreNumberFormatException(final boolean value) {
		this.ignoreNumberFormatException = value;
		return (T) this;
	}

	private T create(final String value) {
		return createImpl(value, String.class);
	}

	private T create(final Number value) {
		return createImpl(value, Number.class);
	}

	private T createImpl(final Object value, final Class<?> desiredType) {
		return new FailSafeOperation<T>(true) {

			@Override
			protected T tryBody() throws Throwable {
				Class<? extends AbstractDecimal> tType = AbstractDecimal.this.getClass();
				T tInstance = (T) tType.newInstance();
				
				return (T) tType.getMethod("amount", desiredType).invoke(tInstance, value);
			}
			
		}.doTry();
	}

	public T amount(final String value) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = new BigDecimal(value);
			}

		}.doTry();
	}

	public T amount(final Number value) {
		return amount(new NumberToStringOperation(value).toString());
	}

	public T amount(final AbstractDecimal<?> decimal) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = decimal.value;
				
			}
			
		}.doTry();
	}

	public T add(final String value) {
		
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.add(new BigDecimal(value));
			}
			
		}.doTry();
		
	}

	public T add(final Number amount) {
		return add(new NumberToStringOperation(amount).toString());
	}

	public T add(final AbstractDecimal decimal) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.add(decimal.value);
			}

		}.doTry();
	}

	public T subtract(final String value) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.subtract(new BigDecimal(value));
			}
			
		}.doTry();
	}

	public T subtract(final Number amount) {
		return subtract(new NumberToStringOperation(amount).toString());
	}

	public T subtract(final AbstractDecimal decimal) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.subtract(decimal.value);
			}
			
		}.doTry();
	}

	public T multiply(final String value) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.multiply(new BigDecimal(value));
			}
			
		}.doTry();
	}

	public T multiply(final Number amount) {
		return multiply(new NumberToStringOperation(amount).toString());
	}

	public T multiply(final AbstractDecimal decimal) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.multiply(decimal.value);
			}
			
		}.doTry();
	}

	public T divide(final Number amount) {
		return divide(new NumberToStringOperation(amount).toString());
	}
	
	public T divide(final Number amount, final int scale, final RoundingMode roundingMode) {
		return divide(new NumberToStringOperation(amount).toString(), scale, roundingMode);
	}
	
	public T divide(final String value) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.divide(new BigDecimal(value));
			}
			
		}.doTry();
	}
	
	public T divide(final String value, final int scale, final RoundingMode roundingMode) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.divide(new BigDecimal(value), scale, roundingMode);
			}
			
		}.doTry();
	}

	public T divide(final AbstractDecimal decimal) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.divide(decimal.value);
			}
			
		}.doTry();
	}
	
	public T divide(final AbstractDecimal decimal, final int scale, final RoundingMode roundingMode) {
		return new Operation() {

			@Override
			protected void doTryBody() throws Throwable {
				instance.value = instance.value.divide(decimal.value, scale, roundingMode);
			}
			
		}.doTry();
	}

	public T getPercentage(final String percentage) {
		return getPercentageImpl(percentage, String.class);
	}

	public T getPercentage(final Number percentage) {
		return getPercentageImpl(percentage, Number.class);
	}

	public T getPercentage(final AbstractDecimal percentage) {
		return getPercentageImpl(percentage, AbstractDecimal.class);
	}
	
	private T getPercentageImpl(final Object value, final Class<?> desiredType) {
		return new FailSafeOperation<T>(!ignoreNumberFormatException) {

			@Override
			protected T tryBody() throws Throwable {
				return (T) createImpl(value, desiredType).divide(100).multiply(AbstractDecimal.this);
			}
			
		}.doTry();
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
		return new FailSafeOperation<Boolean>(true) {

			@Override
			protected Boolean tryBody() throws Throwable {
				return AbstractDecimal.this.compareTo((T) value) > 0;
			}
			
		}.doTry();
	}

	public boolean isGreaterOrEqualsThan(final String value) {
		return isGreaterOrEqualsThan(create(value));
	}

	public boolean isGreaterOrEqualsThan(final Number value) {
		return isGreaterOrEqualsThan(create(value));
	}

	public boolean isGreaterOrEqualsThan(final AbstractDecimal value) {
		return new FailSafeOperation<Boolean>(true) {

			@Override
			protected Boolean tryBody() throws Throwable {
				return AbstractDecimal.this.compareTo((T) value) >= 0;
			}
			
		}.doTry();
	}

	public boolean isSmallerThan(final String value) {
		return isSmallerThan(create(value));
	}

	public boolean isSmallerThan(final Number value) {
		return isSmallerThan(create(value));
	}

	public boolean isSmallerThan(final AbstractDecimal value) {
		return new FailSafeOperation<Boolean>(true) {

			@Override
			protected Boolean tryBody() throws Throwable {
				return AbstractDecimal.this.compareTo((T) value) < 0;
			}
			
		}.doTry();
	}

	public boolean isSmallerOrEqualsThan(final String value) {
		return isSmallerOrEqualsThan(create(value));
	}

	public boolean isSmallerOrEqualsThan(final Number value) {
		return isSmallerOrEqualsThan(create(value));
	}

	public boolean isSmallerOrEqualsThan(final AbstractDecimal value) {
		return new FailSafeOperation<Boolean>(true) {

			@Override
			protected Boolean tryBody() throws Throwable {
				return AbstractDecimal.this.compareTo((T) value) <= 0;
			}
			
		}.doTry();
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
		return maxBetweenImpl(values, String.class);
	}

	public T maxBetween(final Number... values) {
		return maxBetweenImpl(values, Number.class);
	}

	public T maxBetween(final AbstractDecimal... values) {
		return maxBetweenImpl(values, AbstractDecimal.class);
	}

	private T maxBetweenImpl(final Object[] values, final Class<?> desiredType) {
		AbstractDecimal result = this;

		for (Object s : values) {
			AbstractDecimal d = createImpl(s, desiredType);

			if (result.isSmallerThan(d)) {
				result = d;
			}
		}

		return (T) result;
	}
	
	public T minBetween(final String... values) {
		return minBetweenImpl(values, String.class);
	}

	public T minBetween(final Number... values) {
		return minBetweenImpl(values, Number.class);
	}

	public T minBetween(final AbstractDecimal... values) {
		return minBetweenImpl(values, AbstractDecimal.class);
	}
	
	private T minBetweenImpl(final Object[] values, final Class<?> desiredType) {
		AbstractDecimal result = this;

		for (Object s : values) {
			AbstractDecimal d = createImpl(s, desiredType);

			if (result.isGreaterThan(d)) {
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

	private abstract class Operation extends FailSafeOperation<T> {

		protected T instance;
		
		public Operation() {
			instance = (T) AbstractDecimal.this;
		}
		
		public Operation(final T instance) {
			this.instance = instance;
		}
		
		@Override
		protected final T tryBody() throws Throwable {
			doTryBody(); 
			
			return instance;
		}
		
		protected abstract void doTryBody() throws Throwable;
		
		@Override
		protected T doCatch(final Throwable throwable) {
			if (!ignoreNumberFormatException) {
				throw (RuntimeException) throwable;
			}
			
			if (logger.isWarnEnabled()) {
				logger.warn("Invalid value! Operation ignored!", throwable);
			}

			return instance;
		}
	}

	private class NumberToStringOperation extends FailSafeOperation<String> {
		private final Number number;
		
		public NumberToStringOperation(final Number number) {
			super(!ignoreNumberFormatException);
			
			this.number = number;
		}
		
		@Override
		protected String tryBody() throws Throwable {
			return String.valueOf(number.doubleValue());
		}
		
		@Override
		public String toString() {
			return doTry();
		}
		
	}
	
}

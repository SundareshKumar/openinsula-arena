package org.openinsula.arena.hibernate.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.openinsula.arena.lang.numbers.AbstractDecimal;
import org.openinsula.arena.lang.reflection.GenericsUtils;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("unchecked")
public abstract class AbstractDecimalUserType<T extends AbstractDecimal<?>> implements UserType {

	private static final int[] SQL_TYPES = { Types.NUMERIC };

	private final Class<T> decimalType;

	public AbstractDecimalUserType() {
		super();

		this.decimalType = (Class<T>) GenericsUtils.getSuperclassGenericType(getClass());
	}

	public Object deepCopy(final Object value) throws HibernateException {
		if (value == null) {
			return null;
		}

		try {
			return decimalType.getConstructor(decimalType).newInstance((T) value);
		}
		catch (Exception e) {
			throw new HibernateException(e);
		}
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) deepCopy(value);
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return ObjectUtils.nullSafeHashCode(x);
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final Object owner) throws HibernateException,
			SQLException {

		BigDecimal value = rs.getBigDecimal(names[0]);

		if (!rs.wasNull()) {
			try {
				T newObject = decimalType.newInstance();
				newObject.amount(value);
				prepareObject(newObject);

				return newObject;
			}
			catch (Exception e) {
				throw new HibernateException(e);
			}
		}

		return null;
	}

	protected void prepareObject(final T value) {

	}

	@Override
	public void nullSafeSet(final PreparedStatement st, final Object value, final int index) throws HibernateException,
			SQLException {

		if (value == null) {
			st.setNull(index, SQL_TYPES[0]);
		}
		else {
			T type = (T) value;
			st.setBigDecimal(index, type.toBigDecimal());
		}

	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		if (equals(original, target)) {
			return original;
		}

		return deepCopy(original);
	}

	@Override
	public Class<?> returnedClass() {
		return decimalType;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}

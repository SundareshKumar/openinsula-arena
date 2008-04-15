/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena-Hibernate.
 *
 *  Arena-Hibernate is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Hibernate is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Hibernate.  If not, see <http://www.gnu.org/licenses/>.
 */
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

package org.openinsula.arena.hibernate.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.openinsula.arena.lang.numbers.Money;

public class MoneyUserType implements UserType, ParameterizedType {
	public static final String PARAM_LANGUAGE = "_language";
	public static final String PARAM_COUNTRY = "_country";
	public static final String PARAM_VARIANT = "_variant";

	private static final int[] SQL_TYPES = { Types.NUMERIC };
	
	private Locale locale = Locale.getDefault();
	
	@Override
	public void setParameterValues(final Properties parameters) {
		
		if (parameters != null) {
			String language = parameters.getProperty(PARAM_LANGUAGE);
			String country = parameters.getProperty(PARAM_COUNTRY);
			String variant = parameters.getProperty(PARAM_VARIANT);
			
			if (language != null) {
				
				if (country != null) {
					
					if (variant != null) {
						locale = new Locale(language, country, variant);
					} else {
						locale = new Locale(language, country);
					}
					
				} else {
					locale = new Locale(language);
				}				
			} 
			
		}
	}
	
	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		
		if (null == x || null == y) {
			return false;
		}
		
		return x.equals(y);
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return x == null ? 0 : x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final Object owner) throws HibernateException, SQLException {
		BigDecimal value = rs.getBigDecimal(names[0]);
		
		if (!rs.wasNull()) {
			try {
				return Money.class.newInstance().from(locale).amount(value);
				
			} catch (Exception e) {
				throw new HibernateException(e);
			}
		}
		
		return null;
	}

	@Override
	public void nullSafeSet(final PreparedStatement st, final Object value, final int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, SQL_TYPES[0]);
			
		} else {
			Money money = (Money) value;
			st.setBigDecimal(index, money.toBigDecimal());
		}
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return original;
	}
	
	@Override
	public Class<?> returnedClass() {
		return Money.class;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}

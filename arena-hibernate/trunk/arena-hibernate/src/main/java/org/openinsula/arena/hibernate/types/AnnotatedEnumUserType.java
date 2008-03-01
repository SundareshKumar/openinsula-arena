package org.openinsula.arena.hibernate.types;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.openinsula.arena.hibernate.types.annotations.IntEnumValue;
import org.openinsula.arena.hibernate.types.annotations.StringEnumValue;
import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
public class AnnotatedEnumUserType implements UserType, ParameterizedType {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Class<Enum> enumClass = null;

	private boolean intEnum = false;

	private boolean stringEnum = false;

	private boolean standardEnum = false;

	@SuppressWarnings("unchecked")
	public void setParameterValues(final Properties params) {
		String enumClassName = params.getProperty("enumClass");
		if (enumClassName == null) {
			throw new MappingException("enumClass parameter not specified");
		}

		try {
			enumClass = (Class<Enum>) Class.forName(enumClassName);
		} catch (ClassNotFoundException ex) {
			throw new MappingException("enumClass " + enumClassName
					+ " not found.", ex);
		} catch (ClassCastException ex) {
			throw new MappingException("enumClass " + enumClassName
					+ " is not a Java 5 Enum.", ex);
		}

		Field[] fields = enumClass.getFields();
		for (Field field : fields) {
			if (field.getAnnotation(IntEnumValue.class) != null) {
				intEnum = true;
			} else if (field.getAnnotation(StringEnumValue.class) != null) {
				stringEnum = true;
			} else {
				standardEnum = true;
			}
		}

		if (intEnum && stringEnum && standardEnum) {
			logger
					.warn("Mixing IntEnumValue, StringEnumValue and Standard Java 5 Enum in enum: "
							+ enumClassName);
		} else if (intEnum && stringEnum) {
			logger.warn("Mixing IntEnumValue and StringEnumValue in enum: "
					+ enumClassName);
		} else if (intEnum && standardEnum) {
			logger
					.warn("Mixing IntEnumValue and Standard Java 5 Enum in enum: "
							+ enumClassName);
		} else if (stringEnum && standardEnum) {
			logger
					.warn("Mixing StringEnumValue and Standard Java 5 Enum in enum: "
							+ enumClassName);
		}
	}

	private static final int[] SQL_TYPE_VARCHAR = { Types.VARCHAR };

	private static final int[] SQL_TYPE_INTEGER = { Types.INTEGER };

	public int[] sqlTypes() {
		if (intEnum && !stringEnum && !standardEnum) {
			return SQL_TYPE_INTEGER;
		} else if (stringEnum && !intEnum && !standardEnum) {
			return SQL_TYPE_VARCHAR;
		} else {
			return SQL_TYPE_VARCHAR;
		}
	}

	@SuppressWarnings("unchecked")
	public Object nullSafeGet(final ResultSet rs, final String[] names,
			final Object owner) throws HibernateException, SQLException {
		if (intEnum && !stringEnum && !standardEnum) {
			int value = rs.getInt(names[0]);
			if (!rs.wasNull()) {
				Field[] fields = enumClass.getFields();
				for (Field field : fields) {
					if (field.getAnnotation(IntEnumValue.class).value() == value) {
						try {
							return field.get(null);
						} catch (Exception ex) {
							throw new HibernateException(ex);
						}
					}
				}
			}
		} else if (!intEnum && stringEnum && !standardEnum) {
			String value = rs.getString(names[0]);

			if (!rs.wasNull()) {
				Field[] fields = enumClass.getFields();
				for (Field field : fields) {
					StringEnumValue stringEnumValue = field
							.getAnnotation(StringEnumValue.class);

					if (stringEnumValue.trim()) {
						value = StringUtils.trimWhitespace(value);
					}

					if (stringEnumValue.toUpperCase()
							^ stringEnumValue.toLowerCase()) {
						value = stringEnumValue.toUpperCase() ? value
								.toUpperCase() : value.toLowerCase();
					}

					if (stringEnumValue != null
							&& stringEnumValue.value().equals(value)) {
						try {
							return field.get(null);
						} catch (Exception ex) {
							throw new HibernateException(ex);
						}
					}
				}
			}
		} else {
			String name = rs.getString(names[0]);
			if (!rs.wasNull()) {
				return Enum.valueOf(enumClass, name);
			}
		}

		return null;
	}

	public void nullSafeSet(final PreparedStatement st, final Object value,
			final int index) throws HibernateException, SQLException {
		if (intEnum && !stringEnum && !standardEnum) {
			if (value == null) {
				st.setNull(index, Types.INTEGER);
			} else {
				Enum e = (Enum) value;
				Field[] fields = enumClass.getFields();
				for (Field field : fields) {
					if (field.getName().equals(e.name())) {
						st.setInt(index, field
								.getAnnotation(IntEnumValue.class).value());
					}
				}
			}
		} else if (!intEnum && stringEnum && !standardEnum) {
			if (value == null) {
				st.setNull(index, Types.VARCHAR);
			} else {
				Enum e = (Enum) value;
				Field[] fields = enumClass.getFields();
				for (Field field : fields) {
					if (field.getName().equals(e.name())) {
						st.setString(index, field.getAnnotation(
								StringEnumValue.class).value());
					}
				}
			}
		} else {
			if (value == null) {
				st.setNull(index, Types.VARCHAR);
			} else {
				st.setString(index, ((Enum) value).name());
			}
		}
	}

	public Class returnedClass() {
		return enumClass;
	}

	public Object deepCopy(final Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Object assemble(final Serializable cached, final Object owner)
			throws HibernateException {
		return cached;
	}

	public Serializable disassemble(final Object value)
			throws HibernateException {
		return (Serializable) value;
	}

	public Object replace(final Object original, final Object target,
			final Object owner) throws HibernateException {
		return original;
	}

	public int hashCode(final Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean equals(final Object x, final Object y)
			throws HibernateException {
		if (x == y) {
			return true;
		}
		if (null == x || null == y) {
			return false;
		}
		return x.equals(y);
	}
}
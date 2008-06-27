/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 *
 *  This file is part of Arena Hibernate.
 *
 *  Arena Hibernate is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena Hibernate is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena Hibernate.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.hibernate.types;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.openinsula.arena.hibernate.types.annotations.IntEnumValue;
import org.openinsula.arena.hibernate.types.annotations.StringEnumValue;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
public class AnnotatedEnumUserType implements UserType, ParameterizedType {
	public static final String PARAM_ENUM_CLASS = "enumClass";

	private final Log logger = LogFactory.getLog(getClass());

	private Class<Enum> enumClass;

	private EnumTypeResolver enumTypeResolver;

	private List<EnumTypeResolver> enumTypeResolverCandidates;

	public AnnotatedEnumUserType() {
		enumTypeResolver = new EnumTypeResolver();

		enumTypeResolverCandidates = new ArrayList<EnumTypeResolver>();
		enumTypeResolverCandidates.add(new StringEnumTypeResolver());
		enumTypeResolverCandidates.add(new IntEnumTypeResolver());
	}

	public void setParameterValues(final Properties params) {
		String enumClassName = params.getProperty(PARAM_ENUM_CLASS);

		if (enumClassName == null) {
			throw new MappingException(PARAM_ENUM_CLASS + " parameter not specified");
		}

		try {
			enumClass = (Class<Enum>) Class.forName(enumClassName);
		} catch (ClassNotFoundException ex) {
			throw new MappingException("type " + enumClassName + " not found.", ex);
		} catch (ClassCastException ex) {
			throw new MappingException("type " + enumClassName + " is not a Java 5 Enum.", ex);
		}

		chooseEnumTypeResolver();
	}

	private void chooseEnumTypeResolver() {
		EnumTypeResolver firstChoice = null;

		int matches = 0;

		for (EnumTypeResolver resolver : enumTypeResolverCandidates) {
			if (resolver.matches(enumClass)) {
				if (matches == 0) {
					firstChoice = resolver;
				}
				matches++;
			}
		}

		if (matches == 0) {
			if (logger.isWarnEnabled()) {
				logger.warn("Using default EnumUserTypeResolver for " + enumClass);
			}
			enumTypeResolver = new EnumTypeResolver();

		} else if (matches == 1) {
			if (logger.isInfoEnabled()) {
				logger.info("Using " + firstChoice.getClass().getSimpleName() + " for " + enumClass);
			}
			enumTypeResolver = firstChoice;

		} else {
			throw new MappingException("Multiple EnumTypeResolver candidates found for " + enumClass);
		}

	}

	public int[] sqlTypes() {
		return new int[] { enumTypeResolver.sqlType() };
	}

	public Object nullSafeGet(final ResultSet rs, final String[] names, final Object owner) throws HibernateException, SQLException {
		Object value = enumTypeResolver.readValue(rs, names[0]);

		if (!rs.wasNull()) {
			return enumTypeResolver.getEnumConstant(value, enumClass);
		}

		return null;
	}

	public void nullSafeSet(final PreparedStatement st, final Object value, final int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, enumTypeResolver.sqlType());
		} else {
			enumTypeResolver.setEnumConstant(st, (Enum) value, index);
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

	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return cached;
	}

	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return original;
	}

	public int hashCode(final Object x) throws HibernateException {
		return ObjectUtils.nullSafeHashCode(x);
	}

	public boolean equals(final Object x, final Object y) throws HibernateException {
		return ObjectUtils.nullSafeEquals(x, y);
	}

	private class EnumTypeResolver {

		public boolean matches(final Class<Enum> enumClass) {
			return true;
		}

		public int sqlType() {
			return Types.VARCHAR;
		}

		public Object readValue(final ResultSet rs, final String name) throws SQLException {
			return rs.getString(name);
		}

		public Object getEnumConstant(final Object value, final Class<Enum> enumClass) {
			return Enum.valueOf(enumClass, value.toString());
		}

		public void setEnumConstant(final PreparedStatement st, final Enum value, final int index) throws SQLException {
			st.setString(index, value.name());
		}

	}

	private abstract class GenericEnumTypeResolver<A extends Annotation> extends EnumTypeResolver {
		private Class<A> annotationClass;

		public GenericEnumTypeResolver(final Class<A> annotationClass) {
			this.annotationClass = annotationClass;
		}

		@Override
		public boolean matches(final Class<Enum> enumClass) {
			Field[] fields = enumClass.getFields();

			int enumConstantsCount = 0;
			int annotatedEnumConstantsCount = 0;

			for (Field field : fields) {
				if (field.isEnumConstant()) {
					enumConstantsCount++;

					if (field.isAnnotationPresent(annotationClass)) {
						annotatedEnumConstantsCount++;
					}
				}
			}

			return enumConstantsCount > 0 && enumConstantsCount == annotatedEnumConstantsCount;
		}

		@Override
		public Object getEnumConstant(final Object value, final Class<Enum> enumClass) {
			Field[] fields = enumClass.getFields();

			for (Field field : fields) {
				if (field.isEnumConstant()) {
					A ann = field.getAnnotation(annotationClass);
					Object newValue = applyAnnotationParametersOnReadValue(ann, value);

					if (valueMatches(ann, newValue)) {
						try {
							return field.get(null);
						} catch (Exception ex) {
							throw new HibernateException(ex);
						}
					}
				}
			}
			return null;
		}

		@Override
		public void setEnumConstant(final PreparedStatement st, final Enum value, final int index) throws SQLException {
			Field[] fields = enumClass.getFields();

			for (Field field : fields) {

				if (field.isEnumConstant() && field.getName().equals(value.name())) {
					writeValue(st, index, field, field.getAnnotation(annotationClass));
				}
			}
		}

		protected abstract boolean valueMatches(final A ann, final Object value);

		protected abstract void writeValue(PreparedStatement st, int index, Field enumField, A annotation) throws SQLException;

		protected Object applyAnnotationParametersOnReadValue(final A ann, final Object value) {
			return value;
		}

	}

	private class StringEnumTypeResolver extends GenericEnumTypeResolver<StringEnumValue> {

		public StringEnumTypeResolver() {
			super(StringEnumValue.class);
		}

		@Override
		protected Object applyAnnotationParametersOnReadValue(final StringEnumValue ann, Object value) {
			if (ann.trim()) {
				value = StringUtils.trimWhitespace(value.toString());
			}

			if (ann.toUpperCase() ^ ann.toLowerCase()) {
				value = ann.toUpperCase() ? value.toString().toUpperCase() : value.toString().toLowerCase();
			}

			return value;
		}

		@Override
		protected boolean valueMatches(final StringEnumValue ann, final Object value) {
			return ann.value().equals(value.toString());
		}

		@Override
		protected void writeValue(final PreparedStatement st, final int index, final Field enumField, final StringEnumValue annotation) throws SQLException {
			st.setString(index, annotation.value());
		}

	};

	private class IntEnumTypeResolver extends GenericEnumTypeResolver<IntEnumValue> {

		public IntEnumTypeResolver() {
			super(IntEnumValue.class);
		}

		@Override
		public int sqlType() {
			return Types.INTEGER;
		}

		@Override
		public Object readValue(final ResultSet rs, final String name) throws SQLException {
			return rs.getInt(name);
		}

		@Override
		protected boolean valueMatches(final IntEnumValue ann, final Object value) {
			return ann.value() == (Integer) value;
		}

		@Override
		protected void writeValue(final PreparedStatement st, final int index, final Field enumField, final IntEnumValue annotation) throws SQLException {
			st.setInt(index, annotation.value());
		}

	};

}
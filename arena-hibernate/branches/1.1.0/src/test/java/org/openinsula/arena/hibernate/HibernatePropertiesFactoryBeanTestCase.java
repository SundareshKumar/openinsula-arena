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
package org.openinsula.arena.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

public class HibernatePropertiesFactoryBeanTestCase {

	@Test
	public void testMysqlProperties() throws Exception {
		MysqlHibernatePropertiesFactoryBean factoryBean = new MysqlHibernatePropertiesFactoryBean();
		Object object = factoryBean.getObject();

		assertTrue(object instanceof Properties);

		Properties props = (Properties) object;
		assertEquals("org.hibernate.dialect.MySQL5InnoDBDialect", props.get("hibernate.dialect"));
		assertEquals(true, props.get("hibernate.show_sql"));
		assertEquals(true, props.get("hibernate.format_sql"));
		assertEquals("net.sf.ehcache.hibernate.SingletonEhCacheProvider", props.get("hibernate.cache.provider_class"));
		assertEquals(true, props.get("hibernate.bytecode.use_reflection_optimizer"));
	}

	@Test
	public void testDerbyProperties() throws Exception {
		DerbyHibernatePropertiesFactoryBean factoryBean = new DerbyHibernatePropertiesFactoryBean();
		Object object = factoryBean.getObject();

		assertTrue(object instanceof Properties);

		Properties props = (Properties) object;
		assertEquals("org.hibernate.dialect.DerbyDialect", props.get("hibernate.dialect"));
	}

	@Test
	public void testHSQLProperties() throws Exception {
		HSQLHibernatePropertiesFactoryBean factoryBean = new HSQLHibernatePropertiesFactoryBean();
		Object object = factoryBean.getObject();

		assertTrue(object instanceof Properties);

		Properties props = (Properties) object;
		assertEquals("org.hibernate.dialect.HSQLDialect", props.get("hibernate.dialect"));
	}

	@Test
	public void testOracleProperties() throws Exception {
		OracleHibernatePropertiesFactoryBean factoryBean = new OracleHibernatePropertiesFactoryBean();
		Object object = factoryBean.getObject();

		assertTrue(object instanceof Properties);

		Properties props = (Properties) object;
		assertEquals("org.hibernate.dialect.Oracle10gDialect", props.get("hibernate.dialect"));
	}

	@Test
	public void testDB2Properties() throws Exception {
		DB2HibernatePropertiesFactoryBean factoryBean = new DB2HibernatePropertiesFactoryBean();
		Object object = factoryBean.getObject();

		assertTrue(object instanceof Properties);

		Properties props = (Properties) object;
		assertEquals("org.hibernate.dialect.DB2Dialect", props.get("hibernate.dialect"));
	}

}

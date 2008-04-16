/*
 *  (C) Copyright 2007 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena Test.
 *
 *  Arena Test is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena Test is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena Test.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.test.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class MysqlTestDataSourceFactoryBeanTestCase {

	@Test
	public void testGetObject() throws Exception {
		MysqlTestDataSourceFactoryBean factoryBean = new MysqlTestDataSourceFactoryBean();

		Object object = factoryBean.getObject();

		assertNotNull(object);
		assertTrue(object instanceof DataSource);
		assertTrue(object instanceof BasicDataSource);

		DataSource dataSource = (DataSource) object;

		SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		assertEquals(1, simpleJdbcTemplate.queryForInt("select 1"));
	}

	@Test
	public void testGetJdbcUrl() {
		MysqlTestDataSourceFactoryBean factoryBean = new MysqlTestDataSourceFactoryBean();

		factoryBean.setDatabaseName("testcase");
		assertEquals("jdbc:mysql://localhost:3306/testcase", factoryBean.getJdbcUrl());

		factoryBean.setHost("127.0.0.1");
		assertEquals("jdbc:mysql://127.0.0.1:3306/testcase", factoryBean.getJdbcUrl());

		factoryBean.setPort("123");
		assertEquals("jdbc:mysql://127.0.0.1:123/testcase", factoryBean.getJdbcUrl());

		factoryBean.setDatabaseName("bla");
		assertEquals("jdbc:mysql://127.0.0.1:123/bla", factoryBean.getJdbcUrl());
	}

}

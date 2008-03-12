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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class DerbyTestDataSourceFactoryBeanTestCase {

	@Test
	public void testGetObject() throws Exception {
		DerbyTestDataSourceFactoryBean factoryBean = new DerbyTestDataSourceFactoryBean();

		factoryBean.setDatabaseName("testcase");

		Object object = factoryBean.getObject();

		assertNotNull(object);
		assertTrue(object instanceof DataSource);
		assertTrue(object instanceof BasicDataSource);

		DataSource dataSource = (DataSource) object;

		assertNotNull(dataSource.getConnection());

		assertTrue(factoryBean.databaseDir.exists());
		factoryBean.destroy();
		assertFalse(factoryBean.databaseDir.exists());
	}

	@Test
	public void testGetJdbcUrl() {
		DerbyTestDataSourceFactoryBean factoryBean = new DerbyTestDataSourceFactoryBean();

		factoryBean.setDatabaseName("testcase");
		String url = factoryBean.getJdbcUrl();
		assertEquals("jdbc:derby:" + factoryBean.databaseDir.getAbsolutePath() + ";create=true", url);

		factoryBean.setDatabaseName("bla");
		url = factoryBean.getJdbcUrl();
		assertEquals("jdbc:derby:" + factoryBean.databaseDir.getAbsolutePath() + ";create=true", url);
	}

}

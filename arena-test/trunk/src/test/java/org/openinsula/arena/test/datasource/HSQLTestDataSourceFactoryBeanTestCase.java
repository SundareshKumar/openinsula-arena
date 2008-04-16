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

import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class HSQLTestDataSourceFactoryBeanTestCase {

	@Test
	public void testGetObject() throws Exception {
		HSQLTestDataSourceFactoryBean factoryBean = new HSQLTestDataSourceFactoryBean();

		Object object = factoryBean.getObject();

		assertNotNull(object);
		assertTrue(object instanceof DataSource);
		assertTrue(object instanceof SingleConnectionDataSource);

		DataSource dataSource = (DataSource) object;

		assertNotNull(dataSource.getConnection());
	}

	@Test
	public void testGetJdbcUrl() {
		HSQLTestDataSourceFactoryBean factoryBean = new HSQLTestDataSourceFactoryBean();

		String url = factoryBean.getJdbcUrl();
		assertEquals("jdbc:hsqldb:mem:/testcase", url);
	}

}

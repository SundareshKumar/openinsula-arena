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
package org.openinsula.arena.test.sql;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.openinsula.arena.test.datasource.DerbyTestDataSourceFactoryBean;
import org.openinsula.arena.test.datasource.MysqlTestDataSourceFactoryBean;

public class SqlTemplateExecutorTestCase {

	@Test
	public void testReadSql() throws Exception {
		SqlTemplateExecutor executor = new SqlTemplateExecutor();

		List<String> sqlList = executor.readSql("/sql/dne.sql");

		assertEquals(27, sqlList.size());
	}

	@Test
	public void testMysql() throws Exception {
		MysqlTestDataSourceFactoryBean factoryBean = new MysqlTestDataSourceFactoryBean();
		DataSource dataSource = (DataSource) factoryBean.getObject();

		testExecute(dataSource);
	}

	@Test
	public void testDerby() throws Exception {
		DerbyTestDataSourceFactoryBean factoryBean = new DerbyTestDataSourceFactoryBean();
		DataSource dataSource = (DataSource) factoryBean.getObject();

		testExecute(dataSource);
	}

	public void testExecute(DataSource dataSource) throws Exception {
		Connection conn = dataSource.getConnection();

		SqlTemplateExecutor executor = new SqlTemplateExecutor();
		executor.setConnection(conn);
		executor.execute("/sql/dne.sql");

		ResultSet rs = conn.getMetaData().getTables(null, null, "DNE%", null);

		int count = 0;
		while (rs.next()) {
			count++;
		}

		assertEquals(11, count);
	}

}

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

import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class HSQLTestDataSourceFactoryBean extends AbstractTestDataSourceFactoryBean implements DisposableBean {

	private static final long serialVersionUID = 1L;

	protected SingleConnectionDataSource dataSource = null;

	public Object getObject() throws Exception {
		dataSource = doCreateDataSource();

		fireBeforeDropDatabase(dataSource);
		fireAfterDropDatabase(dataSource);
		fireBeforeCreateDatabase(dataSource);
		fireAfterCreateDatabase(dataSource);

		return dataSource;
	}

	private SingleConnectionDataSource doCreateDataSource() {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();

		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl(getJdbcUrl());
		dataSource.setSuppressClose(true);

		return dataSource;
	}

	public void destroy() throws Exception {
		dataSource.setSuppressClose(false);
		dataSource.getConnection().close();
	}

	protected String getJdbcUrl() {
		return String.format("jdbc:hsqldb:mem:/testcase");
	}

}

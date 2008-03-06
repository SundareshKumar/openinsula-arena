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

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.openinsula.arena.test.datasource.listener.TestDataSourceListener;
import org.springframework.beans.factory.FactoryBean;

public abstract class AbstractTestDataSourceFactoryBean implements FactoryBean {

	protected List<TestDataSourceListener> listenerList = new LinkedList<TestDataSourceListener>();

	protected String databaseName = "testcase";

	public Class<?> getObjectType() {
		return DataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	protected void fireBeforeDropDatabase(DataSource dataSource) throws SQLException {
		for (TestDataSourceListener listener : listenerList) {
			listener.beforeDropDatabase(dataSource, databaseName);
		}
	}

	protected void fireAfterDropDatabase(DataSource dataSource) throws SQLException {
		for (TestDataSourceListener listener : listenerList) {
			listener.beforeDropDatabase(dataSource, databaseName);
		}
	}

	protected void fireBeforeCreateDatabase(DataSource dataSource) throws SQLException {
		for (TestDataSourceListener listener : listenerList) {
			listener.beforeCreateDatabase(dataSource, databaseName);
		}
	}

	protected void fireAfterCreateDatabase(DataSource dataSource) throws SQLException {
		for (TestDataSourceListener listener : listenerList) {
			listener.afterCreateDatabase(dataSource, databaseName);
		}
	}

	public List<TestDataSourceListener> getListenerList() {
		return listenerList;
	}

	public void setListenerList(List<TestDataSourceListener> listenerList) {
		this.listenerList = listenerList;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

}

/*
 *  (C) Copyright 2006 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena DNE.
 *
 *  Arena DNE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena DNE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena DNE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.dne.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.dataset.stream.StreamingDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.openinsula.arena.test.datasource.listener.TestDataSourceListener;
import org.openinsula.arena.test.sql.SqlTemplateExecutor;
import org.xml.sax.InputSource;

public class PopulateDneTestDataSourceListenerImpl implements TestDataSourceListener {

	@Override
	public void afterCreateDatabase(DataSource dataSource, String databaseName) throws Exception {
		Connection conn = dataSource.getConnection();

		SqlTemplateExecutor executor = new SqlTemplateExecutor();

		executor.setConnection(conn);
		executor.execute("/sql/dne.sql");

		IDataSetProducer producer = new FlatXmlProducer(new InputSource(new GZIPInputStream(getClass()
				.getResourceAsStream("/data/dataset.xml.gz"))));

		IDataSet dataSet = new StreamingDataSet(producer);

		IDatabaseConnection connection = new DatabaseConnection(conn);
		connection.getConfig().setFeature("http://www.dbunit.org/features/batchedStatements", true);

		DatabaseOperation.INSERT.execute(connection, dataSet);
	}

	@Override
	public void afterDropDatabase(DataSource dataSource, String databaseName) throws SQLException {
	}

	@Override
	public void beforeCreateDatabase(DataSource dataSource, String databaseName) throws SQLException {
	}

	@Override
	public void beforeDropDatabase(DataSource dataSource, String databaseName) throws SQLException {
	}

}

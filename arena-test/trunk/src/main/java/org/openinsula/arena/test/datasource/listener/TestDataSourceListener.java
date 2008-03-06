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
package org.openinsula.arena.test.datasource.listener;

import java.sql.SQLException;

import javax.sql.DataSource;

public interface TestDataSourceListener {

	public void beforeDropDatabase(DataSource dataSource, String database) throws SQLException;

	public void afterDropDatabase(DataSource dataSource, String database) throws SQLException;

	public void beforeCreateDatabase(DataSource dataSource, String database) throws SQLException;

	public void afterCreateDatabase(DataSource dataSource, String database) throws SQLException;

}

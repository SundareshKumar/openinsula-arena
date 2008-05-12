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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlTemplateExecutor {

	protected final Log logger = LogFactory.getLog(getClass());

	protected Connection connection;

	public void execute(String resourceName) throws Exception {
		Statement st = connection.createStatement();

		List<String> sqlList = readSql(resourceName);

		for (String sql : sqlList) {
			if (logger.isDebugEnabled()) {
				logger.debug("Executing sql statement: " + sql);
			}

			st.execute(sql);
		}
		
		st.close();
	}

	protected List<String> readSql(String resourceName) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Reading sql resource: " + resourceName);
		}

		List<String> sqlList = new LinkedList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(SqlTemplateExecutor.class.getResourceAsStream(resourceName)));

		String s = null;

		while ((s = parseNextSqlCommand(br)) != null) {
			sqlList.add(s);
		}

		return sqlList;
	}

	protected String parseNextSqlCommand(BufferedReader br) throws IOException {
		String s = null;

		StringBuilder sb = new StringBuilder();

		while ((s = br.readLine()) != null) {
			String trim = s.trim();
			if (!"".equals(trim)) {
				sb.append(trim);

				if (trim.endsWith(";")) {
					String sql = sb.toString().trim();

					return sql.substring(0, sql.length() - 1);
				}
			}
		}

		return null;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}

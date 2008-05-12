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

import java.sql.Connection;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.DisposableBean;

/**
 * Factory bean que cria um DataSource para ser utilizado nos TestCases de banco
 * de dados. <br>
 * Esta implementação é específica para o <strong>MySQL</strong>, e executa um
 * "drop database" e um "create database" ao ser inicializado.
 * @author yanaga
 */
public class MysqlTestDataSourceFactoryBean extends AbstractTestDataSourceFactoryBean implements DisposableBean {

	private static final long serialVersionUID = 1L;

	private BasicDataSource dataSource;

	private String host = "localhost";

	private String port = "3306";

	private String username = "testcase";

	private String password = "testcase";

	public Object getObject() throws Exception {
		dataSource = doCreateDataSource();

		Connection conn = dataSource.getConnection();
		Statement st = conn.createStatement();

		fireBeforeDropDatabase(dataSource);
		st.execute("drop database " + databaseName);
		fireAfterDropDatabase(dataSource);

		fireBeforeCreateDatabase(dataSource);
		st.execute("create database " + databaseName);
		st.execute("use " + databaseName);
		fireAfterCreateDatabase(dataSource);
		
		st.close();
		conn.close();

		return dataSource;
	}

	@Override
	public void destroy() throws Exception {
		dataSource.close();
	}

	private BasicDataSource doCreateDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		dataSource.setUrl(getJdbcUrl());
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		return dataSource;
	}

	protected String getJdbcUrl() {
		return String.format("jdbc:mysql://%s:%s/%s", host, port, databaseName);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

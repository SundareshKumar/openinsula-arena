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

import java.io.File;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.DisposableBean;

/**
 * Factory bean que cria um DataSource para ser utilizado nos TestCases de banco
 * de dados. <br>
 * Esta implementação é específica para o <strong>Derby</strong>, e cria o
 * banco de dados no diretorio apontado pela propriedade "java.io.tmpdir". O
 * diretorio é removido ao se executar o método destroy().
 * @author yanaga
 */
public class DerbyTestDataSourceFactoryBean extends AbstractTestDataSourceFactoryBean implements DisposableBean {

	private static final long serialVersionUID = 1L;

	protected BasicDataSource basicDataSource = null;

	protected File databaseDir;

	public Object getObject() throws Exception {
		basicDataSource = doCreateDataSource();

		fireBeforeDropDatabase(basicDataSource);
		fireAfterDropDatabase(basicDataSource);
		fireBeforeCreateDatabase(basicDataSource);
		fireAfterCreateDatabase(basicDataSource);

		return basicDataSource;
	}

	private BasicDataSource doCreateDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl(getJdbcUrl());

		return dataSource;
	}

	public void destroy() throws Exception {
		basicDataSource.close();

		deleteDir(databaseDir);
	}

	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

	protected File createDatabaseDirectory() {
		return new File(System.getProperty("java.io.tmpdir"), databaseName + (int) (Math.random() * 1000 * 1000 * 1000));
	}

	protected String getJdbcUrl() {
		databaseDir = createDatabaseDirectory();

		StringBuilder sb = new StringBuilder();

		sb.append("jdbc:derby:");
		sb.append(databaseDir.getAbsolutePath());
		sb.append(";create=true");

		return sb.toString();
	}

}

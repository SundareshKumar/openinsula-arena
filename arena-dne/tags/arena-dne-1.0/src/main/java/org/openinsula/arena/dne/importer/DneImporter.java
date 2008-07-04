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
package org.openinsula.arena.dne.importer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.dne.importer.service.ImportService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DneImporter {

	private static Log logger = LogFactory.getLog(DneImporter.class);

	public static void main(String[] args) throws IOException, SQLException {
		if (args.length != 1) {
			logger.warn("Deve ser especificado o diretorio com os arquivos do DNE.");
			System.exit(-1);
		}

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:META-INF/insula-dne-import-context.xml",
						"classpath*:META-INF/derby-datasource-context.xml" });

		ImportService importService = (ImportService) ctx.getBean("importService");
		importService.process(new File(args[0]));

		DataSource dataSource = (DataSource) ctx.getBean("dneDataSource");
		Connection conn = dataSource.getConnection();
		String databaseName = conn.getMetaData().getDatabaseProductName();
		if (databaseName != null && databaseName.startsWith("HSQL")) {
			conn.createStatement().execute("shutdown");
		}
		conn.close();
	}
}

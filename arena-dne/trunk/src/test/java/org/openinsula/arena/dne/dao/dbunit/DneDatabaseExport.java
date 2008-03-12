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
package org.openinsula.arena.dne.dao.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.ForwardOnlyResultSetTableFactory;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DneDatabaseExport {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dne", "dne", "dne");

		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_RESULTSET_TABLE_FACTORY,
				new ForwardOnlyResultSetTableFactory());

		QueryDataSet partialDataSet = new QueryDataSet(connection);

		partialDataSet.addTable("DNE_GU_PAISES");
		partialDataSet.addTable("DNE_GU_UNIDADES_FEDERACAO");
		partialDataSet.addTable("DNE_GU_LOCALIDADES");
		partialDataSet.addTable("DNE_GU_BAIRROS");
		partialDataSet
				.addTable("DNE_GU_LOGRADOUROS",
						"select * from DNE_GU_LOGRADOUROS where chave_logradouro_dne in (84,86,479263,842,677862,46936,117,41819,1315)");
		partialDataSet.addTable("DNE_GU_LOGRADOUROS_SEC",
				"select * from DNE_GU_LOGRADOUROS_SEC where chave_secc_dne in (29)");
		partialDataSet.addTable("DNE_GU_LOGRADOUROS_NUM_LOTE",
				"select * from DNE_GU_LOGRADOUROS_NUM_LOTE where chave_lot_dne in (32129,3254)");
		partialDataSet.addTable("DNE_GU_LOGRADOUROS_COMPL1",
				"select * from DNE_GU_LOGRADOUROS_COMPL1 where chave_compl1_dne in (21113)");
		partialDataSet.addTable("DNE_GU_GRANDES_USUARIOS",
				"select * from DNE_GU_GRANDES_USUARIOS where chave_gu_dne in (4,471)");
		partialDataSet.addTable("DNE_GU_CAIXAS_POSTAIS_COMUNIT",
				"select * from DNE_GU_CAIXAS_POSTAIS_COMUNIT where chave_cx_postal_comu in (1285)");
		partialDataSet.addTable("DNE_GU_UNIDADES_OPERACIONAIS",
				"select * from DNE_GU_UNIDADES_OPERACIONAIS where chave_unid_oper_dne in (10)");

		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("/home/yanaga/partial.xml"));
	}

}

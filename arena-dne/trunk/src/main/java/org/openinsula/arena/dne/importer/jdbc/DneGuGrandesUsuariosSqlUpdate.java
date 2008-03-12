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
package org.openinsula.arena.dne.importer.jdbc;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DneGuGrandesUsuariosSqlUpdate extends SqlUpdate {
	public DneGuGrandesUsuariosSqlUpdate() {
		setSql("insert into DNE_GU_GRANDES_USUARIOS(" + "chave_gu_dne," + "sigla_uf," + "chave_loc_dne,"
				+ "chave_bai_dne," + "nome_ofi_gu," + "cep_gu," + "abrev_gu_rec_ect," + "tipo_ofi_logradouro,"
				+ "preposicao," + "tit_pat_ofi_log," + "chave_logradouro_dne," + "nome_ofi_log," + "num_lote,"
				+ "nome_compl1," + "num_letr_compl1," + "nome_compl2," + "num_letr_compl2," + "tipo_ofi_unid_ocup,"
				+ "num_letr_unid_ocup " + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.NUMERIC));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
	}

}

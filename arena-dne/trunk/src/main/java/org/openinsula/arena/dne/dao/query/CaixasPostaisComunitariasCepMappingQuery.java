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
package org.openinsula.arena.dne.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;

import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class CaixasPostaisComunitariasCepMappingQuery extends MappingSqlQuery {

	public CaixasPostaisComunitariasCepMappingQuery() {
		setSql("select " + "cai.cep_pt_cx_postal_comu as cep," + "cai.end_pt_cx_postal_comu as endereco,"
				+ "cai.sigla_uf as uf," + "cai.chave_loc_dne," + "loc.chave_loc_dne as chaveDne,"
				+ "loc.nome_ofi_localidade as localidade " + "from " + "DNE_GU_CAIXAS_POSTAIS_COMUNIT cai, "
				+ "DNE_GU_LOCALIDADES loc " + "where " + "cai.chave_loc_dne = loc.chave_loc_dne and "
				+ "cai.cep_pt_cx_postal_comu = ?");
		declareParameter(new SqlParameter("cep", Types.INTEGER));
	}

	@Override
	protected Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Endereco endereco = new Endereco();

		endereco.setCep(new DecimalFormat("00000000").format(rs.getInt("cep")));
		endereco.setUf(Uf.valueOf(rs.getString("uf")));
		endereco.setLocalidade(rs.getString("localidade"));
		endereco.setLogradouro(rs.getString("endereco"));
		endereco.setChaveDne(rs.getLong("chaveDne"));

		return endereco;
	}

}

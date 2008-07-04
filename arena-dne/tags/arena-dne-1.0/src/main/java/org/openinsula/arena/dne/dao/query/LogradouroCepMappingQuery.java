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

import org.openinsula.arena.dne.StringUtils;
import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class LogradouroCepMappingQuery extends MappingSqlQuery {

	public LogradouroCepMappingQuery() {
		setSql("select " + "log.tipo_ofi_logradouro as tipo," + "log.preposicao as preposicao,"
				+ "log.tit_pat_ofi_logradouro as titulo," + "log.nome_ofi_logradouro as logradouro,"
				+ "log.cep_logradouro as cep," + "log.sigla_uf as uf," + "log.chave_bai_ini_dne,"
				+ "log.chave_loc_dne," + "loc.chave_loc_dne as chaveDne," + "loc.nome_ofi_localidade as localidade,"
				+ "bai.chave_bai_dne," + "bai.nome_ofi_bai as bairro " + "from " + "DNE_GU_LOGRADOUROS log, "
				+ "DNE_GU_LOCALIDADES loc, " + "DNE_GU_BAIRROS bai " + "where "
				+ "log.chave_loc_dne = loc.chave_loc_dne and " + "log.chave_bai_ini_dne = bai.chave_bai_dne and "
				+ "log.cep_logradouro = ?");
		declareParameter(new SqlParameter("cep", Types.INTEGER));
	}

	@Override
	protected Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Endereco endereco = new Endereco();

		endereco.setCep(new DecimalFormat("00000000").format(rs.getInt("cep")));
		endereco.setUf(Uf.valueOf(rs.getString("uf")));
		endereco.setLocalidade(rs.getString("localidade"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setChaveDne(rs.getLong("chaveDne"));

		StringBuilder sb = new StringBuilder();
		sb.append(rs.getString("tipo"));
		sb.append(" ");
		String s = rs.getString("preposicao");
		if (!StringUtils.isBlank(s)) {
			sb.append(s);
			sb.append(" ");
		}
		s = rs.getString("titulo");
		if (!StringUtils.isBlank(s)) {
			sb.append(s);
			sb.append(" ");
		}
		sb.append(rs.getString("logradouro"));
		endereco.setLogradouro(sb.toString());

		return endereco;
	}

}

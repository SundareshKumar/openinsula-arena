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
import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class UnidadesOperacionaisParameterMappingQuery extends MappingSqlQuery {

	public UnidadesOperacionaisParameterMappingQuery() {
		setSql("select " + "uni.cep_unid_oper as cep," + "uni.tipo_ofi_log as tipo," + "uni.preposicao as preposicao,"
				+ "uni.tit_pat_ofi_log as titulo," + "uni.num_lote as numeroLote,"
				+ "uni.nome_compl1 as nomeComplemento," + "uni.num_let_compl1 as numeroComplemento,"
				+ "uni.chave_logradouro_dne," + "uni.sigla_uf as uf," + "uni.chave_loc_dne," + "uni.chave_bai_dne,"
				+ "log.chave_logradouro_dne," + "log.nome_ofi_logradouro as logradouro," + "loc.chave_loc_dne,"
				+ "loc.nome_ofi_localidade as localidade," + "bai.chave_bai_dne," + "bai.nome_ofi_bai as bairro "
				+ "from " + "DNE_GU_LOGRADOUROS log, " + "DNE_GU_BAIRROS bai, " + "DNE_GU_UNIDADES_OPERACIONAIS uni, "
				+ "DNE_GU_LOCALIDADES loc " + "where " + "uni.chave_loc_dne = loc.chave_loc_dne and "
				+ "uni.chave_logradouro_dne = log.chave_logradouro_dne and "
				+ "uni.chave_bai_dne = bai.chave_bai_dne and " + "log.nome_ofi_logradouro like ? and "
				+ "uni.num_lote like ? and " + "loc.nome_ofi_localidade like ? and " + "uni.sigla_uf = ?");
		declareParameter(new SqlParameter("logradouro", Types.VARCHAR));
		declareParameter(new SqlParameter("numero", Types.VARCHAR));
		declareParameter(new SqlParameter("localidade", Types.VARCHAR));
		declareParameter(new SqlParameter("uf", Types.VARCHAR));
	}

	@Override
	protected Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		BuscaEndereco endereco = new BuscaEndereco();

		endereco.setCep(new DecimalFormat("00000000").format(rs.getInt("cep")));
		endereco.setUf(Uf.valueOf(rs.getString("uf")));
		endereco.setLocalidade(rs.getString("localidade"));
		endereco.setBairro(rs.getString("bairro"));
		endereco.setNumero(rs.getString("numeroLote"));

		StringBuilder sb = new StringBuilder();
		sb.append(rs.getString("tipo"));
		sb.append(" ");
		String preposicao = rs.getString("preposicao");
		if (!StringUtils.isBlank(preposicao)) {
			sb.append(preposicao.trim());
			sb.append(" ");
		}
		String titulo = rs.getString("titulo");
		if (!StringUtils.isBlank(titulo)) {
			sb.append(titulo.trim());
			sb.append(" ");
		}
		sb.append(rs.getString("logradouro"));
		endereco.setLogradouro(sb.toString());

		String nomeComplemento = rs.getString("nomeComplemento");
		if (!StringUtils.isBlank(nomeComplemento)) {
			sb = new StringBuilder();
			sb.append(nomeComplemento.trim());
			sb.append(" ");
			sb.append(rs.getString("numeroComplemento"));
			endereco.setComplemento(sb.toString());
		}

		return endereco;
	}

}

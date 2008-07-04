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
import java.util.List;

import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class LocalidadeParameterMappingQuery extends MappingSqlQuery {

	public LocalidadeParameterMappingQuery() {
		setSql("select " + "cep_localidade as cep," + "sigla_uf as uf," + "nome_ofi_localidade as localidade "
				+ "from DNE_GU_LOCALIDADES " + "where " + "nome_ofi_localidade like ? and " + "sigla_uf = ? and "
				+ "cep_localidade is not null");
		declareParameter(new SqlParameter("localidade", Types.VARCHAR));
		declareParameter(new SqlParameter("uf", Types.VARCHAR));
	}

	@Override
	protected Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		BuscaEndereco endereco = new BuscaEndereco();
		endereco.setCep(rs.getString("cep"));
		endereco.setUf(Uf.valueOf(rs.getString("uf")));
		endereco.setLocalidade(rs.getString("localidade"));

		return endereco;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BuscaEndereco> execute(Object[] params) throws DataAccessException {
		return super.execute(new Object[] { params[2], params[3] });
	}

}

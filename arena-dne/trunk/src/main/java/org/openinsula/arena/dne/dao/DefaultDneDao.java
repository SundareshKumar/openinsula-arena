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
package org.openinsula.arena.dne.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.openinsula.arena.dne.dao.query.CaixasPostaisComunitariasCepMappingQuery;
import org.openinsula.arena.dne.dao.query.CaixasPostaisComunitariasParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.GrandesUsuariosCepMappingQuery;
import org.openinsula.arena.dne.dao.query.GrandesUsuariosParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.LocalidadeCepMappingQuery;
import org.openinsula.arena.dne.dao.query.LocalidadeParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroCepMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroComplemento1CepMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroComplemento1ParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroNumeroLoteCepMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroNumeroLoteParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroSeccionamentoCepMappingQuery;
import org.openinsula.arena.dne.dao.query.LogradouroSeccionamentoParameterMappingQuery;
import org.openinsula.arena.dne.dao.query.UnidadesOperacionaisCepMappingQuery;
import org.openinsula.arena.dne.dao.query.UnidadesOperacionaisParameterMappingQuery;
import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Localidade;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * Implementação de {@link DneDao} baseado em JDBC. Utiliza várias
 * {@link MappingSqlQuery} para executar as buscas.
 * 
 * @author yanaga
 * @since 1.0
 */
public class DefaultDneDao implements DneDao {
	private List<MappingSqlQuery> findByCepQueryList = new LinkedList<MappingSqlQuery>();

	private List<MappingSqlQuery> findByParameterQueryList = new LinkedList<MappingSqlQuery>();

	private DataSource dataSource;

	public DefaultDneDao() {
		findByCepQueryList.add(new LocalidadeCepMappingQuery());
		findByCepQueryList.add(new LogradouroCepMappingQuery());
		findByCepQueryList.add(new LogradouroSeccionamentoCepMappingQuery());
		findByCepQueryList.add(new LogradouroNumeroLoteCepMappingQuery());
		findByCepQueryList.add(new LogradouroComplemento1CepMappingQuery());
		findByCepQueryList.add(new GrandesUsuariosCepMappingQuery());
		findByCepQueryList.add(new CaixasPostaisComunitariasCepMappingQuery());
		findByCepQueryList.add(new UnidadesOperacionaisCepMappingQuery());

		findByParameterQueryList.add(new LocalidadeParameterMappingQuery());
		findByParameterQueryList.add(new LogradouroParameterMappingQuery());
		findByParameterQueryList.add(new LogradouroSeccionamentoParameterMappingQuery());
		findByParameterQueryList.add(new LogradouroNumeroLoteParameterMappingQuery());
		findByParameterQueryList.add(new LogradouroComplemento1ParameterMappingQuery());
		findByParameterQueryList.add(new GrandesUsuariosParameterMappingQuery());
		findByParameterQueryList.add(new CaixasPostaisComunitariasParameterMappingQuery());
		findByParameterQueryList.add(new UnidadesOperacionaisParameterMappingQuery());
	}

	public Localidade findLocalidade(Long chaveLocalidadeDne) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return (Localidade) jdbcTemplate.queryForObject("select " + "chave_loc_dne as chave,"
				+ "nome_ofi_localidade as localidade," + "sigla_uf as uf " + "from DNE_GU_LOCALIDADES " + "where "
				+ "chave_loc_dne = ?", new Object[] { chaveLocalidadeDne }, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Localidade localidade = new Localidade();
				localidade.setChaveDne(rs.getLong("chave"));
				localidade.setNomeOficial(rs.getString("localidade"));
				localidade.setUf(Uf.valueOf(rs.getString("uf")));

				return localidade;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Localidade> findLocalidade(String nome, Uf uf) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query("select " + "chave_loc_dne as chave," + "nome_ofi_localidade as localidade,"
				+ "sigla_uf as uf " + "from DNE_GU_LOCALIDADES " + "where " + "nome_ofi_localidade like ? and "
				+ "sigla_uf = ?", new Object[] { createLikeParameter(nome), uf.toString() }, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Localidade localidade = new Localidade();
				localidade.setChaveDne(rs.getLong("chave"));
				localidade.setNomeOficial(rs.getString("localidade"));
				localidade.setUf(Uf.valueOf(rs.getString("uf")));

				return localidade;
			}
		});
	}

	public Endereco findEnderecoByCep(String cep) {
		for (MappingSqlQuery query : findByCepQueryList) {
			query.setDataSource(dataSource);
			Endereco endereco = (Endereco) query.findObject(Integer.parseInt(cep));
			if (endereco != null) {
				return endereco;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<BuscaEndereco> findEnderecoByParameter(String logradouro, String numero, String localidade, Uf uf) {
		List<BuscaEndereco> enderecos = new ArrayList<BuscaEndereco>();
		for (MappingSqlQuery query : findByParameterQueryList) {
			query.setDataSource(dataSource);
			enderecos.addAll(query.execute(new Object[] { createLikeParameter(logradouro), createLikeParameter(numero),
					createLikeParameter(localidade), uf }));
		}

		return enderecos;
	}

	/**
	 * Método que concatena o caracter % no início e no fim da {@link String}
	 * para ser utilizado como <code>LIKE</code> em SQLs.
	 * @param s A {@link String} para ser concatenada.
	 * @return A {@link String} pronta para ser utilizada como parâmetro para
	 * <code>LIKE</code>.
	 */
	protected String createLikeParameter(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("%");
		sb.append(s);
		sb.append("%");

		return sb.toString();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}

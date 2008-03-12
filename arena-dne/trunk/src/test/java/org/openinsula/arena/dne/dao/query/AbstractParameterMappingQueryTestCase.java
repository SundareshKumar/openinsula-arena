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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public abstract class AbstractParameterMappingQueryTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testLocalidadeParameterMappingQuery() {
		LocalidadeParameterMappingQuery mappingQuery = new LocalidadeParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { null, null, "%LUIS ANTONIO%", Uf.SP });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	public void testLogradouroParameterMappingQuery() {
		LogradouroParameterMappingQuery mappingQuery = new LogradouroParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%RIO BRANCO%", null, "%RIO BRANCO%",
				Uf.AC });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	public void testLogradouroSeccionamentoParameterMappingQuery() {
		LogradouroSeccionamentoParameterMappingQuery mappingQuery = new LogradouroSeccionamentoParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery
				.execute(new Object[] { "%AL-220%", "30", "%ARAPIRACA%", Uf.AL });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testLogradouroNumeroLoteParameterMappingQuery() {
		LogradouroNumeroLoteParameterMappingQuery mappingQuery = new LogradouroNumeroLoteParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%SUL AVENIDA%", "%NS 2%", "%PALMAS%",
				Uf.TO });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testLogradouroComplemento1ParameterMappingQuery() {
		LogradouroComplemento1ParameterMappingQuery mappingQuery = new LogradouroComplemento1ParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%SMDB AREA ESPECIAL%", "%12%",
				"%LAGO SUL%", Uf.DF });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGrandesUsuariosParameterMappingQuery() {
		GrandesUsuariosParameterMappingQuery mappingQuery = new GrandesUsuariosParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%BRASIL%", "%378%", "%RIO BRANCO%",
				Uf.AC });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testCaixasPostaisComunitariasParameterMappingQuery() {
		CaixasPostaisComunitariasParameterMappingQuery mappingQuery = new CaixasPostaisComunitariasParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%QUADRA 1 NO 37%", "", "%RIO LARGO%",
				Uf.AL });
		assertTrue(enderecoList.size() > 0);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnidadesOperacionaisParameterMappingQuery() {
		UnidadesOperacionaisParameterMappingQuery mappingQuery = new UnidadesOperacionaisParameterMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		List<BuscaEndereco> enderecoList = mappingQuery.execute(new Object[] { "%LUIZ PEREIRA LIMA%", "%%",
				"%ARAPIRACA%", Uf.AL });
		assertTrue(enderecoList.size() > 0);
	}

}

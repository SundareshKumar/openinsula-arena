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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Uf;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public abstract class AbstractCepMappingQueryTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testLocalidadeCepMappingQuery() {
		LocalidadeCepMappingQuery mappingQuery = new LocalidadeCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(14210000);
		assertNotNull(endereco);
		assertEquals("LUIS ANTONIO", endereco.getLocalidade());
		assertEquals(Uf.SP, endereco.getUf());
		assertEquals("14210000", endereco.getCep());
	}

	@Test
	public void testLogradouroCepMappingQuery() {
		LogradouroCepMappingQuery mappingQuery = new LogradouroCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(69908340);
		assertNotNull(endereco);
		assertEquals("RUA DO BARAO RIO BRANCO", endereco.getLogradouro());
		assertEquals("RIO BRANCO", endereco.getLocalidade());
		assertEquals("BOSQUE", endereco.getBairro());
		assertEquals(Uf.AC, endereco.getUf());
		assertEquals("69908340", endereco.getCep());

		endereco = (Endereco) mappingQuery.findObject(69911250);
		assertNotNull(endereco);
		assertEquals("ALAMEDA BARCELONA", endereco.getLogradouro());
		assertEquals("RIO BRANCO", endereco.getLocalidade());
		assertEquals("JARDIM EUROPA", endereco.getBairro());
		assertEquals(Uf.AC, endereco.getUf());
		assertEquals("69911250", endereco.getCep());

		endereco = (Endereco) mappingQuery.findObject(1025010);
		assertNotNull(endereco);
		assertEquals("RUA AUGUSTO SEVERO", endereco.getLogradouro());
		assertEquals("SAO PAULO", endereco.getLocalidade());
		assertEquals("CENTRO", endereco.getBairro());
		assertEquals(Uf.SP, endereco.getUf());
		assertEquals("01025010", endereco.getCep());
	}

	@Test
	public void testLogradouroSeccionamentoCepMappingQuery() {
		LogradouroSeccionamentoCepMappingQuery mappingQuery = new LogradouroSeccionamentoCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(57308000);
		assertNotNull(endereco);
		assertEquals("RODOVIA AL-220", endereco.getLogradouro());
		assertEquals("ARAPIRACA", endereco.getLocalidade());
		assertEquals("PLANALTO", endereco.getBairro());
		assertEquals(Uf.AL, endereco.getUf());
		assertEquals("57308000", endereco.getCep());
	}

	@Test
	public void testLogradouroNumeroLoteCepMappingQuery() {
		LogradouroNumeroLoteCepMappingQuery mappingQuery = new LogradouroNumeroLoteCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(77019526);
		assertNotNull(endereco);
		assertEquals(" 1204 SUL AVENIDA", endereco.getLogradouro());
		assertEquals("NS 2", endereco.getNumero());
		assertEquals("PALMAS", endereco.getLocalidade());
		assertEquals("PLANO DIRETOR SUL", endereco.getBairro());
		assertEquals(Uf.TO, endereco.getUf());
		assertEquals("77019526", endereco.getCep());
	}

	@Test
	public void testLogradouroComplemento1CepMappingQuery() {
		LogradouroComplemento1CepMappingQuery mappingQuery = new LogradouroComplemento1CepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(71680111);
		assertNotNull(endereco);
		assertEquals(" SMDB AREA ESPECIAL", endereco.getLogradouro());
		assertEquals("12", endereco.getNumero());
		assertEquals("BLOCO A", endereco.getComplemento());
		assertEquals("LAGO SUL", endereco.getLocalidade());
		assertEquals("SETOR DE MANSOES DOM BOSCO", endereco.getBairro());
		assertEquals(Uf.DF, endereco.getUf());
		assertEquals("71680111", endereco.getCep());
	}

	@Test
	public void testGrandesUsuariosCepMappingQuery() {
		GrandesUsuariosCepMappingQuery mappingQuery = new GrandesUsuariosCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(69900902);
		assertNotNull(endereco);
		assertEquals("AVENIDA BRASIL", endereco.getLogradouro());
		assertEquals("378", endereco.getNumero());
		assertEquals("RIO BRANCO", endereco.getLocalidade());
		assertEquals("CENTRO", endereco.getBairro());
		assertEquals(Uf.AC, endereco.getUf());
		assertEquals("69900902", endereco.getCep());

		endereco = (Endereco) mappingQuery.findObject(60030905);
		assertNotNull(endereco);
		assertEquals("RUA SENADOR ALENCAR", endereco.getLogradouro());
		assertEquals("38", endereco.getNumero());
		assertEquals("1O ANDAR", endereco.getComplemento());
		assertEquals("FORTALEZA", endereco.getLocalidade());
		assertEquals("CENTRO", endereco.getBairro());
		assertEquals(Uf.CE, endereco.getUf());
		assertEquals("60030905", endereco.getCep());
	}

	@Test
	public void testCaixasPostaisComunitariasCepMappingQuery() {
		CaixasPostaisComunitariasCepMappingQuery mappingQuery = new CaixasPostaisComunitariasCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(57100990);
		assertNotNull(endereco);
		assertEquals("QUADRA 1 NO 37 - CONJ.MUTIRAO - RIO LARGO", endereco.getLogradouro());
		assertNull(endereco.getNumero());
		assertEquals("RIO LARGO", endereco.getLocalidade());
		assertNull(endereco.getBairro());
		assertEquals(Uf.AL, endereco.getUf());
		assertEquals("57100990", endereco.getCep());
	}

	@Test
	public void testUnidadesOperacionaisCepMappingQuery() {
		UnidadesOperacionaisCepMappingQuery mappingQuery = new UnidadesOperacionaisCepMappingQuery();
		mappingQuery.setJdbcTemplate((JdbcTemplate) simpleJdbcTemplate.getJdbcOperations());

		Endereco endereco = (Endereco) mappingQuery.findObject(57300971);
		assertNotNull(endereco);
		assertEquals("PRACA LUIZ PEREIRA LIMA", endereco.getLogradouro());
		assertEquals("S/N", endereco.getNumero());
		assertEquals("ARAPIRACA", endereco.getLocalidade());
		assertEquals("CENTRO", endereco.getBairro());
		assertEquals(Uf.AL, endereco.getUf());
		assertEquals("57300971", endereco.getCep());
	}

}

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
package org.openinsula.arena.dne.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EnderecoTestCase {

	@Test
	public void testToString() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("RUA DAS ACACIAS");
		endereco.setNumero(null);
		endereco.setBairro("BAIRRO DAS FLORES");
		endereco.setLocalidade("FLORIANOPOLIS");
		endereco.setUf(Uf.SC);
		endereco.setCep("88000123");

		assertEquals("RUA DAS ACACIAS, S/N - BAIRRO DAS FLORES - FLORIANOPOLIS - SC - CEP 88000123", endereco
				.toString());
	}

	@Test
	public void testToStringArray() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("RUA DAS ACACIAS");
		endereco.setNumero("123");
		endereco.setBairro("BAIRRO DAS FLORES");
		endereco.setCep("88000023");
		endereco.setLocalidade("FLORIANOPOLIS");
		endereco.setUf(Uf.SC);

		assertEquals("RUA DAS ACACIAS, 123 - BAIRRO DAS FLORES", endereco.toStringArray()[0]);
		assertEquals("88000-023 - FLORIANOPOLIS - SC", endereco.toStringArray()[1]);
	}

	@Test
	public void testEqualsObject() {
		Endereco end1 = new Endereco();
		end1.setCep("12345123");

		Endereco end2 = new Endereco();
		end2.setCep("12345123");

		Endereco endNull = new Endereco();

		assertTrue(end1.equals(end2));
		assertTrue(end2.equals(end1));

		assertFalse(end1.equals(endNull));
		assertFalse(end2.equals(endNull));
	}

	@Test
	public void testToShortString() {
		Endereco endereco = new Endereco();
		endereco.setLogradouro("RUA DAS ACACIAS");
		endereco.setNumero("3067");
		endereco.setComplemento("LOJA I-09");
		endereco.setBairro("BAIRRO DAS FLORES");

		assertEquals(53, endereco.toString().length());
		assertEquals("RUA DAS ACACIAS, 3067 - LOJA I-09 - BAIRRO DAS FLORES", endereco.toString());

		assertEquals(40, endereco.toShortString().length());
		assertEquals("RUA DAS ACACIAS, 3067 - LOJA I-09 - BAIR", endereco.toShortString());

		endereco.setNumero(null);
		endereco.setComplemento(null);
		assertEquals(40, endereco.toShortString().length());
		assertEquals("RUA DAS ACACIAS, S/N - BAIRRO DAS FLORES", endereco.toShortString());

		endereco.setBairro(null);
		assertEquals(20, endereco.toShortString().length());
		assertEquals("RUA DAS ACACIAS, S/N", endereco.toShortString());
	}

}

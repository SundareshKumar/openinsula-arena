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

import org.openinsula.arena.dne.dao.DneDao;

/**
 * Entidade não-persistente que é retornadas nas buscas de {@link DneDao} para
 * poder representar um determinado logradouro com uma faixa de números, ao
 * invés de um único número.
 * 
 * @author yanaga
 * @since 1.0
 * @see DneDao
 * @see Endereco
 */
public class BuscaEndereco extends Endereco {
	private static final long serialVersionUID = 1L;

	/**
	 * O número final do intervalo de número do resultado desta busca.
	 */
	private String numeroFinal;

	/**
	 * Método utilizado para converter este {@link BuscaEndereco} retornado numa
	 * busca de {@link DneDao} para uma entidade persistente {@link Endereco}.
	 * 
	 * @return O endereço.
	 */
	public Endereco toEndereco() {
		Endereco endereco = new Endereco();
		endereco.setCep(this.getCep());
		endereco.setUf(this.getUf());
		endereco.setLocalidade(this.getLocalidade());
		endereco.setBairro(this.getBairro());
		endereco.setLogradouro(this.getLogradouro());
		endereco.setNumero(this.getNumero());
		endereco.setComplemento(this.getComplemento());

		return endereco;
	}

	public String getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(String numeroFinal) {
		this.numeroFinal = numeroFinal;
	}
}

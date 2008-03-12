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

import java.util.List;

import org.openinsula.arena.dne.entity.BuscaEndereco;
import org.openinsula.arena.dne.entity.Endereco;
import org.openinsula.arena.dne.entity.Localidade;
import org.openinsula.arena.dne.entity.Uf;

/**
 * Dao utilizado para efetuar consultas na base de dados do DNE dos Correios.
 * @author yanaga
 * @since 1.0
 */
public interface DneDao {

	/**
	 * Método que busca uma {@link Localidade} a partir da chave da
	 * {@link Localidade} no DNE.
	 * 
	 * @param chaveLocalidadeDne A chave da localidade no DNE.
	 * @return A {@link Localidade} encontrada, ou <code>null</code> caso
	 * contrário.
	 */
	public Localidade findLocalidade(Long chaveLocalidadeDne);

	/**
	 * Método que busca uma {@link Localidade} a partir do nome e da UF da
	 * {@link Localidade} no DNE. Buscas parciais também são efetuadas.
	 * 
	 * <p>
	 * Exemplo: A busca pelo nome <code>ARA</code> retorna
	 * <code>ARAGUAIA</code>, <code>ARAGUARI</code> e outros.
	 * 
	 * @param nome O nome da localidade no DNE.
	 * @param uf A {@link Uf} da localidade no DNE.
	 * @return Uma lista de {@link Localidade}s encontradas ou uma lista vazia
	 * caso nenhuma {@link Localidade} tenha sido encontrada.
	 */
	public List<Localidade> findLocalidade(String nome, Uf uf);

	/**
	 * Método que retorna um {@link Endereco} a partir de um CEP do DNE.
	 * 
	 * @param cep A {@link String} representando o CEP. Não deve conter espaços
	 * ou traçoes. Exemplo: <code>87020015</code>
	 * @return O {@link Endereco} encontrado ou <code>null</code> caso nenhum.
	 */
	public Endereco findEnderecoByCep(String cep);

	/**
	 * Método que retorna uma lista de {@link BuscaEndereco} a partir da busca
	 * de endereços com os parâmetros passados. Buscas parciais são efetuadas
	 * nos parâmetros <code>logradouro</code>, <code>numero</code> e
	 * <code>localidade</code>.
	 * 
	 * @param logradouro O nome do logradouro.
	 * @param numero O número do endereço.
	 * @param localidade O nome da localidade do endereço.
	 * @param uf A {@link Uf} do endereço.
	 * @return A lista de {@link BuscaEndereco} contendo o resultado da busca,
	 * ou uma lista vazia caso nenhum {@link BuscaEndereco} tenha sido
	 * encontrado.
	 */
	public List<BuscaEndereco> findEnderecoByParameter(String logradouro, String numero, String localidade, Uf uf);

}

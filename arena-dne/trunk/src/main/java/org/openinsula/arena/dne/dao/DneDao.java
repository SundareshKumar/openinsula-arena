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
	 * M�todo que busca uma {@link Localidade} a partir da chave da
	 * {@link Localidade} no DNE.
	 * 
	 * @param chaveLocalidadeDne A chave da localidade no DNE.
	 * @return A {@link Localidade} encontrada, ou <code>null</code> caso
	 * contr�rio.
	 */
	public Localidade findLocalidade(Long chaveLocalidadeDne);

	/**
	 * M�todo que busca uma {@link Localidade} a partir do nome e da UF da
	 * {@link Localidade} no DNE. Buscas parciais tamb�m s�o efetuadas.
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
	 * M�todo que retorna um {@link Endereco} a partir de um CEP do DNE.
	 * 
	 * @param cep A {@link String} representando o CEP. N�o deve conter espa�os
	 * ou tra�oes. Exemplo: <code>87020015</code>
	 * @return O {@link Endereco} encontrado ou <code>null</code> caso nenhum.
	 */
	public Endereco findEnderecoByCep(String cep);

	/**
	 * M�todo que retorna uma lista de {@link BuscaEndereco} a partir da busca
	 * de endere�os com os par�metros passados. Buscas parciais s�o efetuadas
	 * nos par�metros <code>logradouro</code>, <code>numero</code> e
	 * <code>localidade</code>.
	 * 
	 * @param logradouro O nome do logradouro.
	 * @param numero O n�mero do endere�o.
	 * @param localidade O nome da localidade do endere�o.
	 * @param uf A {@link Uf} do endere�o.
	 * @return A lista de {@link BuscaEndereco} contendo o resultado da busca,
	 * ou uma lista vazia caso nenhum {@link BuscaEndereco} tenha sido
	 * encontrado.
	 */
	public List<BuscaEndereco> findEnderecoByParameter(String logradouro, String numero, String localidade, Uf uf);

}

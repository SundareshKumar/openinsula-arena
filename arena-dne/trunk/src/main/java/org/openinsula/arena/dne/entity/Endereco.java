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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

/**
 * Entidade que representa um endereço segundo a representação dos Correios. É
 * uma entidade que implementa {@link Embeddable}, e pode ser colocada como
 * propriedade de entidades que possuem {@link Entity} do JPA.
 * 
 * @author yanaga
 * @since 1.0
 * @see Localidade
 * @see Uf
 */
@Embeddable
public class Endereco implements Serializable {
	private static final String ESPACO_TRACO_ESPACO = " - ";

	private static final long serialVersionUID = 1L;

	@Column(length = 72, name = "endereco__logradouro")
	private String logradouro;

	@Column(length = 15, name = "endereco__numero")
	private String numero;

	@Column(length = 25, name = "endereco__complemento")
	private String complemento;

	@Column(length = 35, name = "endereco__bairro")
	private String bairro;

	@Column(length = 40, name = "endereco__localidade")
	private String localidade;

	@Enumerated(EnumType.STRING)
	@Column(length = 2, name = "endereco__uf")
	private Uf uf;

	@Column(length = 8, name = "endereco__cep")
	private String cep;

	@Transient
	private Long chaveDne;

	/**
	 * Retorna um array resultante do endereço composto de Logradouro, Número,
	 * Complemento e Bairro na primeira posição e CEP, Localidade e UF na
	 * segunda posição.
	 * 
	 * @return Um array de duas {@link String}s contendo o endereço.
	 */
	public String[] toStringArray() {
		StringBuilder sb = new StringBuilder();
		String[] strings = new String[2];

		if (getLogradouro() != null) {
			sb.append(getLogradouro());
			sb.append(", ");

			if (getNumero() != null) {
				sb.append(getNumero());
			}
			else {
				sb.append("S/N");
			}

			if (getComplemento() != null && !"".equals(getComplemento())) {
				sb.append(ESPACO_TRACO_ESPACO);
				sb.append(getComplemento());
			}
		}
		if (getBairro() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getBairro());
		}
		strings[0] = sb.toString();

		sb = new StringBuilder();

		if (getCep() != null) {
			sb.append(getCep().substring(0, 5));
			sb.append("-");
			sb.append(getCep().substring(5));
		}
		if (getLocalidade() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getLocalidade());
		}
		if (getUf() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getUf());
		}
		strings[1] = sb.toString();

		return strings;
	}

	/**
	 * Retorna uma {@link String} resultante do endereço composto de Logradouro,
	 * Número, Complemento, Bairro, Localidade, UF e CEP.
	 * 
	 * @return A {@link String} do endereço.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (getLogradouro() != null) {
			sb.append(getLogradouro());
			sb.append(", ");

			if (getNumero() != null) {
				sb.append(getNumero());
			}
			else {
				sb.append("S/N");
			}

			if (getComplemento() != null && !"".equals(getComplemento())) {
				sb.append(ESPACO_TRACO_ESPACO);
				sb.append(getComplemento());
			}
		}

		if (getBairro() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getBairro());
		}

		if (getLocalidade() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getLocalidade());
		}

		if (getUf() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append(getUf());
		}

		if (getCep() != null) {
			sb.append(ESPACO_TRACO_ESPACO);
			sb.append("CEP ");
			sb.append(getCep());
		}

		return sb.toString();
	}

	/**
	 * /** Retorna uma {@link String} resultante do endereço composto de
	 * Logradouro, Número, Complemento e Bairro.
	 * 
	 * @return A {@link String} do endereço.
	 */
	public String toShortString() {
		StringBuilder sb = new StringBuilder();
		if (getLogradouro() != null) {
			sb.append(getLogradouro());
			sb.append(", ");

			if (getNumero() != null) {
				sb.append(getNumero());
			}
			else {
				sb.append("S/N");
			}

			if (getComplemento() != null) {
				sb.append(ESPACO_TRACO_ESPACO);
				sb.append(getComplemento());
			}

			if (getBairro() != null) {
				sb.append(ESPACO_TRACO_ESPACO);
				sb.append(getBairro());
			}
		}

		if (sb.toString().length() > 40) {
			return sb.toString().substring(0, 40);
		}
		return sb.toString();
	}

	/**
	 * Verifica se dois Endereços são iguais, basicamente comparando o tipo e o
	 * CEP.
	 * @return <strong>true</strong> se os CEPs forem iguais, <strong>false</strong>
	 * caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Endereco) {
			Endereco other = (Endereco) obj;
			if (this.getCep() != null) {
				return this.getCep().equals(other.getCep());
			}
			else {
				return other.getCep() == null;
			}
		}
		return false;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Long getChaveDne() {
		return chaveDne;
	}

	public void setChaveDne(Long chaveDne) {
		this.chaveDne = chaveDne;
	}

}

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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Entidade que representa uma Localidade segundo a representação dos Correios.
 * É {@link Embeddable}.
 * 
 * @author yanaga
 * @since 1.0
 */
@Embeddable
public class Localidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "localidade__chaveDne")
	private Long chaveDne;

	@Column(name = "localidade__uf", length = 2)
	@Enumerated(EnumType.STRING)
	private Uf uf;

	@Column(name = "localidade__nomeOficial", length = 72)
	private String nomeOficial;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Localidade) {
			Localidade other = (Localidade) obj;

			if (other.chaveDne == this.chaveDne) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (chaveDne != null) {
			return chaveDne.hashCode();
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(chaveDne);
		sb.append("] ");
		sb.append(nomeOficial);
		sb.append(", ");
		sb.append(uf);

		return sb.toString();
	}

	public Long getChaveDne() {
		return chaveDne;
	}

	public void setChaveDne(Long chaveDne) {
		this.chaveDne = chaveDne;
	}

	public String getNomeOficial() {
		return nomeOficial;
	}

	public void setNomeOficial(String nomeOficial) {
		this.nomeOficial = nomeOficial;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

}

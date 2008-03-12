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

/**
 * Enum representados todas as unidades da federação brasileira.
 * @author yanaga
 * @since 1.0
 */
public enum Uf {
	AC("Acre"),

	AL("Alagoas"),

	AM("Amazonas"),

	AP("Amapá"),

	BA("Bahia"),

	CE("Ceará"),

	DF("Distrito Federal"),

	ES("Espírito Santo"),

	GO("Goiás"),

	MA("Maranhão"),

	MG("Minas Gerais"),

	MS("Mato Grosso do Sul"),

	MT("Mato Grosso"),

	PA("Pará"),

	PB("Paraíba"),

	PE("Pernambuco"),

	PI("Piauí"),

	PR("Paraná"),

	RJ("Rio de Janeiro"),

	RN("Rio Grande do Norte"),

	RO("Rondônia"),

	RR("Roraima"),

	RS("Rio Grande do Sul"),

	SC("Santa Catarina"),

	SE("Sergipe"),

	SP("São Paulo"),

	TO("Tocantins");

	private String nome;

	private Uf(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}

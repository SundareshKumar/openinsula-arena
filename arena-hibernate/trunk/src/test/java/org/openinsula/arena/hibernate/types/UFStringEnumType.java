package org.openinsula.arena.hibernate.types;

import org.openinsula.arena.hibernate.types.annotations.StringEnumValue;

public enum UFStringEnumType {

	@StringEnumValue("Parana")
	PR("Parana"),

	@StringEnumValue("SP")
	SP("Sao Paulo"),

	@StringEnumValue("SC")
	SC("Santa Catarina");

	UFStringEnumType(final String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public static final String TYPE = "UFStringEnumType";

	public final String nomeCompleto;

}

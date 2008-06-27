package org.openinsula.arena.hibernate.types;

import org.openinsula.arena.hibernate.types.annotations.IntEnumValue;

public enum UFIntEnumType {

	@IntEnumValue(1)
	PR("Parana"),

	@IntEnumValue(2)
	SP("Sao Paulo"),

	@IntEnumValue(3)
	SC("Santa Catarina");

	UFIntEnumType(final String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public static final String TYPE = "UFIntEnumType";

	public final String nomeCompleto;

}

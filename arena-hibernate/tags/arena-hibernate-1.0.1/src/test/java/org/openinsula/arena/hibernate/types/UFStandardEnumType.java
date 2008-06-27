package org.openinsula.arena.hibernate.types;

public enum UFStandardEnumType {

	PR("Parana"),
	SP("Sao Paulo"),
	SC("Santa Catarina");

	UFStandardEnumType(final String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public static final String TYPE = "UFStandardEnumType";

	public final String nomeCompleto;

}

package org.openinsula.arena.echo2.component.provider.type;

public enum FieldDelimiter {
	VIRGULA(","),
	
	PONTO_VIRGULA(";");
	
	private String delimiter;
	
	FieldDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDelimiter() {
		return delimiter;
	}
}

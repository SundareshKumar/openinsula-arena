package org.openinsula.arena.echo2.component.provider.type;

public enum RecordDelimiter {
	BARRA_N("\n"),
	
	BARRA_R("\r");
	
	private String delimiter;
	
	RecordDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDelimiter() {
		return delimiter;
	}
}

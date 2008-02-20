package br.com.insula.arena.echo2.component.util;

public enum TextAlignment {

	RIGHT("right"),
	LEFT("left"),
	CENTER("center"),
	JUSTIFY("justify");
	
	private String name;

	private TextAlignment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}

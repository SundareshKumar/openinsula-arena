package org.openinsula.arena.gwt.client.beans.ui;

public class OracleSuggestEvent {
	
	private int suggestCount;
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public OracleSuggestEvent(int suggestCount, String value) {
		this.suggestCount = suggestCount;
		this.value = value;
	}

	public int getSuggestCount() {
		return suggestCount;
	}
}

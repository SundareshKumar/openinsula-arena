package org.openinsula.arena.gwt.client.components.cardpanel;

public class CardChangeEvent {

	private int newIndex;
	
	private int oldIndex;

	public int getNewIndex() {
		return newIndex;
	}

	public int getOldIndex() {
		return oldIndex;
	}

	public CardChangeEvent(int newIndex, int oldIndex) {
		super();
		this.newIndex = newIndex;
		this.oldIndex = oldIndex;
	}
	
}

package org.openinsula.arena.gwt.client.components.cardpanel;

public class CardChangeEvent {

	private Card newCard;
	
	private Card oldCard;

	public CardChangeEvent(Card newCard, Card oldCard) {
		super();
		this.newCard = newCard;
		this.oldCard = oldCard;
	}
	
	public Card getNewCard() {
		return newCard;
	}

	public Card getOldCard() {
		return oldCard;
	}

}

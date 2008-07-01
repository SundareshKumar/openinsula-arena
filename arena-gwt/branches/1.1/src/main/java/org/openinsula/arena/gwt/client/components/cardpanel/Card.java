package org.openinsula.arena.gwt.client.components.cardpanel;

public interface Card extends CardChangeListener {

	void setIndex(int index);
	
	int getIndex();
	
	void addCardChangeListener(CardChangeListener listener);
	
	void removeCardChangeListener(CardChangeListener listener);
	
	boolean fireCardShowed(CardChangeEvent event);
	
	boolean fireCardHided(CardChangeEvent event);
	
	void setNextCard(Card card);
	
	void setPreviousCard(Card card);
	
	Card getNextCard();
	
	Card getPreviousCard();
	
	String getNextLabel();
	
	String getPreviousLabel();
	
}

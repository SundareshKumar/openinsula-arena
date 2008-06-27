package org.openinsula.arena.gwt.client.components.cardpanel;

public interface Card extends CardChangeListener {

	void setIndex(int index);
	
	int getIndex();
	
	void addCardChangeListener(CardChangeListener listener);
	
	void removeCardChangeListener(CardChangeListener listener);
	
	boolean fireCardChanged(CardChangeEvent event);
	
}

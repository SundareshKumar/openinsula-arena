package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.DeckPanel;

public class CardPanel extends DeckPanel {
	
	private List<CardChangeListener> listeners = new ArrayList<CardChangeListener>();
	
	public void addCardChangeListener(CardChangeListener listener) {
		listeners.add(listener);
	}
	
	public void removeCardChangeListener(CardChangeListener listener) {
		listeners.remove(listener);
	}
	
	protected boolean fireCardShowed(CardChangeEvent event) {
		boolean returnValue = true;
		for (CardChangeListener listener : listeners) {
			returnValue = returnValue && listener.onShow(event);
		}
		
		return returnValue;
	}
	
	protected boolean fireCardHided(CardChangeEvent event) {
		boolean returnValue = true;
		for (CardChangeListener listener : listeners) {
			returnValue = returnValue && listener.onHide(event);
		}
		
		return returnValue;
	}
	
	public void showCard(Card card) {
		int indiceAtual = getVisibleWidget();
		Card oldCard = getCard(indiceAtual);
		CardChangeEvent event = new CardChangeEvent(card, oldCard); 
		
		if (oldCard == null || oldCard.fireCardHided(event)) {
			if (fireCardHided(event)) {
				if (card.fireCardShowed(event)) {
					if (fireCardShowed(event)) {
						showWidget(card.getIndex());
					}
				}
			}
		}
		
	}
	
	public Card getCard(int index) {
		try {
			return (Card) getWidget(index);
		} catch (ClassCastException ex) {
			ex.printStackTrace();
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void addCard(AbstractCard card) {
		add(card);
		card.setIndex(getWidgetCount() -1);
	}
	
	public void next() {
		int indiceAtual = getVisibleWidget();
		Card cardAtual = getCard(indiceAtual);
		if (cardAtual != null && cardAtual.getNextCard() != null) {
			showCard(cardAtual.getNextCard());
		} else if (++indiceAtual < getWidgetCount()) {
			Card card = getCard(indiceAtual);
			showCard(card);
		}
	}
	
	public void previous() {
		int indiceAtual = getVisibleWidget();
		Card cardAtual = getCard(indiceAtual);
		if (cardAtual != null && cardAtual.getPreviousCard() != null) {
			showCard(cardAtual.getPreviousCard());
		} else if (--indiceAtual >= 0) {
			Card card = getCard(indiceAtual);
			showCard(card);
		}
	}
	
	public boolean hasNext() {
		return getVisibleWidget() < (getWidgetCount() -1);
	}
	
	public boolean hasPrevious() {
		return getVisibleWidget() > 0;
	}
	
	public void pack() {
		showCard(getCard(0));
	}
	
}

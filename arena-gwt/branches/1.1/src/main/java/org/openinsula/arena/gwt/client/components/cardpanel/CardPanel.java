package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardPanel extends DeckPanel {
	
	private List<CardChangeListener> listeners = new ArrayList<CardChangeListener>();

	private Sequence<Card> cardSequence = new CardSequence();
	
	public Sequence<Card> getCardSequence() {
		return cardSequence;
	}
	
	@Override
	public void add(Widget w) {
		throw new IllegalArgumentException("Utilize o metodo addCard() para adicionar novos Cards.");
	}

	public void setCardSequence(Sequence<Card> cardSequence) {
		this.cardSequence = cardSequence;
	}

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
		card.setIndex(getWidgetCount());
		cardSequence.add(card);
		super.add(card);
	}
	
	public void next() {
		int indiceAtual = getVisibleWidget();
		Card cardAtual = getCard(indiceAtual);
		Card newCard = cardSequence.next(cardAtual);
		showCard(newCard);
	}
	
	public void previous() {
		int indiceAtual = getVisibleWidget();
		Card cardAtual = getCard(indiceAtual);
		Card newCard = cardSequence.previous(cardAtual);
		showCard(newCard);
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

package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractCard extends Composite implements Card {

	private int index;
	
	private Card previousCard = null;
	
	private Card nextCard = null;
	
	private List<CardChangeListener> cardChangeListeners = new ArrayList<CardChangeListener>();
	
	{
		addCardChangeListener(this);
		initComponents();
		customizeComponents();
		initActions();
		initWidget(createMainWidget());
	}
	
	public Card getPreviousCard() {
		return previousCard;
	}

	public void setPreviousCard(Card previousCard) {
		this.previousCard = previousCard;
	}

	public Card getNextCard() {
		return nextCard;
	}

	public void setNextCard(Card nextCard) {
		this.nextCard = nextCard;
	}

	protected abstract Widget createMainWidget();
	
	protected abstract void initActions();

	protected abstract void customizeComponents();

	protected abstract void initComponents();
	
	public String getNextLabel() {
		return "Próximo";
	}

	public String getPreviousLabel() {
		return "Anterior";
	}
	
	public boolean fireCardHided(CardChangeEvent event) {
		boolean returnValue = true;
		for (CardChangeListener listener : cardChangeListeners) {
			returnValue = returnValue && listener.onHide(event);
		}
		
		return returnValue;
	}

	public boolean fireCardShowed(CardChangeEvent event) {
		boolean retorno = true;
		for (CardChangeListener listener : cardChangeListeners) {
			retorno = retorno && listener.onShow(event);
		}
		
		return retorno;
	}

	public void addCardChangeListener(CardChangeListener listener) {
		cardChangeListeners.add(listener);
	}
	
	public void removeCardChangeListener(CardChangeListener listener) {
		cardChangeListeners.remove(listener);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean onHide(CardChangeEvent event) {
		return true;
	}

	public boolean onShow(CardChangeEvent event) {
		return true;
	}
	
}

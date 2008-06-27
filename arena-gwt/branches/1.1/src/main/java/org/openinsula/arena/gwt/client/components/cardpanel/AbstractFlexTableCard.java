package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;

public abstract class AbstractFlexTableCard extends FlexTable implements Card {

	private int index;
	
	private List<CardChangeListener> cardChangeListeners = new ArrayList<CardChangeListener>();
	
	{
		addCardChangeListener(this);
	}
	
	public boolean fireCardChanged(CardChangeEvent event) {
		boolean retorno = true;
		for (CardChangeListener listener : cardChangeListeners) {
			retorno = retorno && listener.onCardChange(event);
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

	public boolean onCardChange(CardChangeEvent event) {
		return true;
	}
	
}

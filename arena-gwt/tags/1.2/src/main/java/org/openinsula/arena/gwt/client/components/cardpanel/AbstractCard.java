package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractCard extends Composite implements Card {

	private int index;

	private List<CardChangeListener> cardChangeListeners = new ArrayList<CardChangeListener>();

	{
		addCardChangeListener(this);
		initComponents();
		customizeComponents();
		initActions();
		initWidget(createMainWidget());
	}

	@Override
	public int hashCode() {
		return index;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractCard) {
			return ((AbstractCard) obj).getIndex() == this.getIndex();
		}
		return false;
	}

	protected abstract Widget createMainWidget();

	protected abstract void initActions();

	protected abstract void customizeComponents();

	protected abstract void initComponents();

	public String getNextLabel() {
		return "Pr\u00f3ximo";
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

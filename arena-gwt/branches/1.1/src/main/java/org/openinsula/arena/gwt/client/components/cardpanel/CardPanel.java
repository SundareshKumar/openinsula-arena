package org.openinsula.arena.gwt.client.components.cardpanel;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

public class CardPanel extends DeckPanel {
	
	public void showCard(Card card) {
		int indiceAtual = getVisibleWidget();
		if (card.fireCardChanged(new CardChangeEvent(card.getIndex(), indiceAtual))) {
			showWidget(card.getIndex());
		}
	}
	
	public void addCard(Widget widget) {
		if (!(widget instanceof Card)) {
			throw new IllegalArgumentException("Widgets adicionados a este container devem implementar a interface " + Card.class.getName());
		}
		add(widget);
		Card card = (Card) widget;
		card.setIndex(getWidgetCount() -1);
	}
	
	public void next() {
		int indiceAtual = getVisibleWidget();
		Card card = (Card) getWidget(indiceAtual);
		if (++indiceAtual < getWidgetCount()) {
			if (card.fireCardChanged(new CardChangeEvent(indiceAtual, indiceAtual -1))) {
				showWidget(indiceAtual);
			}
		}
	}
	
	public void previous() {
		int indiceAtual = getVisibleWidget();
		Card card = (Card) getWidget(indiceAtual);
		if (--indiceAtual >= 0) {
			if (card.fireCardChanged(new CardChangeEvent(indiceAtual, indiceAtual + 1))) {
				showWidget(indiceAtual);
			}
		}
	}
	
	public void pack() {
		showWidget(0);
	}
	
}

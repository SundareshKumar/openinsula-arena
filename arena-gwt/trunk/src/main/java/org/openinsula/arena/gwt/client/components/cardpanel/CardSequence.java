package org.openinsula.arena.gwt.client.components.cardpanel;

import java.util.ArrayList;
import java.util.List;

public class CardSequence implements Sequence<Card> {

	private List<Card> cards = new ArrayList<Card>();
	
	public Card next(Card baseCard) {
		int baseIndex = cards.indexOf(baseCard);
		if (++baseIndex < cards.size()) {
			return cards.get(baseIndex);
		}
		return null; 
	}

	public Card previous(Card baseCard) {
		int baseIndex = cards.indexOf(baseCard);
		if (--baseIndex >= 0) {
			return cards.get(baseIndex);
		}
		return null;
	}

	public void add(Card card) {
		if (cards.contains(card)) {
			throw new IllegalArgumentException("Card já inserido na sequencia.");
		}
		cards.add(card);
	}
	
}

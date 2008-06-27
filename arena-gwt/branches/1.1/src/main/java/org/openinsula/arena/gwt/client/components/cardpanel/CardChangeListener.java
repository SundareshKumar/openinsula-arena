package org.openinsula.arena.gwt.client.components.cardpanel;

/**
 * @author fabiano
 */
public interface CardChangeListener {

	/**
	 * @param event
	 * @return booleano indicando se a troca de paineis deve proceder.
	 */
	boolean onCardChange(CardChangeEvent event);
	
}

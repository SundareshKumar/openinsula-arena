package org.openinsula.arena.gwt.client.components.cardpanel.wizard;

import org.openinsula.arena.gwt.client.components.cardpanel.AbstractCard;

public class WizardNavigatorEvent {

	private AbstractCard sender;

	public AbstractCard getSender() {
		return sender;
	}

	public WizardNavigatorEvent(AbstractCard sender) {
		super();
		this.sender = sender;
	}
	
}

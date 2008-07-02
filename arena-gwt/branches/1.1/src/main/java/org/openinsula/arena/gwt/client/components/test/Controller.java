package org.openinsula.arena.gwt.client.components.test;

public interface Controller<L extends PanelListener> {

	AbstractPanel<L> getPanel(L listener);
	
}

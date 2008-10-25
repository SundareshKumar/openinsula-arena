package org.openinsula.arena.gwt.client.application.display;

import org.openinsula.arena.gwt.client.application.history.HistoryController;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DeckPanelDisplayer extends Composite implements WidgetDisplayer {

	private DeckPanel deckPanel;

	private HistoryController historyController;

	public DeckPanelDisplayer() {
		initPanel();
	}

	private void initPanel() {
		deckPanel = new DeckPanel();

		initWidget(deckPanel);
	}

	public void show(Widget widget) {
	}

	public HistoryController getHistoryController() {
		return historyController;
	}

	public void setHistoryController(HistoryController historyController) {
		this.historyController = historyController;
	}

}

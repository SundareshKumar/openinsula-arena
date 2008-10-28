package org.openinsula.arena.gwt.client.application.display;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DeckPanelDisplayer extends AbstractWidgetDisplayer {

	private final DeckPanel deckPanel = new DeckPanel();

	public DeckPanelDisplayer() {
		initWidget(deckPanel);
	}

	@Override
	protected boolean showWidget(Widget widget) {
		final int index = deckPanel.getWidgetIndex(widget);

		if (index > -1) {
			deckPanel.showWidget(index);
			return true;
		}
		return false;
	}

	public void addWidget(Widget widget) {
		deckPanel.add(widget);
	}

	public void removeWidget(Widget widget) {
		deckPanel.remove(widget);
	}

}

package org.openinsula.arena.gwt.application.client.display;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DeckPanelDisplayer extends WidgetDisplayer {

	private final DeckPanel deckPanel = new DeckPanel();

	public DeckPanelDisplayer() {
		initWidget(deckPanel);
	}

	@Override
	protected boolean doShow(Widget widget) {
		final int index = deckPanel.getWidgetIndex(widget);

		if (index > -1) {
			deckPanel.showWidget(index);
			return true;
		}
		return false;
	}

	public void clear() {
		deckPanel.clear();
	}

	@Override
	public boolean constains(Widget widget) {
		return deckPanel.getWidgetIndex(widget) > -1;
	}

	@Override
	public void add(Widget widget) {
		deckPanel.add(widget);
	}

	@Override
	public void remove(Widget widget) {
		deckPanel.remove(widget);
	}

}

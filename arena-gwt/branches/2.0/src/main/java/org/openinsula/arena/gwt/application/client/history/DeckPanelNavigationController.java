package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DeckPanelNavigationController implements NavigationController {

	private final DeckPanel panel;

	public DeckPanelNavigationController() {
		this(new DeckPanel());
	}

	public DeckPanelNavigationController(DeckPanel panel) {
		this.panel = panel;
	}

	public void show(Widget widget) {
		if (widget == null) {
			return;
		}

		final int index = panel.getWidgetIndex(widget);

		if (index > -1) {
			panel.showWidget(index);
		}
		else {
			panel.add(widget);

			show(widget);
		}
	}

	public void showPageNotFound(String historyToken) {
	}

	public DeckPanel getPanel() {
		return panel;
	}

}

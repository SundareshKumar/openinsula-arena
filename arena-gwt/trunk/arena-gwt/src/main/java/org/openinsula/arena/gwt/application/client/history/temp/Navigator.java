package org.openinsula.arena.gwt.application.client.history.temp;

import java.util.HashMap;
import java.util.Map;

import org.openinsula.arena.gwt.components.client.util.Assert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Navigator {

	private static Navigator instance;

	private final Map<String, HistoryItem> historyMap;
	
	private final Map<Class<? extends HistoryItem>, HistoryItem> historyTypeMap;

	public static Navigator get() {
		return get(RootPanel.get());
	}

	public static Navigator get(final HasWidgets rootPanel) {
		if (instance == null) {
			instance = new Navigator(rootPanel);
		}
		return instance;
	}

	public void open(final Class<? extends Widget> pageType) {
		HistoryItem historyItem = historyTypeMap.get(pageType);
		
		if (historyItem == null) {
			historyItem = GWT.create(pageType);
		}
		
		Assert.notNull(historyItem);
		History.newItem(historyItem.getHistoryToken());
	}
	
	public void addHistoryItem(final HistoryItem historyItem) {
		Assert.notNull(historyItem, "HistoryItem must not be null");

		String historyToken = historyItem.getHistoryToken();
		Assert.hasText(historyToken, "Invalid history token");

		historyMap.put(historyToken, historyItem);
		historyTypeMap.put(historyItem.getClass(), historyItem);
	}

	private Navigator(final HasWidgets rootPanel) {
		historyMap = new HashMap<String, HistoryItem>();
		historyTypeMap = new HashMap<Class<? extends HistoryItem>, HistoryItem>();
		History.addHistoryListener(new NavigatorHistoryListener(rootPanel));
	}

	private final class NavigatorHistoryListener implements HistoryListener {
		private HistoryItem currentItem;

		private Widget currentWidget;

		private final HasWidgets rootPanel;

		public NavigatorHistoryListener(final HasWidgets rootPanel) {
			this.rootPanel = rootPanel;
		}

		public void onHistoryChanged(final String historyToken) {
			HistoryItem next = historyMap.get(historyToken);

			if (currentItem != null) {
				hideCurrentItem(next, historyToken);
			}
			else {
				showNewItem(next, historyToken);
			}
		}

		private void hideCurrentItem(final HistoryItem next, final String historyToken) {
			NavigatorFlow historyItemChain = new NavigatorFlow() {
				public void continueFlow() {
					showNewItem(next, historyToken);
				}
			};
			currentItem.onHide(historyItemChain);
		}

		private void showNewItem(final HistoryItem next, final String historyToken) {
			currentItem = next;

			if (currentWidget != null) {
				rootPanel.remove(currentWidget);
			}

			currentWidget = next.show(historyToken);
			rootPanel.add(currentWidget);
		}
	}

}

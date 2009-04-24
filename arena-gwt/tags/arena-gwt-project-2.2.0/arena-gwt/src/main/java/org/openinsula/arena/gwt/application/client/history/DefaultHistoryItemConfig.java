package org.openinsula.arena.gwt.application.client.history;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucas K Mogari
 */
public class DefaultHistoryItemConfig extends HistoryItemConfig {

	private final Map<String, HistoryItem> historyItems = new HashMap<String, HistoryItem>();

	@Override
	public boolean containsHistoryToken(String historyToken) {
		return getHistoryItem(historyToken) != null;
	}

	@Override
	public HistoryItem getHistoryItem(String historyToken) {
		final String[] args = separeHistoryToken(historyToken);

		return historyItems.get(args[0]);
	}

	@Override
	public void addHistoryItem(HistoryItem historyItem) {
		historyItems.put(historyItem.getToken(), historyItem);
	}

	@Override
	public void addHistoryItems(HistoryItemsProvider historyItemsProvider) {
		for (final HistoryItem historyItem : historyItemsProvider.getHistoryItems()) {
			addHistoryItem(historyItem);
		}
	}

	@Override
	public void addHistoryItem(final String token, final String name, final Object target) {
		addHistoryItem(new HistoryItem() {
			public String getToken() {
				return token;
			}

			public Object getTarget() {
				return target;
			}
		});
	}

	@Override
	public HistoryItem removeHistoryItem(String historyToken) {
		return historyItems.remove(historyToken);
	}

}

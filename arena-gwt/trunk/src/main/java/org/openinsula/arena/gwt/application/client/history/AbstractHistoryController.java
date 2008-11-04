package org.openinsula.arena.gwt.application.client.history;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractHistoryController implements HistoryController {

	private final List<HistoryChangeInterceptor> historyChangeInterceptors = new LinkedList<HistoryChangeInterceptor>();

	private final Set<HistoryItem> historyItems = new HashSet<HistoryItem>();

	public final void addHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.add(interceptor);
	}

	public final void removeHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.remove(interceptor);
	}

	protected boolean doPreHistoryChange(String historyToken) {
		boolean sucess = true;

		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			if (!interceptor.preHistoryChange(historyToken)) {
				sucess = false;
				break;
			}
		}
		return sucess;
	}

	protected void doPostHistoryChange(String historyToken, boolean success) {
		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			interceptor.postHistoryChange(historyToken, success);
		}
	}

	public void addTarget(String historyToken, LazyHistoryTargetProvider targetProvider, int scope) {
		historyItems.add(new HistoryItem(historyToken, targetProvider, scope));
	}

	public void addTarget(String historyToken, LazyHistoryTargetProvider targetProvider) {
		addTarget(historyToken, targetProvider, NO_SCOPE);
	}

	public void addTarget(String historyToken, Object target) {
		historyItems.add(new HistoryItem(historyToken, target, APPLICATION_SCOPE));
	}

	public boolean containsTarget(String historyToken) {
		return findHistoryItem(historyToken) != null;
	}

	public Object getTarget(String historyToken) {
		final HistoryItem historyItem = findHistoryItem(historyToken);
		return historyItem != null ? historyItem.target : null;
	}

	public Object removeTarget(String historyToken) {
		final HistoryItem historyItem = findHistoryItem(historyToken);
		return historyItem != null && historyItems.remove(historyToken) ? historyItem.target : null;
	}

	private HistoryItem findHistoryItem(String historyToken) {
		final List<HistoryItem> possibleHistoryItems = new ArrayList<HistoryItem>();

		for (final HistoryItem historyItem : historyItems) {
			if (historyItem.matchesHistoryToken(historyToken)) {
				possibleHistoryItems.add(historyItem);
			}
		}

		HistoryItem historyItem = null;
		final int possibleHistoryItemsSize = possibleHistoryItems.size();

		if (possibleHistoryItemsSize == 1) {
			historyItem = possibleHistoryItems.get(0);
		}
		else if (possibleHistoryItemsSize > 1) {
			for (final HistoryItem historyItem2 : possibleHistoryItems) {
				if (historyItem != null && historyItem.historyToken.length() > historyItem2.historyToken.length()) {
					continue;
				}

				historyItem = historyItem2;
				break;
			}
		}

		return historyItem;
	}

	protected static final class HistoryItem {

		private final String historyToken;

		private final Object target;

		private final int scope;

		private boolean dynamic;

		private HistoryItem(String historyToken, Object target, int scope) {
			this.target = target;
			this.scope = scope;

			if (historyToken.endsWith("*")) {
				dynamic = true;
				this.historyToken = historyToken.replaceAll("*", "");
			}
			else {
				this.historyToken = historyToken;
			}
		}

		private boolean matchesHistoryToken(String string) {
			return dynamic ? string.startsWith(historyToken) : historyToken.equals(string);
		}

		protected String getHistoryToken() {
			return historyToken;
		}

		protected Object getTarget() {
			return target;
		}

		protected int getScope() {
			return scope;
		}

		protected boolean isDynamic() {
			return dynamic;
		}

	}

}

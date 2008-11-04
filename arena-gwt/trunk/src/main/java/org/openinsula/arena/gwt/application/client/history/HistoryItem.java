/**
 * 
 */
package org.openinsula.arena.gwt.application.client.history;

final class HistoryItem {

	private final String historyToken;

	private final Object target;

	private final int scope;

	private boolean dynamic;

	private HistoryItem(String historyToken, Object target, int scope) {
		this.historyToken = historyToken;
		this.target = target;
		this.scope = scope;
	}

	private boolean matchesHistoryToken(String string) {
		if (dynamic) {

		}
		return false;
	}

	private String getHistoryToken() {
		return historyToken;
	}

	private Object getTarget() {
		return target;
	}

	private int getScope() {
		return scope;
	}

	private boolean isDynamic() {
		return dynamic;
	}

}
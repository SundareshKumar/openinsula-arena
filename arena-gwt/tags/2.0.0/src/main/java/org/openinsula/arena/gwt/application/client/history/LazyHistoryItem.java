package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public abstract class LazyHistoryItem implements HistoryItem {

	private final String token;

	public LazyHistoryItem(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}

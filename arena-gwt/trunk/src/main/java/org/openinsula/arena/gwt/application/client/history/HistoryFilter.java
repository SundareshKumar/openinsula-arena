package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public interface HistoryFilter {

	public void doFilter(String historyToken, HistoryFilterChain historyFilterChain);

}

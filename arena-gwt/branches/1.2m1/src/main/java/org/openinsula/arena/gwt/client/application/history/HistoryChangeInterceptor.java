package org.openinsula.arena.gwt.client.application.history;

/**
 * @author Lucas K Mogari
 */
public interface HistoryChangeInterceptor {

	public boolean preHistoryChange(String historyToken);

	public void postHistoryChange(String historyToken, boolean success);

}

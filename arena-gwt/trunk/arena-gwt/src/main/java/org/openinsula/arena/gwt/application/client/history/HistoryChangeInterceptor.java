package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public interface HistoryChangeInterceptor {

	public boolean preHistoryChange(String historyToken);

	public void postHistoryChange(String historyToken, boolean success);

}

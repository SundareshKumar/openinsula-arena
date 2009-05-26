package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public interface HistoryChangeHandler {

	public void handleHistoryChange(String oldHistoryToken, String newHistoryToken);

}

package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.user.client.HistoryListener;

/**
 * Controls the history flow.
 * 
 * @author Lucas K Mogari
 */
public interface HistoryController extends HistoryListener {

	public static final String START_PAGE = "startPageAttribute";

	public void addHistoryChangeInterceptor(HistoryChangeInterceptor interceptor);

	public void removeHistoryChangeInterceptor(HistoryChangeInterceptor interceptor);

	public HistoryItemConfig getHistoryItemConfig();

}

package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.user.client.HistoryListener;

/**
 * Controls a predefined history flow.
 * 
 * @author Lucas K Mogari
 */
public interface HistoryController extends HistoryListener {

	public static final int NO_SCOPE = 0;

	public static final int SESSION_SCOPE = 1;

	public static final int APPLICATION_SCOPE = 2;

	public void addHistoryChangeInterceptor(HistoryChangeInterceptor interceptor);

	public void removeHistoryChangeInterceptor(HistoryChangeInterceptor interceptor);

	public boolean containsTarget(String historyToken);

	public void addTarget(String historyToken, Object target);

	public void addTarget(String historyToken, LazyHistoryTargetProvider provider);

	public void addTarget(String historyToken, LazyHistoryTargetProvider provider, int scope);

	public Object removeTarget(String historyToken);

	public Object getTarget(String historyToken);

}

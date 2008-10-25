package org.openinsula.arena.gwt.client.application.history;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
public class HistoryChangeInterceptorCollection {

	private final List<HistoryChangeInterceptor> historyChangeInterceptors = new LinkedList<HistoryChangeInterceptor>();

	public final void addHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.add(interceptor);
	}

	public final void removeHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.remove(interceptor);
	}

	public boolean doPreHistoryChange(String historyToken) {
		boolean sucess = true;

		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			if (!interceptor.preHistoryChange(historyToken)) {
				sucess = false;
				break;
			}
		}
		return sucess;
	}

	public void doPostHistoryChange(String historyToken, boolean success) {
		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			interceptor.postHistoryChange(historyToken, success);
		}
	}

}

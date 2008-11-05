package org.openinsula.arena.gwt.application.client.history;


import com.google.gwt.user.client.HistoryListener;

/**
 * Controls a predefined history flow.
 * 
 * @author Lucas K Mogari
 */
public interface HistoryController extends HistoryListener {

	public void addHistoryFilter(HistoryFilter filter);

	public void removeHistoryFilter(HistoryFilter filter);

	public void setHistoryTargetResolver(HistoryTargetResolver historyTargetResolver);

}

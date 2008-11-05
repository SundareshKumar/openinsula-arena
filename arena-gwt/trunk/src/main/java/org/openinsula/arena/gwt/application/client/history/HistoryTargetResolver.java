package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface HistoryTargetResolver {

	public Widget resolve(String historyToken);

}

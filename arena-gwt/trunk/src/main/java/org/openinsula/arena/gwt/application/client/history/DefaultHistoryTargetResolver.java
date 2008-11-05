package org.openinsula.arena.gwt.application.client.history;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultHistoryTargetResolver implements HistoryTargetResolver {

	private final Map<String, Widget> historyTargets = new HashMap<String, Widget>();

	public void add(String historyToken, Widget target) {
		historyTargets.put(historyToken, target);
	}

	public void remove(String historyToken) {
		historyTargets.remove(historyToken);
	}

	public final Widget resolve(String historyToken) {
		return historyTargets.get(historyToken);
	}

}

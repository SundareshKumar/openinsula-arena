package org.openinsula.arena.gwt.application.client.history;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class MappedHistoryTargetResolver implements HistoryTargetResolver {

	private final Map<String, Object> historyTargets = new HashMap<String, Object>();

	public void add(String historyToken, Object target) {
		historyTargets.put(historyToken, target);
	}

	public Object remove(String historyToken) {
		return historyTargets.remove(historyToken);
	}

	public void clear() {
		historyTargets.clear();
	}

	public final Widget resolve(String historyToken) {
		Widget widget = null;
		final Object target = historyTargets.get(historyToken);

		if (target instanceof Widget) {
			widget = (Widget) target;
		}
		else if (target instanceof HistoryTargetResolver) {
			widget = ((HistoryTargetResolver) target).resolve(historyToken);
		}
		return widget;
	}

}

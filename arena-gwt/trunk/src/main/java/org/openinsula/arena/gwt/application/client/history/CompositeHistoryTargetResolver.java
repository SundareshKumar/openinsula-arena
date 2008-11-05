package org.openinsula.arena.gwt.application.client.history;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class CompositeHistoryTargetResolver implements HistoryTargetResolver {

	private final List<HistoryTargetResolver> resolvers = new LinkedList<HistoryTargetResolver>();

	public void add(HistoryTargetResolver resolver) {
		resolvers.add(resolver);
	}

	public void remove(HistoryTargetResolver resolver) {
		resolvers.remove(resolver);
	}

	public final Widget resolve(String historyToken) {
		Widget target = null;

		for (final HistoryTargetResolver resolver : resolvers) {
			target = resolver.resolve(historyToken);

			if (target != null) {
				break;
			}
		}
		return target;
	}

}

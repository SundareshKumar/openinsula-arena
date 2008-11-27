package org.openinsula.arena.gwt.application.client.history;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.application.client.Application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultHistoryController implements HistoryController {

	private List<HistoryFilter> historyFilters;

	private HistoryTargetResolver historyTargetResolver;

	public final void onHistoryChanged(String historyToken) {
		if (historyToken == null || historyToken.trim().length() == 0) {
			final HistoryTokens historyTokens = GWT.create(HistoryTokens.class);

			historyToken = historyTokens.startPageToken();
		}

		if (historyFilters == null || historyFilters.isEmpty()) {
			changeHistoryTarget(historyToken);
		}
		else {
			final DefaultHistoryFilterChain historyFilterChain = new DefaultHistoryFilterChain();

			historyFilterChain.doFilter(historyToken);
		}
	}

	private void changeHistoryTarget(String historyToken) {
		final Widget target = historyTargetResolver.resolve(historyToken);

		if (target != null) {
			Application.getInstance().getWidgetDisplayer().show(target);
		}
	}

	public final void addHistoryFilter(HistoryFilter interceptor) {
		if (historyFilters == null) {
			historyFilters = new LinkedList<HistoryFilter>();
		}
		historyFilters.add(interceptor);
	}

	public final void removeHistoryFilter(HistoryFilter interceptor) {
		if (historyFilters != null) {
			historyFilters.remove(interceptor);
		}
	}

	public void setHistoryTargetResolver(HistoryTargetResolver historyTargetResolver) {
		this.historyTargetResolver = historyTargetResolver;
	}

	private final class DefaultHistoryFilterChain implements HistoryFilterChain {

		private final Iterator<HistoryFilter> iterator;

		public DefaultHistoryFilterChain() {
			iterator = new LinkedList<HistoryFilter>(historyFilters).iterator();
		}

		public void doFilter(String historyToken) {
			if (iterator.hasNext()) {
				iterator.next().doFilter(historyToken, this);
			}
			else {
				changeHistoryTarget(historyToken);
			}
		}

	}

}

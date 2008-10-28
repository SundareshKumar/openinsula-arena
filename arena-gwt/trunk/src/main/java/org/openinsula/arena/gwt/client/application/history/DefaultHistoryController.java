package org.openinsula.arena.gwt.client.application.history;

import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultHistoryController extends AbstractHistoryController {

	final HistoryTokens historyTokens = GWT.create(HistoryTokens.class);

	public final void onHistoryChanged(String historyToken) {
		if (historyToken == null || historyToken.trim().length() == 0) {
			showPage(historyTokens.startPageToken());
		}
		else if (!containsTarget(historyToken)) {
			showPage(historyTokens.pageNotFoundToken());
		}
		else {
			final List<HistoryChangeInterceptor> historyChangeInterceptors = getHistoryChangeInterceptors();

			for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
				if (!interceptor.preHistoryChange(historyToken)) {
					return;
				}
			}

			final boolean changed = changeHistoryTarget(historyToken);

			for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
				interceptor.postHistoryChange(historyToken, changed);
			}
		}
	}

	protected boolean changeHistoryTarget(String historyToken) {
		final Object target = getTarget(historyToken);

		if (target instanceof HistoryDispatcher) {
			final HistoryDispatcher dispatcher = (HistoryDispatcher) target;

			dispatcher.forwardHistoryChanged(historyToken);
		}
		else if (target instanceof Widget) {
			// Application.getInstance().getNavigationController().show((Widget)
			// target);
		}
		else {
			return false;
		}
		return true;
	}

	private void showPage(String token) {
		History.newItem(token);
	}

}

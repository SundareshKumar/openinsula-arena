package org.openinsula.arena.gwt.application.client.application;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.application.client.history.DefaultHistoryItemConfig;
import org.openinsula.arena.gwt.application.client.history.HistoryChangeHandler;
import org.openinsula.arena.gwt.application.client.history.HistoryChangeInterceptor;
import org.openinsula.arena.gwt.application.client.history.HistoryController;
import org.openinsula.arena.gwt.application.client.history.HistoryItem;
import org.openinsula.arena.gwt.application.client.history.HistoryItemConfig;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class ApplicationHistoryController implements HistoryController {

	private static final String DEFAULT_START_PAGE_TOKEN = "start";

	private final List<HistoryChangeInterceptor> historyChangeInterceptors = new LinkedList<HistoryChangeInterceptor>();

	private final HistoryItemConfig historyItemConfig;

	private String oldHistoryToken;

	public ApplicationHistoryController() {
		this(new DefaultHistoryItemConfig());
	}

	public ApplicationHistoryController(HistoryItemConfig historyItemConfig) {
		this.historyItemConfig = historyItemConfig;
	}

	public final void onHistoryChanged(String historyToken) {
		if (historyToken == null || historyToken.trim().length() == 0) {
			showStartPage();
			return;
		}
		if (!historyItemConfig.containsHistoryToken(historyToken)) {
			// TODO show page not found
			return;
		}

		final List<HistoryChangeInterceptor> historyChangeInterceptors = new LinkedList<HistoryChangeInterceptor>(
				this.historyChangeInterceptors);

		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			if (!interceptor.preHistoryChange(historyToken)) {
				return;
			}
		}

		final boolean success = doHistoryChangeHandling(historyToken);

		for (final HistoryChangeInterceptor interceptor : historyChangeInterceptors) {
			interceptor.postHistoryChange(historyToken, success);
		}

		oldHistoryToken = historyToken;
	}

	protected boolean doHistoryChangeHandling(String historyToken) {
		final HistoryItem historyItem = historyItemConfig.getHistoryItem(historyToken);

		return historyItem == null ? false : handleHistoryItem(historyItem, historyToken);
	}

	private boolean handleHistoryItem(HistoryItem historyItem, String historyToken) {
		final Object target = historyItem.getTarget();

		if (target instanceof HistoryChangeHandler) {
			final HistoryChangeHandler handler = (HistoryChangeHandler) target;

			handler.handleHistoryChange(oldHistoryToken, historyToken);
		}
		else if (target instanceof Widget) {
			Application.getInstance().getNavigationController().show((Widget) target);
		}
		else {
			return false;
		}

		return true;
	}

	private void showStartPage() {
		String startPageToken = Application.getInstance().getContext().getAttribute(START_PAGE);

		if (startPageToken == null) {
			startPageToken = DEFAULT_START_PAGE_TOKEN;
		}

		History.newItem(startPageToken);
	}

	public final void addHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.add(interceptor);
	}

	public final void removeHistoryChangeInterceptor(HistoryChangeInterceptor interceptor) {
		historyChangeInterceptors.remove(interceptor);
	}

	public HistoryItemConfig getHistoryItemConfig() {
		return historyItemConfig;
	}

}

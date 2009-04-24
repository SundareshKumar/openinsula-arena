package org.openinsula.arena.gwt.application.client.history.temp;

import com.google.gwt.user.client.ui.Widget;

public abstract class HistoryItem {

	private Widget widget;

	public HistoryItem() {
		Navigator.get().addHistoryItem(this);
	}

	public boolean isSingleton() {
		return true;
	}

	public boolean deleteOnHide() {
		return true;
	}

	public void onHide(final NavigatorFlow flow) {
		if (deleteOnHide()) {
			widget = null;
		}
		flow.continueFlow();
	}

	public final Widget show(final String historyToken) {
		if (widget == null || !isSingleton()) {
			widget = createWidget(historyToken);
		}
		return widget;
	}

	protected abstract Widget createWidget(String historyToken);

	public abstract String getHistoryToken();
}

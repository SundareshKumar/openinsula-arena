package org.openinsula.arena.gwt.components.client.ui.suggest.bean;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractTimedSuggestOracle extends BeanSuggestOracle {

	private final SuggestOracleTimer timer = new SuggestOracleTimer();

	private final int delay;

	private HasText textWidget;

	public AbstractTimedSuggestOracle() {
		this(500);
	}

	public AbstractTimedSuggestOracle(int delay) {
		this.delay = delay;
	}

	@Override
	public void requestSuggestions(final Request request, final Callback callback) {
		if (timer != null) {
			timer.cancel();
		}

		timer.request = request;
		timer.callback = callback;

		timer.schedule(delay);
	}

	protected void requestTimedSuggestions(Request request, Callback callback) {
		renderRequestSuggestions(request, callback);
	}

	protected void renderRequestSuggestions(Request request, Callback callback) {
		AbstractTimedSuggestOracle.super.requestSuggestions(request, callback);
	}

	private final class SuggestOracleTimer extends Timer {

		private Callback callback;

		private Request request;

		@Override
		public void run() {
			if (textWidget != null) {
				final String text = textWidget.getText();

				if (text == null || text.length() == 0) {
					return;
				}
			}
			requestTimedSuggestions(request, callback);
		}
	}

	public int getDelay() {
		return delay;
	}

	public HasText getTextWidget() {
		return textWidget;
	}

	public void setTextWidget(HasText textWidget) {
		this.textWidget = textWidget;
	}

}

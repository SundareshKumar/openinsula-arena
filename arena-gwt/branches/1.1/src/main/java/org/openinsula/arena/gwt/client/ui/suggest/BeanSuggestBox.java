package org.openinsula.arena.gwt.client.ui.suggest;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.Widget;

public abstract class BeanSuggestBox<T> extends Composite implements SourcesFocusEvents {
	private final SuggestBox suggestBox;
	private BeanSuggestBoxListener<T> listener;
	private Timer keyboardEnterTimer;
	private T selection;

	public BeanSuggestBox(final RemoteBeanSuggestOracle<T> oracle, final BeanSuggestBoxListener<T> listener) {
		setListener(listener);

		suggestBox = new SuggestBox(oracle);

		suggestBox.addEventHandler(new SuggestionHandler() {
			public void onSuggestionSelected(final SuggestionEvent event) {
				BeanSuggestion<T> suggestion = (BeanSuggestion<T>) event.getSelectedSuggestion();
				T selectedBean = suggestion.getBean();

				// Important, this prevents duplications
				if (keyboardEnterTimer != null) {
					keyboardEnterTimer.cancel();
				}

				onBeanSelect(selectedBean);
			}
		});

		suggestBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(final Widget sender, final char keyCode, final int modifiers) {
				if (keyCode == KEY_ENTER) {
					keyboardEnterTimer = new Timer() {
						public void run() {
							onNewEntry(suggestBox.getText());
						}
					};
					keyboardEnterTimer.schedule(400);
				}
			}
		});

		initWidget(suggestBox);
	}

	private void onBeanSelect(final T selectedBean) {
		this.selection = selectedBean;

		if (listener != null) {
			listener.onBeanSelect(this.selection);
		}
	}

	private void onNewEntry(final String text) {
		if (listener != null) {
			listener.onNewEntry(text);
		}
	}

	public void setListener(final BeanSuggestBoxListener<T> listener) {
		this.listener = listener;
	}

	public T getSelection() {
		return selection;
	}

	public SuggestBox getSuggestBox() {
		return suggestBox;
	}

	public void setFocus(final boolean b) {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				suggestBox.setFocus(b);
			}
		});

	}

	public void addFocusListener(FocusListener listener) {
		suggestBox.addFocusListener(listener);
	}

	public void removeFocusListener(FocusListener listener) {
		suggestBox.removeFocusListener(listener);
	}
	
}

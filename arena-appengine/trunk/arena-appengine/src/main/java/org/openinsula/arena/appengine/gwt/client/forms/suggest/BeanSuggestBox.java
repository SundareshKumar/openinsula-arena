package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class BeanSuggestBox<T> extends Composite implements HasFocus, SourcesFocusEvents {
	private final SuggestBox suggestBox;

	private final TextBoxBase textBox;

	private BeanSuggestBoxListener<T> listener;

	private Timer keyboardEnterTimer;

	private T selection;

	public BeanSuggestBox(final RemoteBeanSuggestOracle<T> oracle) {
		this(oracle, null);
	}

	public void showSuggestions() {
		showSuggestions(suggestBox, suggestBox.getText());
	}

	public void hideSuggestions() {
		hideSuggestions(suggestBox, new ArrayList<Suggestion>());
	}

	private native void hideSuggestions(SuggestBox sb, Collection<? extends Suggestion> suggestions) /*-{
		sb.@com.google.gwt.user.client.ui.SuggestBox::showSuggestions(Ljava/util/Collection;) (suggestions);
	}-*/;

	private native void showSuggestions(SuggestBox sb, String value) /*-{
		sb.@com.google.gwt.user.client.ui.SuggestBox::showSuggestions(Ljava/lang/String;) (value);
	}-*/;

	public void setEnabled(boolean enabled) {
		textBox.setEnabled(enabled);
	}

	public boolean isEnabled() {
		return textBox.isEnabled();
	}

	public BeanSuggestBox(final RemoteBeanSuggestOracle<T> oracle, final BeanSuggestBoxListener<T> listener) {
		setListener(listener);

		textBox = new TextBox();
		suggestBox = new SuggestBox(oracle, textBox);

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
			@Override
			public void onKeyDown(Widget sender, char keyCode, int modifiers) {
				switch (keyCode) {
				case KEY_ENTER:
					break;
				case KEY_ESCAPE:
					hideSuggestions();
				default:
					selection = null;
				}
			}

			@Override
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				BeanSuggestBox.this.listener.onKeyPress(sender, keyCode, modifiers);
				if (keyCode == KEY_ENTER) {
					if (selection == null) {
						keyboardEnterTimer = new Timer() {
							public void run() {
								onNewEntry(suggestBox.getText());
							}
						};
						keyboardEnterTimer.schedule(400);
					}
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

	public void addFocusListener(final FocusListener listener) {
		textBox.addFocusListener(listener);
	}

	public void removeFocusListener(final FocusListener listener) {
		textBox.removeFocusListener(listener);
	}

	public void setText(String value) {
		textBox.setText(value);
	}

	public void addKeyboardListener(KeyboardListener listener) {
		textBox.addKeyboardListener(listener);
	}

	public void removeKeyboardListener(KeyboardListener listener) {
		textBox.removeKeyboardListener(listener);
	}

	public int getTabIndex() {
		return suggestBox.getTabIndex();
	}

	public void setAccessKey(char key) {
		suggestBox.setAccessKey(key);
	}

	public void setTabIndex(int index) {
		suggestBox.setTabIndex(index);
	}

	public String getText() {
		return suggestBox.getText();
	}

}

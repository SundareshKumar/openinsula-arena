package org.openinsula.arena.gwt.components.client.ui.suggest.bean;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.utils.StringUtils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author João Galli
 */
public class BeanSuggestBox extends Composite implements HasFocus, HasText {

	private final SuggestBox suggestBox;

	private final BeanSuggestOracle beanSuggestOracle;

	private final List<BeanSelectionListener> listeners = new LinkedList<BeanSelectionListener>();

	private String suggestion;

	private Object selectedBean;

	public BeanSuggestBox(final BeanSuggestOracle oracle) {
		this(oracle, 3);
	}

	public BeanSuggestBox(final BeanSuggestOracle oracle, final int minText) {
		beanSuggestOracle = oracle;
		suggestBox = new SuggestBox(oracle);

		Event.addEventPreview(new EventPreview() {
			public boolean onEventPreview(final Event event) {
				final int type = DOM.eventGetType(event);

				if (type == Event.ONKEYUP) {
					return suggestBox.getText().length() >= minText;
				}
				return true;
			}
		});

		if (oracle instanceof AbstractTimedSuggestOracle) {
			((AbstractTimedSuggestOracle) oracle).setTextWidget(this);
		}

		suggestBox.addEventHandler(new SuggestionHandler() {
			public void onSuggestionSelected(final SuggestionEvent event) {
				suggestion = event.getSelectedSuggestion().getReplacementString();
				selectedBean = beanSuggestOracle.findBeanByValue(suggestion);

				for (final BeanSelectionListener listener : listeners) {
					listener.beanSelected(selectedBean);
				}
			}
		});
		suggestBox.addKeyboardListener(new KeyboardListenerAdapter() {
			@Override
			public void onKeyUp(final Widget sender, final char keyCode, final int modifiers) {
				onTextChange();
			}
		});
		suggestBox.addChangeListener(new ChangeListener() {
			public void onChange(final Widget sender) {
				onTextChange();
			}
		});
		
		initWidget(suggestBox);
		
	}

	private void onTextChange() {
		final String text = suggestBox.getText();

		if (selectedBean != null && !suggestion.equals(text) || StringUtils.isEmpty(text)) {
			selectedBean = null;

			for (final BeanSelectionListener listener : listeners) {
				listener.beanDeselected();
			}
		}
	}

	public void addBeanSelectionListener(final BeanSelectionListener listener) {
		listeners.add(listener);
	}

	public void removeBeanSelectionListener(final BeanSelectionListener listener) {
		listeners.remove(listener);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSelectedBean() {
		return (T) selectedBean;
	}

	public String getText() {
		return suggestBox.getText();
	}

	public void setText(final String text) {
		suggestBox.setText(text);
	}

	public void setLimit(final int limit) {
		suggestBox.setLimit(limit);
	}

	public int getTabIndex() {
		return suggestBox.getTabIndex();
	}

	public void setAccessKey(final char key) {
		suggestBox.setAccessKey(key);
	}

	public void setFocus(final boolean focused) {
		suggestBox.setFocus(focused);
	}

	public void setTabIndex(final int index) {
		suggestBox.setTabIndex(index);
	}

	public void addFocusListener(final FocusListener listener) {
		suggestBox.addFocusListener(listener);
	}

	public void removeFocusListener(final FocusListener listener) {
		suggestBox.removeFocusListener(listener);
	}

	public void addKeyboardListener(final KeyboardListener listener) {
		suggestBox.addKeyboardListener(listener);
	}

	public void removeKeyboardListener(final KeyboardListener listener) {
		suggestBox.removeKeyboardListener(listener);
	}

}

/*
 * Copyright 2008 Jeff Dwyer
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.openinsula.arena.gwt.client.beans.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractCompleter<T> extends Composite {

	private final AbstractSuggestOracle<T> oracle;
	private final CompleteListener<T> completeListener;
	private final SuggestBox suggestBox;

	private Timer keyboardEnterTimer;

	public AbstractCompleter(final AbstractSuggestOracle<T> oracle, final CompleteListener<T> completeListener) {
		super();
		this.oracle = oracle;
		this.completeListener = completeListener;

		suggestBox = new SuggestBox(oracle);
		suggestBox.addEventHandler(new SuggestionHandler() {

			public void onSuggestionSelected(final SuggestionEvent event) {

				if (keyboardEnterTimer != null) {
					keyboardEnterTimer.cancel();
				}

				complete(event.getSelectedSuggestion().getReplacementString());
			}
		});

		suggestBox.addKeyboardListener(new KeyboardListenerAdapter() {

			public void onKeyPress(final Widget sender, final char keyCode, final int modifiers) {
				if (keyCode == KEY_ENTER) {

					keyboardEnterTimer = new Timer() {
						public void run() {
							complete(suggestBox.getText());
						}
					};
					keyboardEnterTimer.schedule(400);
				}
			}
		});
		initWidget(suggestBox);
	}

	/**
	 * public so we can call this at any time
	 */
	public void complete() {
		complete(suggestBox.getText());
	}

	/**
	 * Careful to prevent dupes, one from enter key keyboard listener, one from
	 * the enter key selecting a suggestion. We need the keyboard listener
	 * because we want the enter key to add the current text when there's no
	 * suggestion.
	 *
	 * @param completeStr
	 */
	private void complete(final String completeStr) {
		oracle.fireCompleteListenerFromCompleteString(completeStr, completeListener);

		suggestBox.setText("");
	}

	public void setText(final String string) {
		suggestBox.setText(string);
	}

	public String getText() {
		return suggestBox.getText();
	}

	public void setFocus(final boolean b) {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				suggestBox.setFocus(b);
			}
		});

	}

}

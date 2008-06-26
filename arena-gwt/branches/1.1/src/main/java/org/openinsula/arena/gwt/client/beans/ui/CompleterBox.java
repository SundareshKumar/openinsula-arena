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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;

public class CompleterBox<T> extends Composite {

	private final SuggestBox suggestBox;
	
	private AbstractSuggestOracle<T> suggestOracle;
	
	public void addCompleteListener(CompleteListener<T> listener) {
		suggestBox.addEventHandler(listener);
	}
	
	public AbstractSuggestOracle<T> getSuggestOracle() {
		return suggestOracle;
	}
	
	public CompleterBox(final AbstractSuggestOracle<T> oracle) {
		super();
		suggestBox = new SuggestBox(oracle);
		this.suggestOracle = oracle;
		initWidget(suggestBox);
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

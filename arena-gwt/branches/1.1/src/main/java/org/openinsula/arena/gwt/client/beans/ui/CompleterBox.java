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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompleterBox<T> extends Composite {

	private final SuggestBox suggestBox;
	
	private DeckPanel deckPanel;
	
	private Label selectedItemLabel;
	
	private Hyperlink changeSelectionLink;
	
	private AbstractSuggestOracle<T> suggestOracle;
	
	private Timer keyboardEnterTimer;
	
	private T selectedItem;
	
	private CompleterBoxRenderer<T> renderer = new StringCompleterBoxRenderer<T>();
	
	public void setRenderer(CompleterBoxRenderer<T> renderer) {
		this.renderer = renderer;
	}

	public void setSelectedItem(T selectedItem) {
		this.selectedItem = selectedItem;
		if (selectedItem != null) {
			GWT.log("label: " + selectedItemLabel, null);
			GWT.log("renderer: " + renderer, null);
			deckPanel.showWidget(1);
			selectedItemLabel.setText(renderer.render(selectedItem));
		} else {
			deckPanel.showWidget(0);
			selectedItemLabel.setText("");
		}
	}

	public T getSelectedItem() {
		return selectedItem;
	}

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
		
		suggestBox.addEventHandler(new CompleteListener<T>() {
			@Override
			public void onComplete(T result) {
				if (keyboardEnterTimer != null) {
					keyboardEnterTimer.cancel();
				}
				setSelectedItem(result);
				
				deckPanel.showWidget(1);
			}
		});
		
		suggestBox.addKeyboardListener(new KeyboardListenerAdapter() {

			@Override
			public void onKeyPress(final Widget sender, final char keyCode, final int modifiers) {
				if (keyCode == KEY_ENTER) {
					keyboardEnterTimer = new Timer() {
						@Override
						public void run() {
						}
					};
					keyboardEnterTimer.schedule(400);
				}
			}
		});
		
		deckPanel = new DeckPanel();
		changeSelectionLink = new Hyperlink("Alterar Selecao", null);
		changeSelectionLink.setStyleName("completer-changeSelectionLink");
		selectedItemLabel = new Label();
		
		changeSelectionLink.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				deckPanel.showWidget(0);
				selectedItem = null;
				selectedItemLabel.setText("");
			}
		});
		
		VerticalPanel panel =  new VerticalPanel();
		panel.add(changeSelectionLink);
		panel.add(selectedItemLabel);
		
		deckPanel.add(suggestBox);
		deckPanel.add(panel);
		
		deckPanel.showWidget(0);
		initWidget(deckPanel);
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

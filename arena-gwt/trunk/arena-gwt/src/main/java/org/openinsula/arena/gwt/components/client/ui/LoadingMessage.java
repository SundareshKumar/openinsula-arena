package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class LoadingMessage {
	private static LoadingMessage _instance;

	private final PopupPanel panel;
	
	public static void show() {
		show("Carregando...");
	}

	public static void show(final String message) {
		PopupPanel panel = get().panel;
		((HTML) panel.getWidget()).setHTML(message);
		panel.center();
	}

	public static void hide() {
		get().panel.hide();
	}

	private static LoadingMessage get() {
		if (_instance == null) {
			_instance = new LoadingMessage();
		}
		return _instance;
	}

	private LoadingMessage() {
		panel = new PopupPanel(false, true);
		panel.setAnimationEnabled(true);
		panel.setStyleName("LoadingMessage");
		panel.add(new HTML());
	}

}

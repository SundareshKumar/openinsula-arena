package org.openinsula.arena.gwt.components.client.form;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Lucas K Mogari
 */
public class Action {

	private String text;

	private Image image;

	private String historyToken;

	private ClickListener[] clickListeners;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ClickListener[] getClickListeners() {
		return clickListeners;
	}

	public void setClickListeners(ClickListener... clickListeners) {
		this.clickListeners = clickListeners;
	}

	public String getHistoryToken() {
		return historyToken;
	}

	public void setHistoryToken(String historyToken) {
		this.historyToken = historyToken;
	}

}

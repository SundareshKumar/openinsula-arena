package org.openinsula.arena.gwt.client.ui.form;

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

	private boolean enabled = true;

	public Action(String text, ClickListener... clickListeners) {
		this(text, null, null, clickListeners);
	}

	public Action(String text, String historyToken, ClickListener... clickListeners) {
		this(text, historyToken, null, clickListeners);
	}

	public Action(String text, String historyToken, Image image, ClickListener... clickListeners) {
		this.text = text;
		this.historyToken = historyToken;
		this.image = image;
		this.clickListeners = clickListeners;
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

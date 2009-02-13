package org.openinsula.arena.gwt.form.client;

public interface WindowRenderer extends UIModelRenderer {

	void onTitleChange(String oldValue, String newValue);

	void onBodyChange(UIModel<?> oldValue, UIModel<?> newValue);

}

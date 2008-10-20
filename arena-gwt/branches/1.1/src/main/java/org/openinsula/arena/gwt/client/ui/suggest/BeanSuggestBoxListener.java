package org.openinsula.arena.gwt.client.ui.suggest;

import com.google.gwt.user.client.ui.Widget;

public interface BeanSuggestBoxListener<T> {

	void onBeanSelect(T result);

	void onNewEntry(String value);

	void onKeyPress(Widget sender, char keyCode, int modifiers);

}

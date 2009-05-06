package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import com.google.gwt.user.client.ui.Widget;

public interface BeanSuggestBoxListener<T> {

	void onBeanSelect(T result);

	void onNewEntry(String value);

	void onKeyPress(Widget sender, char keyCode, int modifiers);

}

package org.openinsula.arena.gwt.client.ui.suggest;

public interface BeanSuggestBoxListener<T> {

	void onBeanSelect(T result);

	void onNewEntry(String value);

}

package org.openinsula.arena.gwt.client.components.test.search;


public interface SearchFormActionListener<T> {

	void onModelSelected(T bean);

	void onNewEntry(T bean);

}

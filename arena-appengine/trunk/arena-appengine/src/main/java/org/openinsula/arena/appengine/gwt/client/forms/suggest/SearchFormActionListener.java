package org.openinsula.arena.appengine.gwt.client.forms.suggest;


public interface SearchFormActionListener<T> {

	void onModelSelected(T bean);

	void onNewEntry(T bean);

}

package org.openinsula.arena.gwt.client.beans.ui;

import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;

public abstract class CompleteListener<T> implements SuggestionHandler {

	public abstract void onComplete(T result);
	
	@SuppressWarnings("unchecked")
	public void onSuggestionSelected(SuggestionEvent event) {
		BeanSuggestion<T> suggestion = (BeanSuggestion<T>) event.getSelectedSuggestion();
		onComplete(suggestion.getBean());
	}

}

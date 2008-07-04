package org.openinsula.arena.gwt.client.ui.suggest;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public abstract class BeanSuggestion<T> implements Suggestion {

	private T bean;
	
	public abstract String getDisplayString(T bean);
	
	public abstract String getReplacementString(T bean);
	
	public BeanSuggestion(T bean) {
		this.bean = bean;
	}
	
	public String getDisplayString() {
		return getDisplayString(bean);
	}

	public String getReplacementString() {
		return getReplacementString(bean);
	}

	public T getBean() {
		return bean;
	}
	
}

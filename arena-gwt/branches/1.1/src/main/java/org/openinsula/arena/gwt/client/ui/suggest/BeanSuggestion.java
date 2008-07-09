package org.openinsula.arena.gwt.client.ui.suggest;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class BeanSuggestion<T> implements Suggestion {

	private final T bean;

	private String query;

	public BeanSuggestion(final T bean) {
		this(bean, null);
	}

	public BeanSuggestion(final T bean, final String query) {
		this.bean = bean;
		this.query = query;
	}

	public final String getDisplayString() {
		String displayString = getBeanDisplayString(bean);
		return query == null ? displayString : RemoteBeanSuggestOracle.highlight(displayString, query);
	}

	public final String getReplacementString() {
		return getBeanReplacementString(bean);
	}

	public T getBean() {
		return bean;
	}

	protected String getBeanDisplayString(final T bean) {
		return String.valueOf(bean);
	}

	protected String getBeanReplacementString(final T bean) {
		return String.valueOf(bean);
	}

}

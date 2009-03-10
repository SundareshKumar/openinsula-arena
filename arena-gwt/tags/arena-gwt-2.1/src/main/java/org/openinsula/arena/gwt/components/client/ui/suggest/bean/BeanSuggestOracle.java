package org.openinsula.arena.gwt.components.client.ui.suggest.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * @author João Galli
 */
public abstract class BeanSuggestOracle extends SuggestOracle {

	private final List<Object> beans = new ArrayList<Object>();

	private final MultiWordSuggestOracle multiWordSuggestOracle = new MultiWordSuggestOracle();

	protected abstract String getBeanValue(Object obj);

	public void add(Object obj) {
		beans.add(obj);

		multiWordSuggestOracle.add(getBeanValue(obj));
	}

	public void addAll(Collection<?> beans) {
		if (beans != null) {
			for (final Object obj : beans) {
				add(obj);
			}
		}
	}

	public void setBeans(Collection<?> beans) {
		clear();
		addAll(beans);
	}

	@Override
	public boolean isDisplayStringHTML() {
		return true;
	}

	/**
	 * Procura o item
	 */
	@SuppressWarnings("unchecked")
	public <T> T findBeanByValue(String value) {
		for (final Object obj : beans) {
			final String beanValue = getBeanValue(obj);

			if (value.equals(beanValue)) {
				return (T) obj;
			}
		}
		return null;
	}

	public void clear() {
		beans.clear();
		multiWordSuggestOracle.clear();
	}

	@Override
	public void requestSuggestions(Request request, Callback callback) {
		multiWordSuggestOracle.requestSuggestions(request, callback);
	}

}

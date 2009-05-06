package org.openinsula.arena.appengine.gwt.client.forms.suggest;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem;

public class EditionSearchFormTransitionListener<T> implements SearchFormTransitionListener<T> {

	private String labelOnDetailShow;

	private String labelOnSearchShow;

	private FormItem formItem;

	public EditionSearchFormTransitionListener(FormItem formItem, String labelOnDetailShow, String labelOnSearchShow) {
		this.formItem = formItem;
		this.labelOnDetailShow = labelOnDetailShow;
		this.labelOnSearchShow = labelOnSearchShow;
	}

	public boolean beforeDetailFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, boolean editionMode) {
		formItem.label(labelOnDetailShow);
		return true;
	}

	public boolean beforeSearchFormShow(AbstractSearchFormTemplate<T> searchForm) {
		formItem.label(labelOnSearchShow);
		return true;
	}

}

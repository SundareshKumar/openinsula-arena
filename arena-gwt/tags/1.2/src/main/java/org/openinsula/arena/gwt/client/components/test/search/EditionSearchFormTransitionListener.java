package org.openinsula.arena.gwt.client.components.test.search;

import org.openinsula.arena.gwt.client.ui.form.FormItem;

public class EditionSearchFormTransitionListener<T> implements SearchFormTransitionListener<T> {

	private String labelOnDetailShow;

	private String labelOnSearchShow;

	private FormItem<?> formItem;

	public EditionSearchFormTransitionListener(FormItem<?> formItem, String labelOnDetailShow, String labelOnSearchShow) {
		this.formItem = formItem;
		this.labelOnDetailShow = labelOnDetailShow;
		this.labelOnSearchShow = labelOnSearchShow;
	}

	public boolean beforeDetailFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, boolean editionMode) {
		formItem.getLabel().setText(labelOnDetailShow);
		return true;
	}

	public boolean beforeSearchFormShow(AbstractSearchFormTemplate<T> searchForm) {
		formItem.getLabel().setText(labelOnSearchShow);
		return true;
	}

}

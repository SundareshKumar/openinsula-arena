package org.openinsula.arena.appengine.gwt.client.forms.suggest;

public interface SearchFormTransitionListener<T> {

	/**
	 * @param detailForm
	 * @param editionMode
	 * @return valor booleano indicando se a transição de ve ser realizada.
	 */
	boolean beforeDetailFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, boolean editionMode);

	/**
	 * @param searchForm
	 * @return valor booleano indicando se a transição de ve ser realizada.
	 */
	boolean beforeSearchFormShow(AbstractSearchFormTemplate<T> searchForm);

}

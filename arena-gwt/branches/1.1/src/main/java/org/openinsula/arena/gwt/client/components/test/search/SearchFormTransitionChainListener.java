package org.openinsula.arena.gwt.client.components.test.search;

public abstract class SearchFormTransitionChainListener<T> {

	private SearchFormTransitionChainListener<T> nextListener;

	void setNextListener(SearchFormTransitionChainListener<T> nextListener) {
		if (this.nextListener != null) {
			setNextListener(this.nextListener.getNextListener());
		}

		this.nextListener = nextListener;
	}

	void removeListener(SearchFormTransitionChainListener<T> listener) {
		if (listener.equals(nextListener)) {
			nextListener = nextListener.getNextListener();
		}
	}

	SearchFormTransitionChainListener<T> getNextListener() {
		return nextListener;
	}

	abstract void onDetailFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, SearchFormTransitionChainListener<T> chainListener, boolean editionMode);
	abstract void onSearchFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, SearchFormTransitionChainListener<T> chainListener);

	void onDetailFormShow(AbstractDetailsSearchFormTemplate<T> detailForm, boolean editionMode) {
		onDetailFormShow(detailForm, getNextListener(), editionMode);
	}

	void onSearchFormShow(AbstractDetailsSearchFormTemplate<T> detailForm) {
		onSearchFormShow(detailForm, getNextListener());
	}

}

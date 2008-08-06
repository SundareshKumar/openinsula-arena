package org.openinsula.arena.gwt.client.ui.list;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class RemoteListBoxModel<T> extends DefaultListBoxModel<T> {

	private final AsyncCallback<List<T>> callback = new AsyncCallback<List<T>>() {

		public void onFailure(Throwable caught) {
			fireOnLoadComplete();
			Window.alert(caught.getMessage());
		}

		public void onSuccess(List<T> result) {
			setValues(result);
		}

	};

	public RemoteListBoxModel() {
		loadData(callback);
	}

	public final void refresh() {
		fireOnLoading();
		loadData(callback);
	}

	/**
	 * <b>DO NOT call this directly</b>. Always use <b>{@link #refresh()}</b>.
	 */
	public void setValues(final List<T> values) {
		super.setValues(values);
		fireOnLoadComplete();
	}

	private void fireOnLoading() {
		for (ListBoxModelListener listener : listeners) {
			if (listener instanceof RemoteListBoxModelListener) {
				((RemoteListBoxModelListener) listener).onLoading();
			}
		}
	}

	private void fireOnLoadComplete() {
		for (ListBoxModelListener listener : listeners) {
			if (listener instanceof RemoteListBoxModelListener) {
				((RemoteListBoxModelListener) listener).onLoadComplete();
			}
		}
	}

	protected abstract void loadData(AsyncCallback<List<T>> callback);

}

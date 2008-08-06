package org.openinsula.arena.gwt.client.remote;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ResultCallback<T> implements AsyncCallback<T> {

	public void onFailure(final Throwable error) {
		GWT.log(error.getMessage(), error);
	}
}

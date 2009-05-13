package org.openinsula.arena.appengine.gwt.client.json;

import java.io.Serializable;

import org.openinsula.arena.appengine.gwt.client.http.DefaultHTTPRequestCallback;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class JsonSerializerCallback<T extends Serializable> extends DefaultHTTPRequestCallback {

	private final T vo;

	public JsonSerializerCallback(final T vo) {
		super();
		this.vo = vo;
	}

	public JsonSerializerCallback(final T vo, final boolean treatEmptyResponseAsFault) {
		super(treatEmptyResponseAsFault);
		this.vo = vo;
	}

	@Override
	public final void onSuccess() {
		super.onSuccess();

		if (!isDone()) {
			JsonRemoteSerializerAsync jsonRemoteSerializer = JsonRemoteSerializerAsync.Impl.get();

			jsonRemoteSerializer.fromJson(getResponse().getText(), vo, new AsyncCallback<T>() {
				public void onFailure(final Throwable error) {
					updateError(error);
					onError();
				}

				public void onSuccess(final T result) {
					JsonSerializerCallback.this.onSuccess(result);
				}
			});
		}
	}

	protected abstract void onSuccess(final T parsedJson);

}

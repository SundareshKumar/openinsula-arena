package org.openinsula.arena.appengine.gwt.client.json;

import java.io.Serializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface JsonRemoteSerializerAsync {

	void toJson(Serializable jsonObject, AsyncCallback<String> callback);
	
	<T extends Serializable> void fromJson(String json, T template, AsyncCallback<T> callback);

	public static class Impl {
		private static JsonRemoteSerializerAsync instance;

		public static JsonRemoteSerializerAsync get() {
			if (instance == null) {
				instance = GWT.create(JsonRemoteSerializer.class);
			}
			return instance;
		}

	}

}

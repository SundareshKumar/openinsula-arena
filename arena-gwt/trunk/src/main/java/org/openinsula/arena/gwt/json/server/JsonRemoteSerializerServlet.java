package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;

import org.openinsula.arena.gwt.json.client.JsonRemoteSerializer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class JsonRemoteSerializerServlet extends RemoteServiceServlet implements JsonRemoteSerializer {

	private final JsonRemoteSerializer delegate = new XStreamJsonSerializer();

	public <T extends Serializable> T fromJson(final String jsonString, final T template) {
		return delegate.fromJson(jsonString, template);
	}

	public String toJson(final Serializable jsonObject) {
		return delegate.toJson(jsonObject);
	}

}

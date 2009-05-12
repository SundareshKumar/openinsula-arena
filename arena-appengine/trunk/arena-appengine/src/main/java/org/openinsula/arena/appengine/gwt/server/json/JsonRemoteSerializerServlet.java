package org.openinsula.arena.appengine.gwt.server.json;

import java.io.Serializable;

import org.openinsula.arena.appengine.gwt.client.json.JsonRemoteSerializer;
import org.openinsula.arena.appengine.gwt.client.json.VoFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class JsonRemoteSerializerServlet extends RemoteServiceServlet implements JsonRemoteSerializer {

	private final XStreamJsonSerializer delegate;

	public JsonRemoteSerializerServlet() {
		this(null);
	}

	public JsonRemoteSerializerServlet(final VoFactory voFactory) {
		delegate = new XStreamJsonSerializer(voFactory);
	}

	public <T extends Serializable> T fromJson(final String jsonString, final T template) {
		return delegate.fromJson(jsonString, template);
	}

	public String toJson(final Serializable jsonObject) {
		return delegate.toJson(jsonObject);
	}

}

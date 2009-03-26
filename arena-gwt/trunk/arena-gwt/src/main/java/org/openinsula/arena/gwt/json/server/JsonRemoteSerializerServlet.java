package org.openinsula.arena.gwt.json.server;

import java.io.Serializable;

import org.openinsula.arena.gwt.json.client.JsonRemoteSerializer;
import org.openinsula.arena.gwt.json.client.VoFactory;

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

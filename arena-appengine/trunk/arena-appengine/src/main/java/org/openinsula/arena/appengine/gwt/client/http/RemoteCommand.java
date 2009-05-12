package org.openinsula.arena.appengine.gwt.client.http;

import org.openinsula.arena.appengine.gwt.client.json.JsonRemoteSerializerAsync;

import com.google.gwt.user.client.Command;

/**
 * @author Eduardo Rebola
 */
public abstract class RemoteCommand implements Command {

	protected abstract EnhancedRequestBuilder createRequestBuilder();

	protected final JsonRemoteSerializerAsync jsonSerializer() {
		return JsonRemoteSerializerAsync.Impl.get();
	}

}

package org.openinsula.arena.gwt.http.client;

import org.openinsula.arena.gwt.json.client.JsonRemoteSerializerAsync;

import com.google.gwt.user.client.Command;

/**
 * @author Eduardo Rebola
 */
public abstract class RemoteCommand implements Command {
	
	private EnhancedRequestBuilder requestBuilder;
	
	protected final EnhancedRequestBuilder requestBuilder() {
		if (requestBuilder == null) {
			requestBuilder = createRequestBuilder();
		}
		return requestBuilder;
	}
	
	protected abstract EnhancedRequestBuilder createRequestBuilder();

	protected final JsonRemoteSerializerAsync jsonSerializer() {
		return JsonRemoteSerializerAsync.Impl.get();
	}

}

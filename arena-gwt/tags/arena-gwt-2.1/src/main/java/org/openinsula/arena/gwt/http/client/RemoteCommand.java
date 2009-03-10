package org.openinsula.arena.gwt.http.client;

import org.openinsula.arena.gwt.json.client.JsonRemoteSerializerAsync;

import com.google.gwt.user.client.Command;

/**
 * @author Eduardo Rebola
 */
public abstract class RemoteCommand implements Command {
	
	/**
	 * @deprecated In favor of {@link #createRequestBuilder()}.
	 */
	protected final EnhancedRequestBuilder requestBuilder() {
		return createRequestBuilder();
	}
	
	protected abstract EnhancedRequestBuilder createRequestBuilder();

	protected final JsonRemoteSerializerAsync jsonSerializer() {
		return JsonRemoteSerializerAsync.Impl.get();
	}

}

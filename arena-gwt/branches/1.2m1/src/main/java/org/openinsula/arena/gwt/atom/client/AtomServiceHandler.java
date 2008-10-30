package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.http.client.rest.AbstractRestServiceHandler;

/**
 * @author Lucas K Mogari
 */
public class AtomServiceHandler extends AbstractRestServiceHandler {

	public boolean matches(String url, Object data) {
		return data instanceof PublishingEntry;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String getRequestDataValue(Object data) {
		final PublishingEntry entry = (PublishingEntry) data;
		return entry.toXml();
	}

}

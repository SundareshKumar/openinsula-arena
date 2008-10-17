package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 */
public class EntryRequestCallback<T extends Entry> extends XmlRequestCallback {

	private T entry;

	public EntryRequestCallback() {
	}

	public EntryRequestCallback(T entry) {
		this.entry = entry;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final void onXmlParsed(Document document) {
		if (entry == null) {
			entry = (T) new Entry();
		}

		entry.parse(document);

		onEntryParsed(entry);
	}

	protected void onEntryParsed(T entry) {
	}

}

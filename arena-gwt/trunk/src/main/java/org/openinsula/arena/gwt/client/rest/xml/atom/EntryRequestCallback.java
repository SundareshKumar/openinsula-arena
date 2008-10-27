package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 */
public abstract class EntryRequestCallback<T extends Entry> extends XmlRequestCallback {

	private T entry;

	public EntryRequestCallback() {
	}

	public EntryRequestCallback(T entry) {
		this.entry = entry;
	}

	protected abstract void onEntryParsed(T entry);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#onXmlParsed
	 * (com.google.gwt.xml.client.Document)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected final void onXmlParsed(Document document) {
		if (entry == null) {
			entry = (T) new Entry();
		}

		entry.parse(document);

		onEntryParsed(entry);
	}

	public void setEntry(T entry) {
		this.entry = entry;
	}

}

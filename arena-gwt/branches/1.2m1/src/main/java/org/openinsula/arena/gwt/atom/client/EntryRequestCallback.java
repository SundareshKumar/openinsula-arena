package org.openinsula.arena.gwt.atom.client;

import org.openinsula.arena.gwt.http.client.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public abstract class EntryRequestCallback<T extends BaseEntry<T>> extends XmlRequestCallback {

	private T entry;

	public EntryRequestCallback() {
	}

	public EntryRequestCallback(T entry) {
		this.entry = entry;
	}

	protected abstract void doWithEntry(T entry);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#doWithXml
	 * (com.google.gwt.xml.client.Document)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doWithXml(Document document) {
		if (entry == null) {
			entry = (T) new Entry();
		}

		doWithEntry(entry.parse(document));
	}

	void setEntry(T entry) {
		this.entry = entry;
	}

}

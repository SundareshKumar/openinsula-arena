package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback;

import com.google.gwt.xml.client.Document;

/**
 * @author Lucas K Mogari
 * @author Eduardo Rebola
 */
public abstract class EntryRequestCallback<T extends BaseEntry<T>> extends XmlRequestCallback {

	private final T entry;

	@SuppressWarnings("unchecked")
	public EntryRequestCallback() {
		this((T) new Entry());
	}

	public EntryRequestCallback(final T entry) {
		this.entry = entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openinsula.arena.gwt.client.rest.xml.XmlRequestCallback#doWithXml
	 * (com.google.gwt.xml.client.Document)
	 */
	protected final void doWithXml(final Document document) {
		doWithEntry(entry.parse(document));
	}

	protected abstract void doWithEntry(T entry);

}

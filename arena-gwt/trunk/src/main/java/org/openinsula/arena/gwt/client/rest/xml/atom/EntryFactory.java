package org.openinsula.arena.gwt.client.rest.xml.atom;

/**
 * @author Lucas K Mogari
 */
public interface EntryFactory<T extends Entry> {

	public T createEntry();

}

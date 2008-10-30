package org.openinsula.arena.gwt.atom.client;

/**
 * @author Lucas K Mogari
 */
public interface EntryFactory<T extends BaseEntry<T>> {

	public T createEntry();

}

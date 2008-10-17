package org.openinsula.arena.gwt.client.rest.xml.atom;

/**
 * @author Lucas K Mogari
 */
public class Feed extends BaseFeed<Entry> {

	public Feed() {
	}

	public Feed(String title, String id) {
		super(title, id);
	}

	public Entry createEntry() {
		return new Entry();
	}

}

package org.openinsula.arena.gwt.atom.client;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
public class Feed<T extends BaseEntry<T>> extends AtomResource {

	private Text subtitle;

	private String icon;

	private String logo;

	private List<T> entries = new LinkedList<T>();

	public Feed() {
	}

	public Feed(String title, String id) {
		super(id, title);
	}

	public Text getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Text subtitle) {
		this.subtitle = subtitle;
	}

	public void addEntry(T entry) {
		entries.add(entry);
	}

	public void removeEntry(T entry) {
		entries.remove(entry);
	}

	public List<T> getEntries() {
		return entries;
	}

	public void setEntries(List<T> entries) {
		this.entries = entries;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}

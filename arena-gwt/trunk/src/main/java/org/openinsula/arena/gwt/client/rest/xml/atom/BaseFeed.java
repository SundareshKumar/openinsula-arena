package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class BaseFeed<T extends Entry> extends AtomResource implements EntryFactory<T> {

	private Text subtitle;

	private String icon;

	private String logo;

	private List<T> entries = new LinkedList<T>();

	public BaseFeed() {
	}

	public BaseFeed(String title, String id) {
		super(id, title);
	}

	@Override
	protected void initNodeParsers() {
		super.initNodeParsers();

		addParser("subtitle", new SubtitleNodeParser());
		addParser("icon", new IconNodeParser());
		addParser("logo", new LogoNodeParser());
		addParser("entry", new EntryNodeParser());
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

	private final class SubtitleNodeParser extends TextNodeParser {

		@Override
		public void parse(Node node) {
			super.parse(node);

			subtitle = getText();
		}

	}

	private final class IconNodeParser implements NodeParser {

		public void parse(Node node) {
			icon = XmlParserUtils.getText(node);
		}

	}

	private final class LogoNodeParser implements NodeParser {

		public void parse(Node node) {
			logo = XmlParserUtils.getText(node);
		}

	}

	private final class EntryNodeParser implements NodeParser {

		public void parse(Node node) {
			final T entry = createEntry();

			if (entry != null) {
				entry.parse(node);

				addEntry(entry);
			}
		}

	}

}

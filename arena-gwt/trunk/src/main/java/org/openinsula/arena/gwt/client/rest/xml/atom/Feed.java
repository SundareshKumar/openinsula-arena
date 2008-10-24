package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class Feed<T extends Entry> extends AtomResource {

	private Text subtitle;

	private String icon;

	private String logo;

	private List<T> entries = new LinkedList<T>();

	private EntryFactory<T> entryFactory;

	public Feed() {
	}

	public Feed(EntryFactory<T> entryFactory) {
		this.entryFactory = entryFactory;
	}

	public Feed(String title, String id) {
		super(id, title);
	}

	{
		final CompositeNodeParser rootNodeParser = getRootNodeParser();

		rootNodeParser.addParser("subtitle", new SubtitleNodeParser());
		rootNodeParser.addParser("icon", new IconNodeParser());
		rootNodeParser.addParser("logo", new LogoNodeParser());
		rootNodeParser.addParser("entry", new EntryNodeParser());
	}

	public void setEntryFactory(EntryFactory<T> entryFactory) {
		this.entryFactory = entryFactory;
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

		@SuppressWarnings("unchecked")
		public void parse(Node node) {
			T entry = entryFactory.createEntry();

			if (entry == null) {
				entry = (T) new Entry();
			}

			entry.parse(node);

			addEntry(entry);
		}

	}

}

package org.openinsula.arena.gwt.atom.client;

import java.util.Date;

import org.openinsula.arena.gwt.http.client.rest.RestService;
import org.openinsula.arena.gwt.xml.client.AbstractParsedNodeResultAppender;
import org.openinsula.arena.gwt.xml.client.CompositeNodeParser;
import org.openinsula.arena.gwt.xml.client.NodeParser;
import org.openinsula.arena.gwt.xml.client.NodeTextAppender;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public abstract class BaseEntry<T extends BaseEntry<T>> extends AtomResource implements NodeParser<T> {

	private Text summary;

	private Content content;

	private Date published;

	private RestService restService;

	private final CompositeNodeParser<T> entryNodeParser = new CompositeNodeParser<T>();

	public BaseEntry() {
		this(null, null);
	}

	public BaseEntry(String title, String id) {
		super(id, title);

		initParsers();
	}

	protected void initParsers() {
		addNodeParser("summary", new AtomTextNodeAppender<T>() {
			@Override
			public void appendResultTo(T entry, Text summary) {
				setSummary(summary);
			}
		});
		addNodeParser("published", new NodeTextAppender<T>() {
			@Override
			public void appendResultTo(T entry, String published) {
				setPublished(null); // FIXME
			}
		});
	}

	public T parse(Node node) {
		return entryNodeParser.parse(node);
	}

	public void addNodeParser(String nodeName, NodeParser<?> parser) {
		entryNodeParser.addNodeParser(nodeName, parser);
	}

	public void addNodeParser(String nodeName, AbstractParsedNodeResultAppender<T, ?> parser) {
		entryNodeParser.addNodeParser(nodeName, parser);
	}

	public void removeNodeParser(String nodeName) {
		entryNodeParser.removeNodeParser(nodeName);
	}

	@SuppressWarnings("unchecked")
	public void getSelf(EntryRequestCallback<T> callback) {
		final String url = getSelfUrl();

		if (url == null || url.trim().isEmpty()) {
			throw new NullPointerException("Self link must not be null.");
		}

		callback.setEntry((T) this);

		restService.read(url, callback);
	}

	public void setRestService(RestService restService) {
		this.restService = restService;
	}

	public Text getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		setSummary(new Text(summary));
	}

	public void setSummary(Text summary) {
		this.summary = summary;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

}

package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.Date;

import org.openinsula.arena.gwt.client.xml.CompositeNodeFactory;
import org.openinsula.arena.gwt.client.xml.NodeFactory;
import org.openinsula.arena.gwt.client.xml.ValueNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.parse.NodeParser;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public abstract class BaseEntry<T extends BaseEntry<T>> extends AtomResource implements NodeParser<T> {

	private Text summary;

	private Content content;

	private Date published;

	private final CompositeNodeParser<T> entryNodeParser = new AtomResourceNodeParser<T>((T) this);

	private final EntryNodeFactory<T> entryNodeFactory = new EntryNodeFactory<T>((T) this);

	public BaseEntry() {
		this(null, null);
	}

	public BaseEntry(String title, String id) {
		super(id, title);

		addNodeParser("summary", new TextNodeParser() {
			public void onNodeParsed(Text text) {
				setSummary(text);
			}
		});
		addNodeParser("published", new ValueNodeParser() {
			public void onNodeParsed(String value) {
				setPublished(null); // FIXME
			}
		});
	}

	public T parse(Node node) {
		return entryNodeParser.parse(node);
	}

	public CompositeNodeParser<T> addNodeParser(String nodeName, NodeParser<?> parser) {
		entryNodeParser.addNodeParser(nodeName, parser);
		return entryNodeParser;
	}

	public void removeNodeParser(String nodeName) {
		entryNodeParser.removeNodeParser(nodeName);
	}

	public NodeParser<?> getNodeParser(String nodeName) {
		return entryNodeParser.getNodeParser(nodeName);
	}

	public CompositeNodeFactory addNodeFactory(NodeFactory nodeFactory) {
		entryNodeFactory.addNodeFactory(nodeFactory);
		return entryNodeFactory;
	}

	public void removeNodeFactory(NodeFactory nodeFactory) {
		entryNodeFactory.removeNodeFactory(nodeFactory);
	}

	public String toXml() {
		final Document document = XMLParser.createDocument();
		final Node entryNode = createNode(document);

		if (entryNode == null) {
			throw new NullPointerException("");
		}

		document.appendChild(entryNode);

		return document.toString();
	}

	public Node createNode(Document document) {
		entryNodeFactory.setDocument(document);

		return entryNodeFactory.createNode();
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

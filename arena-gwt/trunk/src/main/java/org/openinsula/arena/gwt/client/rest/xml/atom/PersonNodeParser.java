package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.rest.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class PersonNodeParser extends CompositeNodeParser {

	private final Person person = new Person();

	public PersonNodeParser() {
		addParser("name", new NodeParser() {
			public void parse(Node node) {
				person.setName(XmlParserUtils.getText(node));
			}
		});
		addParser("uri", new NodeParser() {
			public void parse(Node node) {
				person.setUri(XmlParserUtils.getText(node));
			}
		});
		addParser("email", new NodeParser() {
			public void parse(Node node) {
				person.setEmail(XmlParserUtils.getText(node));
			}
		});
	}

	public final Person getPerson() {
		return person;
	}

}
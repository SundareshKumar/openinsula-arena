package org.openinsula.arena.gwt.client.rest.xml.atom;

import org.openinsula.arena.gwt.client.xml.CompositeNodeParser;
import org.openinsula.arena.gwt.client.xml.NodeParser;
import org.openinsula.arena.gwt.client.xml.XmlParserUtils;

import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class PersonNodeParser extends CompositeNodeParser<Person> {

	public PersonNodeParser() {
		addParser("name", new NodeParser() {
			@Override
			protected void parseWithNoReturn(Node node) {
				person.setName(XmlParserUtils.getText(node));
			}
		});
		addParser("uri", new NodeParser() {
			@Override
			protected void parseWithNoReturn(Node node) {
				person.setUri(XmlParserUtils.getText(node));
			}
		});
		addParser("email", new NodeParser() {
			@Override
			protected void parseWithNoReturn(Node node) {
				person.setEmail(XmlParserUtils.getText(node));
			}
		});
	}

	@Override
	public Person parse(Node node) {
		final Person person = new Person();

		super.parse(node);

		return person;
	}

}
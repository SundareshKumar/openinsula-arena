package org.openinsula.arena.gwt.client.rest.xml.atom;

import java.util.List;

import org.openinsula.arena.gwt.client.rest.xml.AbstractMultipleNodeFactory;

import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;

/**
 * @author Lucas K Mogari
 */
public class PeopleNodesFactory extends AbstractMultipleNodeFactory<Person> {

	private final String nodeName;

	public PeopleNodesFactory(String nodeName, List<Person> people) {
		super(people);

		this.nodeName = nodeName;
	}

	@Override
	protected Node createNode(Person person) {
		final String name = person.getName();

		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("");
		}

		final Element personElement = createElement(nodeName);
		final String email = person.getEmail();
		final String uri = person.getUri();

		personElement.appendChild(createTextElement("name", name));

		if (email != null) {
			personElement.appendChild(createTextElement("email", email));
		}
		if (uri != null) {
			personElement.appendChild(createTextElement("uri", uri));
		}
		return personElement;
	}

}

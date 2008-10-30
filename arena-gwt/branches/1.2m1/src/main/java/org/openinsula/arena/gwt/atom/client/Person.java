package org.openinsula.arena.gwt.atom.client;

/**
 * @author Lucas K Mogari
 */
public class Person {

	private String name;

	private String uri;

	private String email;

	public Person() {
	}

	public Person(String name) {
		this(name, null, null);
	}

	public Person(String name, String email, String uri) {
		this.name = name;
		this.email = email;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

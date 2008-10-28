package org.openinsula.arena.gwt.client.rest.xml.atom;

/**
 * @author Lucas K Mogari
 */
public class Category {

	private String term;

	private String scheme;

	private String label;

	public Category() {
	}

	public Category(String label, String scheme, String term) {
		this.label = label;
		this.scheme = scheme;
		this.term = term;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}

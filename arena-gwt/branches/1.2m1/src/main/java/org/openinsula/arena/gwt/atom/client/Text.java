package org.openinsula.arena.gwt.atom.client;

/**
 * @author Lucas K Mogari
 */
public class Text {

	public static final String TEXT = "text";

	public static final String HTML = "html";

	public static final String XHTML = "xhtml";

	private String type;

	private String value;

	public Text() {
	}

	public Text(String value) {
		this(value, null);
	}

	public Text(String value, String type) {
		this.value = value;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

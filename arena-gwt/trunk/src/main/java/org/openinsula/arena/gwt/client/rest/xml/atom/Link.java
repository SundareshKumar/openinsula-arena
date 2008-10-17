package org.openinsula.arena.gwt.client.rest.xml.atom;

/**
 * @author Lucas K Mogari
 */
public class Link {

	public static final class Rel {

		public static final String SELF = "self";

		public static final String PREVIOUS = "previous";

		public static final String NEXT = "next";

		public static final String ALTERNATE = "alternate";

		public static final String RELATED = "related";

		public static final String ENTRY_EDIT = "edit";

		public static final String MEDIA_EDIT = "edit-media";

		public static final String VIA = "via";

	}

	public static final class Type {

		public static final String ATOM = "application/atom+xml;charset=UTF-8";

		public static final String HTML = "text/html;charset=UTF-8";

	}

	private String href;

	private String title;

	private String type;

	private String hreflang;

	private byte length;

	private String rel;

	public Link() {
	}

	public Link(String href) {
		this(null, href, null);
	}

	public Link(String title, String href) {
		this(title, href, null);
	}

	public Link(String title, String href, String type) {
		this.title = title;
		this.href = href;
		this.type = type;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHreflang() {
		return hreflang;
	}

	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public boolean matches(String rel2, String type2) {
		return rel2 != null && rel2.equals(rel) && type2 != null && type2.equals(type);
	}

}

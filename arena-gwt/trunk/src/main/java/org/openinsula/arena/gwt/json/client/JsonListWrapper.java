package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("json-list")
public class JsonListWrapper<T extends Serializable> extends JsonVO {

	private static final long serialVersionUID = 1L;

	@XStreamImplicit
	private List<T> wrappedList;

	@XStreamOmitField
	private T template;

	public JsonListWrapper() {
	}

	public JsonListWrapper(final T template) {
		setTemplate(template);
	}

	@Override
	protected String getJsonPrefix() {
		return "json-list";
	}

	public void setTemplate(final T template) {
		if (template == null) {
			throw new IllegalArgumentException("Template is required!");
		}

		this.template = template;
	}

	public T getTemplate() {
		return this.template;
	}

	public final List<T> getList() {
		if (this.wrappedList == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(this.wrappedList);
	}

}

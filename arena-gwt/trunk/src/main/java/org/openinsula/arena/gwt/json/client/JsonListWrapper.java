package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("json-list")
public class JsonListWrapper<T extends Serializable> extends JsonVO {

	@XStreamImplicit
	private List<T> wrappedList;

	@XStreamOmitField
	private T template;

	public JsonListWrapper() {
		super("json-list");
	}

	public JsonListWrapper(final T template) {
		super("json-list");
		setTemplate(template);
	}

	public void setTemplate(final T template) {
		if (template == null) {
			throw new IllegalArgumentException("Template is required!");
		}

		this.template = template;
	}

	public T getTemplate() {
		return template;
	}

	public final List<T> getList() {
		if (wrappedList == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(wrappedList);
	}

}

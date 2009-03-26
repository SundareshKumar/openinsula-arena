package org.openinsula.arena.gwt.json.client;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("json-list")
public class JsonListWrapper<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@XStreamImplicit
	private List<T> wrappedList;

	public final List<T> getList() {
		if (this.wrappedList == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(this.wrappedList);
	}

}

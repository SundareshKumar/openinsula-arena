package org.openinsula.arena.gwt.client.persistence;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.sf.hibernate4gwt.pojo.java5.LazyPojo;

public abstract class GwtEntity<T extends Serializable> extends LazyPojo implements Serializable {

	@Id
	@GeneratedValue
	private T id;

	public T getId() {
		return id;
	}

	public void setId(final T id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GwtEntity)) {
			return false;
		}

		GwtEntity<?> that = (GwtEntity<?>) obj;
		return getId() == null ? that.getId() == null : getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return "id: " + id;
	}

}

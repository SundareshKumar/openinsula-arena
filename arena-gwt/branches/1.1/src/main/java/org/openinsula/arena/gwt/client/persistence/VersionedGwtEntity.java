package org.openinsula.arena.gwt.client.persistence;

import java.io.Serializable;

import javax.persistence.Version;

public abstract class VersionedGwtEntity<T extends Serializable> extends GwtEntity<T> {

	@Version
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

}

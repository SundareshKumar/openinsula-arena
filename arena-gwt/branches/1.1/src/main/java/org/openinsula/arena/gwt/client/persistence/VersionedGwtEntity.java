package org.openinsula.arena.gwt.client.persistence;

import javax.persistence.Version;

public abstract class VersionedGwtEntity<T extends Number> extends GwtEntity<T> {

	@Version
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

}

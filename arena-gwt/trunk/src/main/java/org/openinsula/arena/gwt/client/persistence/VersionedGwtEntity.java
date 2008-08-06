package org.openinsula.arena.gwt.client.persistence;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
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

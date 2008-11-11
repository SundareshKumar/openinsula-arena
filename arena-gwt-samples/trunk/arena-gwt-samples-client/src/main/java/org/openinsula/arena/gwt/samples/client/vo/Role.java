package org.openinsula.arena.gwt.samples.client.vo;

import org.openinsula.arena.gwt.serialization.client.xml.XMLSerializable;

public class Role implements XMLSerializable {

	private int id;

	private Boolean admin;

	private User[] userArray;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(final Boolean admin) {
		this.admin = admin;
	}

	public User[] getUserArray() {
		return userArray;
	}

	public void setUserArray(final User[] userArray) {
		this.userArray = userArray;
	}


}

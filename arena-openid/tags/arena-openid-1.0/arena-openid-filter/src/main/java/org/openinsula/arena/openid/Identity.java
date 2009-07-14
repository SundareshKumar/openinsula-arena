package org.openinsula.arena.openid;

import java.io.Serializable;

public class Identity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String email;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

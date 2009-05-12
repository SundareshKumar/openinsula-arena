package org.openinsula.arena.openid;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IdentityUtils {

	public static final String IDENTITY_ATTRIBUTE = "org.openinsula.arena.openid.IDENTITY_ATTRIBUTE";

	public static Identity obtainIdentity(Map<?,?> sessionMap) {
		return (Identity) sessionMap.get(IDENTITY_ATTRIBUTE);
	}

	public static Identity obtainIdentity(HttpSession session) {
		return (Identity) session.getAttribute(IDENTITY_ATTRIBUTE);
	}

	public static Identity obtainIdentity(HttpServletRequest request) {
		return obtainIdentity(request.getSession());
	}
	
	protected static void setIdentity(HttpSession session, Identity identity) {
		session.setAttribute(IDENTITY_ATTRIBUTE, identity);
	}

	protected static void setIdentity(HttpServletRequest request, Identity identity) {
		setIdentity(request.getSession(), identity);
	}

}

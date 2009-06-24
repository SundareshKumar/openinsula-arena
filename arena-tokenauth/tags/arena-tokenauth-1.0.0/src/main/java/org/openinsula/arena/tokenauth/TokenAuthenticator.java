package org.openinsula.arena.tokenauth;

public interface TokenAuthenticator {

	public static final String TOKEN_AUTHENTICATOR_HEADER = "X-Token-Auth-Header";

	public String isAuthenticated(String token);
	
	public String authenticate(Token token);

}
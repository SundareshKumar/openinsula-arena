package org.openinsula.arena.tokenauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class TokenTest {

	private long timeInMillis = new GregorianCalendar(2008, Calendar.DECEMBER, 4, 1, 0, 0).getTimeInMillis();

	@Test
	public void testGetBytes() {
		Token token = new Token("jedi", timeInMillis);
		byte[] bytes = token.getBytes();

		assertEquals(24, bytes.length);
		assertEquals("jedi", token.getCredential());
		assertEquals(timeInMillis, token.getTimestamp());

		Token parsedToken = Token.valueOf(bytes);

		assertEquals("jedi", parsedToken.getCredential());
		assertEquals(timeInMillis, parsedToken.getTimestamp());
	}

	@Test
	public void testToString() {
		Token token = new Token("Obi Wan", timeInMillis);
		String tokenString = token.toString();

		assertEquals(30, tokenString.length());
		assertEquals("Obi Wan", token.getCredential());
		assertEquals(timeInMillis, token.getTimestamp());

		Token parsedToken = Token.valueOf(tokenString);

		assertEquals("Obi Wan", parsedToken.getCredential());
		assertEquals(timeInMillis, parsedToken.getTimestamp());
	}

	@Test
	public void testRenew() {
		long timeInMillis = new GregorianCalendar(2008, Calendar.NOVEMBER, 19, 21, 0, 0).getTimeInMillis();
		Token token = new Token("jedi", timeInMillis);
		token.renew(3600);

		assertTrue(token.getTimestamp() - 3600 * 1000 == timeInMillis);
	}
}

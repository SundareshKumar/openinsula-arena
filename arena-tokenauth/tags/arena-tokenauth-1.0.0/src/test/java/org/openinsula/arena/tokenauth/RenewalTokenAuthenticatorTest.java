package org.openinsula.arena.tokenauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openinsula.arena.tokenauth.crypt.AESCryptProvider;
import org.openinsula.arena.tokenauth.crypt.CryptProvider;
import org.openinsula.arena.tokenauth.crypt.key.KeyProvider;

public class RenewalTokenAuthenticatorTest {

	private static byte[] key;

	private long epoch = new GregorianCalendar(2008, Calendar.NOVEMBER, 17, 0, 0, 0).getTimeInMillis();

	@BeforeClass
	public static void init() throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey skey = kgen.generateKey();
		key = skey.getEncoded();

	}
	
	@Test
	public void testAuthenticate() {
		RenewalTokenAuthenticator tokenAuthenticator = buidAESRenewalTokenAuthenticator();
		
		Token token = new Token("Luke Skywalker");
		String header = tokenAuthenticator.authenticate(token);
		
		String newHeader = tokenAuthenticator.isAuthenticated(header);
		
		assertNotNull(newHeader);
	}

	@Test
	public void testDoIsAuthenticatedAndRenewal() throws Exception {
		RenewalTokenAuthenticator tokenAuthenticator = buildMockRenewalTokenAuthenticator();
		tokenAuthenticator.setRenewalPeriod(45 * 60);

		byte[] data = new Token("jedi", epoch).getBytes();

		byte[] newData = tokenAuthenticator.doIsAuthenticatedAndRenewal(data);

		assertNotNull(newData);
		Token newToken = Token.valueOf(newData);

		long renewalTimestamp = new GregorianCalendar(2008, Calendar.NOVEMBER, 17, 0, 45, 0).getTimeInMillis();
		assertEquals(renewalTimestamp, newToken.getTimestamp());
	}

	@Test
	public void testIsAuthenticated() throws Exception {
		RenewalTokenAuthenticator tokenAuthenticator = buildMockRenewalTokenAuthenticator();
		tokenAuthenticator.setRenewalPeriod(60 * 60);

		byte[] data = new Token("Yoda", epoch).getBytes();

		String tokenHeader = new String(Hex.encodeHex(data));
		String newTokenHeader = tokenAuthenticator.isAuthenticated(tokenHeader);

		byte[] decodeHex = Hex.decodeHex(newTokenHeader.toCharArray());

		Token newToken = Token.valueOf(decodeHex);

		assertEquals("Yoda", newToken.getCredential());

		long renewalTimestamp = new GregorianCalendar(2008, Calendar.NOVEMBER, 17, 1, 0, 0).getTimeInMillis();
		assertEquals(renewalTimestamp, newToken.getTimestamp());
	}

	@Test
	public void testIsAuthenticatedWithAES() throws Exception {
		RenewalTokenAuthenticator tokenAuthenticator = buidAESRenewalTokenAuthenticator();
		tokenAuthenticator.setRenewalPeriod(60 * 60);

		byte[] data = new Token("Yoda", epoch).getBytes();

		CryptProvider cryptProvider = new AESCryptProvider();
		byte[] cryptedContent = cryptProvider.encryptContent(data, key);

		String tokenHeader = new String(Hex.encodeHex(cryptedContent));
		String newTokenHeader = tokenAuthenticator.isAuthenticated(tokenHeader);

		byte[] decodeHex = Hex.decodeHex(newTokenHeader.toCharArray());

		byte[] decryptedContent = cryptProvider.decryptContent(decodeHex, key);

		Token newToken = Token.valueOf(decryptedContent);

		assertEquals("Yoda", newToken.getCredential());

		long renewalTimestamp = new GregorianCalendar(2008, Calendar.NOVEMBER, 17, 1, 0, 0).getTimeInMillis();
		assertEquals(renewalTimestamp, newToken.getTimestamp());
	}

	private RenewalTokenAuthenticator buildMockRenewalTokenAuthenticator() {
		RenewalTokenAuthenticator tokenAuthenticator = new RenewalTokenAuthenticator();
		tokenAuthenticator.setCryptProvider(new CryptProvider() {
			@Override
			public byte[] decryptContent(byte[] crypted, byte[] key) {
				return crypted;
			}

			@Override
			public byte[] encryptContent(byte[] content, byte[] key) {
				return content;
			}
		});
		tokenAuthenticator.setKeyProvider(new KeyProvider() {
			@Override
			public byte[] getCryptoKey() {
				return null;
			}
		});
		return tokenAuthenticator;
	}

	@Test
	public void testDoIsAuthenticatedAndRenewalWithAES() throws Exception {
		RenewalTokenAuthenticator authenticator = buidAESRenewalTokenAuthenticator();

		Token token = new Token("Obi Wan", epoch);

		CryptProvider cryptProvider = new AESCryptProvider();
		byte[] cryptedContent = cryptProvider.encryptContent(token.getBytes(), key);

		byte[] newData = authenticator.doIsAuthenticatedAndRenewal(cryptedContent);

		byte[] decryptedContent = cryptProvider.decryptContent(newData, key);

		Token newToken = Token.valueOf(decryptedContent);

		assertEquals("Obi Wan", newToken.getCredential());

		long renewalTimestamp = new GregorianCalendar(2008, Calendar.NOVEMBER, 17, 0, 30, 0).getTimeInMillis();
		assertEquals(renewalTimestamp, newToken.getTimestamp());
	}

	private RenewalTokenAuthenticator buidAESRenewalTokenAuthenticator() {
		RenewalTokenAuthenticator authenticator = new RenewalTokenAuthenticator();
		authenticator.setCryptProvider(new AESCryptProvider());
		authenticator.setKeyProvider(new KeyProvider() {
			@Override
			public byte[] getCryptoKey() {
				return key;
			}
		});
		return authenticator;
	}

}

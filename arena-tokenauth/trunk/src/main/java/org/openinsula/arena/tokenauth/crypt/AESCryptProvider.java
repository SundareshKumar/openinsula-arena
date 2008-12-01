package org.openinsula.arena.tokenauth.crypt;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptProvider implements CryptProvider {

	private static final String CYPHER = "AES";

	@Override
	public byte[] decryptContent(byte[] crypted, byte[] key) {
		SecretKeySpec skeySpec = new SecretKeySpec(key, CYPHER);

		try {
			Cipher cipher = Cipher.getInstance(CYPHER);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			return cipher.doFinal(crypted);
		}
		catch (GeneralSecurityException ex) {
			throw new IllegalArgumentException("Unable to get AES cipher.", ex);
		}
	}

	@Override
	public byte[] encryptContent(byte[] content, byte[] key) {
		SecretKeySpec skeySpec = new SecretKeySpec(key, CYPHER);

		try {
			Cipher cipher = Cipher.getInstance(CYPHER);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			return cipher.doFinal(content);
		}
		catch (GeneralSecurityException ex) {
			throw new IllegalArgumentException("Unable to get AES cipher.", ex);
		}
	}

}

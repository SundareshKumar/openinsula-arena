package org.openinsula.arena.tokenauth.crypt.key;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

public class AESKeyGenerator implements KeyGenerator {

	@Override
	public byte[] generateKey() {
		try {
			javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
			kgen.init(256);
			SecretKey skey = kgen.generateKey();

			return skey.getEncoded();
		}
		catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("AES Algorithm not found.");
		}
	}
}

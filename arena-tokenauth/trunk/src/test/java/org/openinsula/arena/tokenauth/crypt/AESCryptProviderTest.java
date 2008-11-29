package org.openinsula.arena.tokenauth.crypt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.junit.Test;

public class AESCryptProviderTest {

	@Test
	public void testEncryptDecrypt() throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(256);
		SecretKey skey = kgen.generateKey();
		byte[] key = skey.getEncoded();

		AESCryptProvider provider = new AESCryptProvider();

		byte[] content = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 11, 25, 4, 4, 12, 78, 80, 20 };

		byte[] crypted = provider.encryptContent(content, key);

		assertFalse(crypted.length == content.length);

		byte[] result = provider.decryptContent(crypted, key);

		assertEquals(content.length, result.length);
		assertArrayEquals(content, result);
	}

}

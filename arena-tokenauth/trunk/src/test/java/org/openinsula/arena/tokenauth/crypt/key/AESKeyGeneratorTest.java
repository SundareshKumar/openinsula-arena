package org.openinsula.arena.tokenauth.crypt.key;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class AESKeyGeneratorTest {

	@Test
	public void testGenerateKey() throws Exception {
		AESKeyGenerator generator = new AESKeyGenerator();
		byte[] key = generator.generateKey();

		assertNotNull(key);
		assertTrue(key.length > 0);

		Key keySpec = new SecretKeySpec(key, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);

		byte[] content = new byte[] { 0, 2, 3, 4, 2, 3, 4, 5, 5, 6, 4, 4, 4 };

		byte[] crypted = cipher.doFinal(content);

		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] result = cipher.doFinal(crypted);

		assertArrayEquals(content, result);
	}

}

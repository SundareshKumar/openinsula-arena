package org.openinsula.arena.tokenauth.crypt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.openinsula.arena.tokenauth.crypt.key.KeyProvider;

public class CryptTemplateTestCase {

	@Test
	public void testEncryptDecrypt() {
		byte[] data = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		CryptTemplate template = new CryptTemplate();
		template.setCryptProvider(new CryptProvider() {
			@Override
			public byte[] decryptContent(byte[] crypted, byte[] key) {
				return crypted;
			}

			@Override
			public byte[] encryptContent(byte[] content, byte[] key) {
				return content;
			}
		});
		template.setKeyProvider(new KeyProvider() {
			@Override
			public byte[] getCryptoKey() {
				return null;
			}
		});

		byte[] result = template.execute(new CryptCallback() {
			@Override
			public byte[] doInCryptographedContent(byte[] content) {
				return content;
			}
		}, data);

		assertNotNull(result);
		assertArrayEquals(data, result);
	}

}

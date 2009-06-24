package org.openinsula.arena.tokenauth.crypt.key;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;

public class AESKeyGenerator {

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);

		SecretKey keySpec = keyGenerator.generateKey();

		System.out.println(Hex.encodeHex(keySpec.getEncoded()));
	}

}

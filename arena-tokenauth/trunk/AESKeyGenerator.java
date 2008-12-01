package org.openinsula.arena.tokenauth.crypt.key;

import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;

public class AESKeyGenerator {

	public static void main(String[] args) throws Exception {
		javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
		kgen.init(256);
		SecretKey skey = kgen.generateKey();
		byte[] encoded = skey.getEncoded();

		System.out.println(Hex.encodeHex(encoded));
	}

}

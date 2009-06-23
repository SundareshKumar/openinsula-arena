package org.openinsula.arena.tokenauth.crypt.key;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Required;

public class HexStaticKeyProvider implements KeyProvider {

	private String key;

	@Required
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public byte[] getCryptoKey() {
		try {
			return Hex.decodeHex(key.toCharArray());
		}
		catch (DecoderException ex) {
			throw new IllegalArgumentException("Illegal key.", ex);
		}
	}

}

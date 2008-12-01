package org.openinsula.arena.tokenauth;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.openinsula.arena.tokenauth.crypt.CryptCallback;
import org.openinsula.arena.tokenauth.crypt.CryptProvider;
import org.openinsula.arena.tokenauth.crypt.CryptTemplate;
import org.openinsula.arena.tokenauth.crypt.key.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class RenewalTokenAuthenticator implements TokenAuthenticator {

	private CryptProvider cryptProvider;

	private KeyProvider keyProvider;

	private int renewalPeriod = 1800;

	@Autowired
	public void setCryptProvider(CryptProvider cryptProvider) {
		this.cryptProvider = cryptProvider;
	}

	@Autowired
	public void setKeyProvider(KeyProvider keyProvider) {
		this.keyProvider = keyProvider;
	}

	public void setRenewalPeriod(int timeInSeconds) {
		this.renewalPeriod = timeInSeconds;
	}

	@Override
	public String isAuthenticated(String tokenHeader) {
		try {
			byte[] decodeHex = Hex.decodeHex(tokenHeader.toCharArray());

			byte[] toEncodeHex = doIsAuthenticatedAndRenewal(decodeHex);

			return new String(Hex.encodeHex(toEncodeHex));
		}
		catch (DecoderException ex) {
			throw new IllegalArgumentException("Unable to decode tokenHeader", ex);
		}
	}

	protected byte[] doIsAuthenticatedAndRenewal(byte[] decodeHex) {
		CryptTemplate template = new CryptTemplate();
		template.setCryptProvider(cryptProvider);
		template.setKeyProvider(keyProvider);

		return template.execute(new CryptCallback() {
			@Override
			public byte[] doInCryptographedContent(byte[] content) {
				Token token = Token.valueOf(content);
				token.renew(renewalPeriod);

				return token.getBytes();
			}
		}, decodeHex);
	}
	
	@Override
	public String authenticate(Token token) {
		byte[] key = keyProvider.getCryptoKey();
		byte[] encryptContent = cryptProvider.encryptContent(token.getBytes(), key);

		return new String(Hex.encodeHex(encryptContent));
	}

}

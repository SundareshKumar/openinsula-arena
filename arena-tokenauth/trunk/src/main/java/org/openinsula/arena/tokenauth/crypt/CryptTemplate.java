package org.openinsula.arena.tokenauth.crypt;

import org.openinsula.arena.tokenauth.crypt.key.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CryptTemplate {

	private CryptProvider cryptProvider;

	private KeyProvider keyProvider;

	@Autowired
	public void setCryptProvider(CryptProvider cryptProvider) {
		Assert.notNull(cryptProvider, "CryptProvider must not be null.");
		this.cryptProvider = cryptProvider;
	}

	@Autowired
	public void setKeyProvider(KeyProvider keyProvider) {
		Assert.notNull(keyProvider, "KeyProvider must not be null.");
		this.keyProvider = keyProvider;
	}

	public byte[] execute(CryptCallback action, byte[] content) {
		byte[] key = keyProvider.getCryptoKey();
		byte[] decryptContent = cryptProvider.decryptContent(content, key);
		byte[] newContent = action.doInCryptographedContent(decryptContent);
		return cryptProvider.encryptContent(newContent, key);
	}

}
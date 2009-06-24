package org.openinsula.arena.tokenauth.crypt;

public interface CryptCallback {

	public byte[] doInCryptographedContent(byte[] content);

}
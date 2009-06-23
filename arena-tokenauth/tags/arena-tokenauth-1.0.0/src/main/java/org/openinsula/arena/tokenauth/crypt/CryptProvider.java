package org.openinsula.arena.tokenauth.crypt;

public interface CryptProvider {

	public byte[] encryptContent(byte[] content, byte[] key);

	public byte[] decryptContent(byte[] crypted, byte[] key);

}

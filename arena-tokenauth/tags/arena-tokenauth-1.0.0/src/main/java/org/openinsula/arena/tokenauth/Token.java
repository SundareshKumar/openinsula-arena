package org.openinsula.arena.tokenauth;

import java.nio.ByteBuffer;
import java.util.Calendar;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Token {

	private long timestamp;

	private String credential;

	public Token(String credential, long timestamp) {
		this.credential = credential;
		this.timestamp = timestamp;
	}

	public Token(String credential) {
		this(credential, System.currentTimeMillis());
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getCredential() {
		return credential;
	}

	public void renew(int timeInSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		calendar.add(Calendar.SECOND, timeInSeconds);

		this.timestamp = calendar.getTimeInMillis();
	}

	public static Token valueOf(byte[] data) {
		return valueOf(new String(data));
	}

	public static Token valueOf(String s) {
		try {
			byte[] data = Hex.decodeHex(s.toCharArray());
			ByteBuffer bb = ByteBuffer.wrap(data);

			long timestamp = bb.getLong();
			byte[] credentialBytes = new byte[data.length - 8];
			bb.get(credentialBytes);

			String credential = new String(credentialBytes);

			Token token = new Token(credential);
			token.timestamp = timestamp;

			return token;
		}
		catch (DecoderException ex) {
			throw new IllegalArgumentException("Invalid data for Token decoding.");
		}
	}

	public byte[] getBytes() {
		return toString().getBytes();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		ByteBuffer bb = ByteBuffer.allocate(8 + credential.length());
		bb.putLong(timestamp);
		bb.put(credential.getBytes());

		sb.append(Hex.encodeHex(bb.array()));

		return sb.toString();
	}

}

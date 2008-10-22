/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 *
 *  This file is part of Arena-Lang.
 *
 *  Arena-Lang is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Lang is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openinsula.arena.lang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Edson Yanaga
 * @author Eduardo Rebola
 * @deprecated This class was moved to {@link org.openinsula.arena.lang.security.HashUtils}
 */
@Deprecated
public abstract class HashUtils {

	private static MessageDigest md5;

	private static MessageDigest sha1;

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
			sha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	private static String toHexString(final byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			String s = Integer.toHexString(0xFF & digest[i]);
			if (s.length() == 1) {
				sb.append('0');
			}
			sb.append(s);
		}
		return sb.toString();
	}

	public static String createMD5(final byte[] bytes) {
		md5.reset();
		md5.update(bytes);
		byte[] digest = md5.digest();

		return toHexString(digest);
	}

	public static String createSHA1(final byte[] bytes) {
		sha1.reset();
		sha1.update(bytes);
		byte[] digest = sha1.digest();

		return toHexString(digest);
	}

	public static String createMD5(final String input) {
		return input == null ? null : createMD5(input.getBytes());
	}

	public static String gerarSHA1(final String input) {
		return input == null ? null : createSHA1(input.getBytes());
	}

}

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
package org.openinsula.arena.lang.security;

import java.security.SecureRandom;
import java.text.DecimalFormat;

/**
 * @author Edson Yanaga
 * @author Eduardo Rebola
 * 
 */
public abstract class RandomGenerator {

	private static final SecureRandom random = new SecureRandom();

	public static String generateNumberAsString(final int digits) {
		StringBuilder sb = new StringBuilder();
		long number = 1L;
		for (int i = 0; i < digits; i++) {
			number *= 10;
			sb.append('0');
		}
		double d = random.nextDouble();
		long i = (long) (d * number);
		DecimalFormat df = new DecimalFormat(sb.toString());
		return df.format(i);
	}

	public static long generateNumberAsLong(final int digits) {
		long number = 1L;
		for (int i = 0; i < digits; i++) {
			number *= 10;
		}
		double d = random.nextDouble();
		long i = (long) (d * number);
		return i;
	}

}

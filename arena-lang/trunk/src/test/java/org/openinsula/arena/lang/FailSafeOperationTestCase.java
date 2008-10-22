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
package org.openinsula.arena.lang;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class FailSafeOperationTestCase {

	@Test
	public void testDoTry() {

		BigDecimal result = new FailSafeOperation<BigDecimal>() {

			@Override
			protected BigDecimal operation() throws Throwable {
				throw new NullPointerException();
			}

		}.execute();

		assertNull(result);
	}

	@Test
	public void testCustomCatch() {

		BigDecimal result = new FailSafeOperation<BigDecimal>() {

			@Override
			protected BigDecimal operation() throws Throwable {
				throw new NullPointerException();
			}

			@Override
			protected BigDecimal handleException(Throwable throwable) {
				return BigDecimal.ZERO;
			}

		}.execute();

		assertEquals(BigDecimal.ZERO, result);
	}

	@Test(expected = NullPointerException.class)
	public void testDoCatchWithException() {
		new FailSafeOperation<BigDecimal>(true) {

			@Override
			protected BigDecimal operation() throws Throwable {
				throw new NullPointerException();
			}

		}.execute();
	}

}

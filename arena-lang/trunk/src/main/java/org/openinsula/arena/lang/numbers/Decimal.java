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
package org.openinsula.arena.lang.numbers;

/**
 * A <b>Mutable</b> Decimal implementation with chained methods. Useful for calculations.
 * @author Eduardo Rebola
 *
 */
public class Decimal extends AbstractDecimal<Decimal> {
	private static final long serialVersionUID = 1L;
	
	public Decimal() {
		this(false);
	}
	
	public Decimal(final boolean ignoreNumberFormatException) {
		super(ignoreNumberFormatException);
	}

	public Decimal(final String amount) {
		this(amount, false);
	}
	
	public Decimal(final String amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Number amount) {
		this(amount, false);
	}
	
	public Decimal(final Number amount, final boolean ignoreNumberFormatException) {
		super(amount, ignoreNumberFormatException);
	}

	public Decimal(final Decimal decimal) {
		this(decimal, false);
	}
	
	public Decimal(final Decimal decimal, final boolean ignoreNumberFormatException) {
		super(decimal, ignoreNumberFormatException);
	}

}

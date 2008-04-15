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
package org.openinsula.arena.lang.math.distribution;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntryTestCase {

	@Test
	public void testListEntry() {
		Entry<Integer> entry1 = new ListEntry<Integer>(1, 2);
		Entry<Integer> entry2 = new ListEntry<Integer>(1, 2);
		Entry<Integer> entry3 = new ListEntry<Integer>(2, 1);
		Entry<Integer> entry4 = new ListEntry<Integer>(0, 3);
		Entry<Integer> entry5 = new SetEntry<Integer>(1, 2, 2);
		
		assertEquals(entry1, entry2);
		assertFalse(entry1.equals(entry3));
		assertFalse(entry1.equals(entry4));
		assertFalse(entry1.equals(entry5));
	}
	
	@Test
	public void testSetEntry() {
		Entry<Integer> entry1 = new SetEntry<Integer>(1, 2);
		Entry<Integer> entry2 = new SetEntry<Integer>(1, 2);
		Entry<Integer> entry3 = new SetEntry<Integer>(2, 1);
		Entry<Integer> entry4 = new SetEntry<Integer>(0, 3);
		Entry<Integer> entry5 = new SetEntry<Integer>(1, 2, 2);
		
		assertEquals(entry1, entry2);
		assertEquals(entry1, entry3);
		assertEquals(entry1, entry5);
		assertFalse(entry1.equals(entry4));
	}

}

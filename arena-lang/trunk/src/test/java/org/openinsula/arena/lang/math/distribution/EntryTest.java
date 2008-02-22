package org.openinsula.arena.lang.math.distribution;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openinsula.arena.lang.math.distribution.Entry;
import org.openinsula.arena.lang.math.distribution.ListEntry;
import org.openinsula.arena.lang.math.distribution.SetEntry;

public class EntryTest {

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

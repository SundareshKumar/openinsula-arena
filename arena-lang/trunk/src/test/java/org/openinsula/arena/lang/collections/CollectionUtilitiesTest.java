package org.openinsula.arena.lang.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilitiesTest {

	@Test
	public void testTransformCollection() {
		Collection<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);

		Collection<String> expected = new ArrayList<String>();
		expected.add("1");
		expected.add("2");
		expected.add("3");

		Collection<String> actual = CollectionUtilities.transform(input, new ToStringTransformer<Integer>());
		
		assertEquals(expected, actual);
	}

	@Test
	public void testTransformList() {
		List<Double> input = new ArrayList<Double>();
		input.add(10.99);
		input.add(20.01);
		input.add(30.00);
		
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(10);
		expected.add(20);
		expected.add(30);

		List<Integer> actual = CollectionUtilities.transform(input, new NumberToIntegerTransformer<Double>());
		
		assertEquals(expected, actual);
	}

	@Test
	public void testTransformSet() {
		Set<Double> input = new LinkedHashSet<Double>();
		input.add(10.99);
		input.add(20.01);
		input.add(30.0);
		
		Set<String> expected = new LinkedHashSet<String>();
		expected.add("10.99");
		expected.add("20.01");
		expected.add("30.0");
		
		Set<String> actual = CollectionUtilities.transform(input, new ToStringTransformer<Double>());
		
		assertEquals(expected, actual);
	}

}

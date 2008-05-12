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
package org.openinsula.arena.lang.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilsTestCase {

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

		Collection<String> actual = CollectionUtils.transform(input, new ToStringTransformer<Integer>());

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

		List<Integer> actual = CollectionUtils.transform(input, new NumberToIntegerTransformer<Double>());

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

		Set<String> actual = CollectionUtils.transform(input, new ToStringTransformer<Double>());

		assertEquals(expected, actual);
	}

}

package org.openinsula.arena.validator;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractIbgeValidator {

	protected static final Set<Integer> ufSet = new LinkedHashSet<Integer>();

	static {
		Integer[] ufNorte = new Integer[] { 11, 12, 13, 14, 15, 16, 17 };
		Integer[] ufNordeste = new Integer[] { 21, 22, 23, 24, 25, 26, 27, 28, 29 };
		Integer[] ufSudeste = new Integer[] { 31, 32, 33, 35 };
		Integer[] ufSul = new Integer[] { 41, 42, 43 };
		Integer[] ufCentroOeste = new Integer[] { 50, 51, 52, 53 };

		ufSet.addAll(Arrays.asList(ufNorte));
		ufSet.addAll(Arrays.asList(ufNordeste));
		ufSet.addAll(Arrays.asList(ufSudeste));
		ufSet.addAll(Arrays.asList(ufSul));
		ufSet.addAll(Arrays.asList(ufCentroOeste));
	}

}

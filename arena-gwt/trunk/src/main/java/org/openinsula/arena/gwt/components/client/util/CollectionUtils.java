package org.openinsula.arena.gwt.components.client.util;

import java.util.Collection;
import java.util.Map;

/**
 * Borrowed from Spring Framework
 * @author Eduardo Rebola
 */
public abstract class CollectionUtils {
	
	/**
	 * Return <code>true</code> if the supplied Collection is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param collection the Collection to check
	 * @return whether the given Collection is empty
	 */
	public static boolean isEmpty(final Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Return <code>true</code> if the supplied Map is <code>null</code>
	 * or empty. Otherwise, return <code>false</code>.
	 * @param map the Map to check
	 * @return whether the given Map is empty
	 */
	public static boolean isEmpty(final Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

}

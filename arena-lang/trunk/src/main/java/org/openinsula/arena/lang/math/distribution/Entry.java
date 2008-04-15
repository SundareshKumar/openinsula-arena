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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Entry<T> implements Iterable<T> {
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected final Collection<T> values;
	
	public Entry(final T... valuesArray) {
		List<T> inputList = Arrays.asList(valuesArray);
		this.values = prepareValues(inputList);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Entry: " + this + " for input " + inputList);
		}
		
	}
	
	protected abstract Collection<T> prepareValues(List<T> referenceValues); 
	
	@Override
	public int hashCode() {
		return values.hashCode();
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Entry)) {
			return false;
		}
		
		Entry<?> that = (Entry<?>) obj;
		
		return values.equals(that.values);
	}
	
	@Override
	public String toString() {
		return values.toString();
	}
	
	@Override
	public Iterator<T> iterator() {
		return values.iterator();
	}
	
}
package org.openinsula.arena.gwt.beans.client.converter;

public interface Converter<S,T> {

	T convert(S source);

}

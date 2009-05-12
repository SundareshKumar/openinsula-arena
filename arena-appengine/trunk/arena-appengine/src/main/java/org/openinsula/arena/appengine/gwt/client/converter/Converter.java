package org.openinsula.arena.appengine.gwt.client.converter;

public interface Converter<S, T> {

	T convertForward(S value);

	S convertReverse(T value);

}

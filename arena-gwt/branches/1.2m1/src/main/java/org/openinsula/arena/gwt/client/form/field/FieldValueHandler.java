package org.openinsula.arena.gwt.client.form.field;

/**
 * @author Lucas K Mogari
 */
public interface FieldValueHandler<T> {

	public void setValue(T widget, Object value);

	public <V> V getValue(T widget);

}

package org.openinsula.arena.appengine.gwt.client.table;

/**
 * @author Lucas K Mogari
 */
public interface TableModelListener {

	public void tableChanged(TableModelEvent e);

	public void tableDataUpdated();

}

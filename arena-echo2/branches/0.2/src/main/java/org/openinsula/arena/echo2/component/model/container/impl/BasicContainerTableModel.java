package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.openinsula.arena.echo2.component.model.container.ContainerTableModel;

/**
 * This implementation is a basic one that makes the only functionality of
 * holding all the data that must appear on the table.
 * @author realm
 *
 * @param <T>
 */
public abstract class BasicContainerTableModel<T> extends AbstractTableModel implements ContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	protected final Logger logger = Logger.getLogger(getClass());
	
	protected List<T> items = new ArrayList<T>();

	/**
	 * If this property is set to true the tableModel will accept
	 * repeated values, else if False the tableModel will consider 
	 * it's policy by the substituteDuplicate property. 
	 */
	private boolean permitDuplicates;
	
	/**
	 * If this property is set to True when a bean is inserted
	 * a duplicate found will be removed and the inserted will be set.
	 * Else if False the inserted bean will be discarded.
	 * 
	 * This property only matters if the property permitDuplicates is set to false.
	 */
	private boolean substituteDuplicate;
	
	public BasicContainerTableModel() {
		this(true, true);
	}
	
	public BasicContainerTableModel(boolean permitDuplicates, boolean substituteDuplicate) {
		this.permitDuplicates = permitDuplicates;
		this.substituteDuplicate = substituteDuplicate;
	}

	/**
	 * This method must be implemented to return an array of string with the column names. 
	 * @return A String array with the column names
	 */
	public abstract String[] getColumns();
	
	public int getColumnCount() {
		return getColumns().length;
	}

	public String getColumnName(int i) {
		return getColumns()[i];
	}

	public int getRowCount() {
		return items.size();
	}

	public Object getValueAt(int columnIndex, int rowIndex) {
		T t = items.get(rowIndex);
		return getColumnValue(columnIndex, t);
	}

	/**
	 * This method is called by the method getValueAt() that finds the bean of generic type T and
	 * sends it here by parameter so that the value from the columnIndex can be returned by
	 * the specification of the bean.
	 * @param columnIndex The number of the column
	 * @param t Generic type bean
	 * @return Object with the value to the column
	 */
	public abstract Object getColumnValue(int columnIndex, T t);

	
	/** Methods from the interface ContainerTableModel */
	public void addItem(T bean) {
		if (permitDuplicates) {
			items.add(bean);
		} else {
			if (items.contains(bean)) {
				if (substituteDuplicate) {
					items.remove(bean);
				}
			}
			items.add(bean);
		}
	}
	
	public void clear() {
		items.clear();
	}

	public boolean deleteItem(Serializable itemId) {
		try {
			int index = new Integer(itemId.toString()).intValue();
			return ( items.remove(index) != null );
		} catch (Exception e) {
			logger.error("An error has ocurred while trying to remove the bean with the id: "+itemId, e);
			return false;
		}
	}

	public boolean deleteItem(T bean) {
		try {
			return items.remove(bean);
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteItem(ActionEvent actionEvent) {
		T bean = getSelectedItem(actionEvent);
		return deleteItem(bean);
	}

	public T getSelectedItem(ActionEvent actionEvent) {
		try {
			String itemId = actionEvent.getActionCommand();
			return getItemById(itemId);
		} catch (Exception e) {
			return null;
		}
	}

	public T getItemById(Serializable itemId) {
		if (itemId == null) {
			return null;
		}
		
		try {
			int index = new Integer(itemId.toString()).intValue();
			return items.get(index);
		} catch (Exception e) {
			logger.warn("There was an error when trying to find an item by it's id: "+itemId.toString());
			return null;
		}
	}
	
	public void addItems(T... beans) {
		for (T bean : beans) {
			this.addItem(bean);
		}
	}

	public void addItems(Collection<T> beans) {
		for (T bean : beans) {
			this.addItem(bean);
		}
	}

	public Serializable findIdFromBean(T bean) {
		return getItems().indexOf(bean);
	}

	public void setItems(Collection<T> beans) {
		this.clear();
		this.addItems(beans);
	}
	
	public List<T> getItems() {
		return items;
	}

	public int size() {
		return items.size();
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}

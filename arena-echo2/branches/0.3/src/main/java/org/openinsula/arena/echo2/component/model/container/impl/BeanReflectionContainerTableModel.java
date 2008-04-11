package org.openinsula.arena.echo2.component.model.container.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * This table model adds the ease of creating columns to define what are they
 * name and what properties will they show in each column.
 * @author realm
 * 
 * @param <T>
 */
public class BeanReflectionContainerTableModel<T> extends BasicContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	private List<TableColumn> tableColumns = new ArrayList<TableColumn>();

	public BeanReflectionContainerTableModel() {
	}

	public BeanReflectionContainerTableModel(boolean permitDuplicates, boolean substituteDuplicate) {
		super(permitDuplicates, substituteDuplicate);
	}

	/**
	 * Add a new TableColumn
	 * @param name
	 * @param property
	 * @return True if succeeded
	 */
	public boolean addTableColumn(String name, String property) {
		try {
			return tableColumns.add(new TableColumn(name, property));
		}
		catch (Exception e) {
			logger.error("There was an erro adding a table column in the " + getClass());
			return false;
		}
	}

	/**
	 * Searches for a table column with the name from the parameter and removes
	 * it from the list.
	 * @param name
	 * @return True if succeeded
	 */
	public boolean removeTableColumn(String name) {
		try {
			TableColumn found = null;
			for (TableColumn tableColumn : tableColumns) {
				if (tableColumn.getName().equals(name)) {
					found = tableColumn;
					break;
				}
			}

			return tableColumns.remove(found);
		}
		catch (Exception e) {
			logger.error("There was an erro removing a table column in the " + getClass());
			return false;
		}
	}

	@Override
	public String[] getColumns() {
		String[] names = new String[tableColumns.size()];
		int counter = 0;
		for (TableColumn tableColumn : tableColumns) {
			names[counter++] = tableColumn.getName();
		}

		return names;
	}

	/**
	 * This method returns the property of the bean using reflection
	 */
	@Override
	public Object getColumnValue(int columnIndex, T t) {
		TableColumn tableColumn = tableColumns.get(columnIndex);

		try {
			return PropertyUtils.getProperty(t, tableColumn.getProperty());
		}
		catch (Exception e) {
			logger.warn("There was an error getting the property '" + tableColumn.getProperty()
					+ "' from a bean of type: " + t.getClass());
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Wrapper class to control properties from each column.
	 * @author realm
	 * 
	 */
	protected class TableColumn {
		/**
		 * The name of the column that appears on the column header.
		 */
		private String name;

		/**
		 * The property that will be get from the bean using reflection
		 */
		private String property;

		private TableColumn(String name, String property) {
			super();
			this.name = name;
			this.property = property;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

	}

}

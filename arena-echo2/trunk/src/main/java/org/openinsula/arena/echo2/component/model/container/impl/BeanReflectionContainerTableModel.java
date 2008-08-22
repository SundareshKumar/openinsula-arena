package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.button.AbstractButton;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.openinsula.arena.echo2.component.model.container.ComponentBuilder;
import org.springframework.util.StringUtils;

/**
 * This table model adds the ease of creating columns to define what are they
 * name and what properties will they show in each column.
 * @author realm
 * 
 * @param <T>
 */
public class BeanReflectionContainerTableModel<T> extends BasicContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	private List<TableColumn<T>> tableColumns = new ArrayList<TableColumn<T>>();

	private Map<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();

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
		return addTableColumn(name, property, -1);
	}

	/**
	 * Add a new TableColumn
	 * @param name The name of the column, and also it's header.
	 * @param property The property to be captured by reflection.
	 * @param width The column width.
	 * @return True if succeeded
	 */
	public boolean addTableColumn(String name, String property, int width) {
		try {
			TableColumn<T> tableColumn = new TableColumn<T>(name, property);

			boolean added = tableColumns.add(tableColumn);

			if (added) {
				int index = findTableColumnIndex(tableColumn);
				columnWidthMap.put(index, width);
			}
			return added;
		}
		catch (Exception e) {
			logger.error("There was an error adding a table column in the " + getClass());
			return false;
		}
	}

	/**
	 * Add a new TableColumn that receives a java.util.Date or a
	 * java.util.Calendar and format's it with the given dateFormat parameter.
	 * @param name The name of the column, and also it's header.
	 * @param property The property to be captured by reflection.
	 * @param width The column width.
	 * @param dateFormat The String formatter to the property.
	 * @return True if succeeded
	 */
	public boolean addDateTableColumn(String name, String property, String dateFormat, int width) {
		try {
			TableColumn<T> tableColumn = new TableColumn<T>(name, property, dateFormat);

			boolean added = tableColumns.add(tableColumn);

			if (added) {
				int index = findTableColumnIndex(tableColumn);
				columnWidthMap.put(index, width);
			}
			return added;
		}
		catch (Exception e) {
			logger.error("There was an error adding a date table column in the " + getClass());
			return false;
		}
	}

	/**
	 * Add a new TableColumn
	 * @param name The name of the column, and also it's header.
	 * @param property The property to be captured by reflection.
	 * @param width The column width.
	 * @return True if succeeded
	 */
	public boolean addTableColumn(String name, ComponentBuilder<?, T> componentBuilder, int width) {
		try {
			TableColumn<T> tableColumn = new TableColumn<T>(name, componentBuilder);

			boolean added = tableColumns.add(tableColumn);

			if (added) {
				int index = findTableColumnIndex(tableColumn);
				columnWidthMap.put(index, width);
			}
			return added;
		}
		catch (Exception e) {
			logger.error("There was an error adding a table column in the " + getClass());
			return false;
		}
	}

	/**
	 * Find a tableColumn by it's name
	 * @param name
	 * @return
	 */
	public TableColumn<T> findTableColumnByName(final String name) {
		TableColumn<T> found = null;
		for (TableColumn<T> tableColumn : tableColumns) {
			if (tableColumn.getName().equals(name)) {
				found = tableColumn;
				break;
			}
		}

		return found;
	}

	/**
	 * @param tableColumn
	 * @return Returns the index of the table column
	 */
	private int findTableColumnIndex(final TableColumn<T> tableColumn) {
		if (tableColumns.contains(tableColumn)) {
			for (int i = 0; i < tableColumns.size(); i++) {
				if (tableColumns.get(i).equals(tableColumn)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Searches for a table column with the name from the parameter and removes
	 * it from the list.
	 * @param name
	 * @return True if succeeded
	 */
	public boolean removeTableColumn(final String name) {
		TableColumn<T> findTableColumnByName = findTableColumnByName(name);
		return removeTableColumn(findTableColumnByName);
	}

	/**
	 * @param tableColumn
	 * @return Tries to remove
	 */
	public boolean removeTableColumn(final TableColumn<T> tableColumn) {
		try {
			boolean removed = tableColumns.remove(tableColumn);

			if (removed) {
				int findTableColumnIndex = findTableColumnIndex(tableColumn);
				columnWidthMap.remove(findTableColumnIndex);
			}

			return removed;
		}
		catch (Exception e) {
			logger.error("There was an error removing a table column in the " + getClass());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.openinsula.arena.echo2.component.model.container.impl.
	 * BasicContainerTableModel#getColumns()
	 */
	@Override
	public String[] getColumns() {
		String[] names = new String[tableColumns.size()];
		int counter = 0;

		for (TableColumn<T> tableColumn : tableColumns) {
			names[counter++] = tableColumn.getName();
		}

		return names;
	}

	/**
	 * This method returns the property of the bean using reflection
	 */
	@Override
	public Object getColumnValue(int columnIndex, T bean) {
		TableColumn<T> tableColumn = tableColumns.get(columnIndex);

		if (StringUtils.hasText(tableColumn.getProperty())) {

			Object value = null;

			try {
				value = PropertyUtils.getProperty(bean, tableColumn.getProperty());
			}
			catch (Exception e) {
				logger.warn("There was an error getting the property '" + tableColumn.getProperty()
						+ "' from a bean of type: " + bean.getClass());
				e.printStackTrace();
				return null;
			}

			if (StringUtils.hasText(tableColumn.getDateFormat())) {
				if (value instanceof Calendar) {
					value = ((Calendar) value).getTime();
				}

				if (value instanceof Date) {
					value = DateFormatUtils.format((Date) value, tableColumn.getDateFormat());
				}

			}

			if (value != null) {
				return value.toString();
			}
			else {
				return "";
			}
		}
		else if (tableColumn.getComponentBuilder() != null) {

			Component component = tableColumn.getComponentBuilder().buildComponent(columnIndex, bean);

			if (component instanceof AbstractButton) {
				((AbstractButton) component).setActionCommand(getActionCommandFromBean(bean));
			}

			// TODO implement the rest of the components that have actionCommand

			return component;
		}

		return null;
	}

	/**
	 * Returns the ActionCommand for the components
	 * @param bean
	 * @return
	 */
	protected String getActionCommandFromBean(T bean) {
		if (bean != null) {
			Serializable beanId = findIdFromBean(bean);
			if (beanId != null) {
				return beanId.toString();
			}
		}
		return null;
	}

	/**
	 * Adds a column width
	 * @param columnIndex
	 * @param width
	 */
	public void addColumnWidth(int columnIndex, int width) {
		columnWidthMap.put(columnIndex, width);
	}

	/**
	 * Adds all the column widths
	 * @param columnIndex
	 * @param width
	 */
	public void addColumnWidth(int[] columnIndex, int[] width) {
		for (int i = 0; i < width.length; i++) {
			addColumnWidth(columnIndex[i], width[i]);
		}
	}

	/**
	 * Adds all width incrementing the columns by the first column passed in the
	 * firstIndex If the first index is 4 and it is passed 3 elements like 100,
	 * 210, 250 in the width array parameter, the combination will be: 4 - 100 5
	 * - 210 6 - 250
	 * @param firstIndex
	 * @param width
	 */
	public void addColumnWidth(int firstIndex, int... width) {
		for (int i = 0; i < width.length; i++) {
			addColumnWidth(firstIndex++, width[i]);
		}
	}

	/**
	 * @param columnIndex
	 * @return The width from the column that corresponds to the columnIndex
	 * parameter.
	 */
	public int getColumnWidth(int columnIndex) {
		try {
			return columnWidthMap.get(columnIndex);
		}
		catch (Exception e) {
			logger.info("An error ocurred when trying to capture the width of the column: " + columnIndex, e);
			return -1;
		}
	}

	/**
	 * @return All tablecolumns in a mutable List
	 */
	public List<TableColumn<T>> getTableColumns() {
		return tableColumns;
	}

}

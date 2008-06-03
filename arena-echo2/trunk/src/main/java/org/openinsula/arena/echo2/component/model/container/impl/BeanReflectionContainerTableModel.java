package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
			return tableColumns.add(new TableColumn(name, property, width));
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
			return tableColumns.add(new TableColumn(name, property, width, dateFormat));
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
	public boolean addTableColumn(String name, ComponentBuilder<?> componentBuilder, int width) {
		try {
			return tableColumns.add(new TableColumn(name, componentBuilder, width));
		}
		catch (Exception e) {
			logger.error("There was an error adding a table column in the " + getClass());
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
	public Object getColumnValue(int columnIndex, T bean) {
		TableColumn tableColumn = tableColumns.get(columnIndex);

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

			return value.toString();
		}
		else if (tableColumn.getComponentBuilder() != null) {

			Component component = tableColumn.getComponentBuilder().buildComponent();

			if (component instanceof Button) {
				((Button) component).setActionCommand(getActionCommandFromBean(bean));
			}
			// TODO implement the rest of the components

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
	 * Wrapper class to control properties from each column.
	 * @author realm
	 * 
	 */
	protected static class TableColumn {
		/**
		 * The name of the column that appears on the column header.
		 */
		private String name;

		/**
		 * The property that will be get from the bean using reflection
		 */
		private String property;

		/**
		 * A component builder
		 */
		private ComponentBuilder<? extends Component> componentBuilder;

		/**
		 * The size property to calculate the width
		 */
		private int width = -1;

		/**
		 * The date format to be used, if this dateFormat is not null or empty
		 * the tableModel will automatically use it.
		 */
		private String dateFormat;

		private TableColumn(String name, String property, int width) {
			super();
			this.name = name;
			this.property = property;
			this.width = width;
		}

		public TableColumn(String name, String property, int width, String dateFormat) {
			super();
			this.name = name;
			this.property = property;
			this.width = width;
			this.dateFormat = dateFormat;
		}

		private TableColumn(String name, ComponentBuilder<?> componentBuilder, int width) {
			super();
			this.name = name;
			this.componentBuilder = componentBuilder;
			this.width = width;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof TableColumn)) {
				return false;
			}

			TableColumn other = (TableColumn) obj;

			return new EqualsBuilder().append(this.name, other.name).isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(this.name).toHashCode();
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

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public ComponentBuilder<? extends Component> getComponentBuilder() {
			return componentBuilder;
		}

		public void setComponentBuilder(ComponentBuilder<? extends Component> componentBuilder) {
			this.componentBuilder = componentBuilder;
		}

		public String getDateFormat() {
			return dateFormat;
		}

		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}

	}

	/**
	 * @param columnIndex
	 * @return The width from the column that corresponds to the columnIndex
	 * parameter.
	 */
	public int getColumnWidth(int columnIndex) {
		try {
			return tableColumns.get(columnIndex).getWidth();
		}
		catch (Exception e) {
			return -1;
		}
	}

	public List<TableColumn> getTableColumns() {
		return tableColumns;
	}

}

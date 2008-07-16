package org.openinsula.arena.echo2.component.model.container.impl;

import nextapp.echo2.app.Component;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openinsula.arena.echo2.component.model.container.ComponentBuilder;

/**
 * Wrapper class to control properties from each column.
 * @author realm
 * 
 */
public class TableColumn<K> {

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
	private ComponentBuilder<? extends Component, K> componentBuilder;

	/**
	 * The date format to be used, if this dateFormat is not null or empty the
	 * tableModel will automatically use it.
	 */
	private String dateFormat;

	public TableColumn(String name, String property) {
		super();
		this.name = name;
		this.property = property;
	}

	public TableColumn(String name, String property, String dateFormat) {
		super();
		this.name = name;
		this.property = property;
		this.dateFormat = dateFormat;
	}

	public TableColumn(String name, ComponentBuilder<?, K> componentBuilder) {
		super();
		this.name = name;
		this.componentBuilder = componentBuilder;
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

	public ComponentBuilder<? extends Component, K> getComponentBuilder() {
		return componentBuilder;
	}

	public void setComponentBuilder(ComponentBuilder<? extends Component, K> componentBuilder) {
		this.componentBuilder = componentBuilder;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}

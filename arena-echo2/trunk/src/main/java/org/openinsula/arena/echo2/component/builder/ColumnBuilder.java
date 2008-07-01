package org.openinsula.arena.echo2.component.builder;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;

/**
 * An builder to help the build of new Columns.
 * @author Joao Galli
 * 
 */
public class ColumnBuilder extends Column {
	private static final long serialVersionUID = 1L;

	public ColumnBuilder() {
	}

	public ColumnBuilder(Component... components) {
		for (Component component : components) {
			this.add(component);
		}
	}

	public ColumnBuilder append(Component... components) {
		for (Component component : components) {
			this.add(component);
		}
		return this;
	}

}

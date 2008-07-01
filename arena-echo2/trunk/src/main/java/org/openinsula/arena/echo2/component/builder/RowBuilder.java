package org.openinsula.arena.echo2.component.builder;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Row;

/**
 * An builder to help the build of new Rows.
 * @author Joao Galli
 *
 */
public class RowBuilder extends Row {
	private static final long serialVersionUID = 1L;

	public RowBuilder append(Component... components) {
		for (Component component : components) {
			this.add(component);
		}
		return this;
	}

}

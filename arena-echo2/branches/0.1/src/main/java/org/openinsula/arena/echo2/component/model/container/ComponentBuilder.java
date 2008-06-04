package org.openinsula.arena.echo2.component.model.container;

import nextapp.echo2.app.Component;

/**
 * This is like a listener that invoked must build a component,
 * it is used in the BeanReflectionContainerTableModel to set a component
 * to each column, unless the column can use reflection with the bean.
 * 
 * @author Joao Galli
 *
 * @param <T>
 */
public interface ComponentBuilder<T extends Component, B> {

	public T buildComponent(int columnIndex, B bean);
	
}

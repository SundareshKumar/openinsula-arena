package org.openinsula.arena.echo2.component.model.container;

import nextapp.echo2.app.Component;

public interface ComponentBuilder<T extends Component> {

	public T buildComponent();
	
}

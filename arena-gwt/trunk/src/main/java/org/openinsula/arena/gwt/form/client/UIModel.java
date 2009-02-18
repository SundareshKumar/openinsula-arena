package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.components.client.ui.WidgetBuilder;

public interface UIModel<T extends UIModelRenderer> extends WidgetBuilder {
	
	T getRenderer();
	
	void setRenderer(T renderer);
	
}

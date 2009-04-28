package org.openinsula.arena.appengine.gwt.client.forms;

import org.openinsula.arena.appengine.gwt.client.ui.WidgetBuilder;

public interface UIModel<T extends UIModelRenderer> extends WidgetBuilder {

	T getRenderer();

	void setRenderer(T renderer);

}

package org.openinsula.arena.gwt.application.client.display;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public interface WidgetDisplayer {

	public void show(Widget widget);

	public void addWidgetDisplayListener(WidgetDisplayListener widgetDisplayListener);

	public void removeWidgetDisplayListener(WidgetDisplayListener widgetDisplayListener);

}

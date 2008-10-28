package org.openinsula.arena.gwt.client.application.display;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractWidgetDisplayer extends Composite implements WidgetDisplayer {

	private final List<WidgetDisplayListener> listeners = new LinkedList<WidgetDisplayListener>();

	private Widget currentWidget;

	protected abstract boolean showWidget(Widget widget);

	public final void show(Widget widget) {
		final Widget oldWidget = currentWidget;

		if ((oldWidget == null || !oldWidget.equals(widget)) && showWidget(widget)) {
			currentWidget = widget;

			fireWidgetShowed(oldWidget);

			if (oldWidget != null) {
				fireWidgetHid(oldWidget);
			}
		}
	}

	public Widget getCurrentWidget() {
		return currentWidget;
	}

	protected void fireWidgetShowed(Widget widget) {
		for (final WidgetDisplayListener listener : listeners) {
			listener.widgetShowed(widget);
		}
	}

	protected void fireWidgetHid(Widget widget) {
		for (final WidgetDisplayListener listener : listeners) {
			listener.widgetHid(widget);
		}
	}

	public void addWidgetDisplayListener(WidgetDisplayListener widgetDisplayListener) {
		listeners.add(widgetDisplayListener);
	}

	public void removeWidgetDisplayListener(WidgetDisplayListener widgetDisplayListener) {
		listeners.remove(widgetDisplayListener);
	}

}

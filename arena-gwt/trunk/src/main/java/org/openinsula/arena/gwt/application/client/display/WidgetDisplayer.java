package org.openinsula.arena.gwt.application.client.display;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class WidgetDisplayer extends Composite {

	private final List<WidgetDisplayListener> listeners = new LinkedList<WidgetDisplayListener>();

	private Widget currentWidget;

	protected abstract boolean doShow(Widget widget);

	public abstract void add(Widget widget);

	public abstract void remove(Widget widget);

	public abstract boolean constains(Widget widget);

	public final void show(Widget widget) {
		final Widget oldWidget = currentWidget;

		if ((oldWidget == null || !oldWidget.equals(widget)) && doShow(widget)) {
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

	private void fireWidgetShowed(Widget widget) {
		for (final WidgetDisplayListener listener : listeners) {
			listener.widgetShowed(widget);
		}
	}

	private void fireWidgetHid(Widget widget) {
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

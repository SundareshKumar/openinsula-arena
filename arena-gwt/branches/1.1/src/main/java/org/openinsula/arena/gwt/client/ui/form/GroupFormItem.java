package org.openinsula.arena.gwt.client.ui.form;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * It's a FormItem that (can) contain more than one FocusWidget
 * 
 * @see FormItem
 * @see FormItemWidgetWrapper
 * @param <T>
 */
public class GroupFormItem<T extends Widget> extends FormItem<T> {

	private boolean sameLine;

	private T[] widgets;

	public GroupFormItem(final String label, final T[] widgets) {
		this(label, widgets, null, false, false, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint) {
		this(label, widgets, hint, false, false, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint, boolean optional, boolean sameLine) {
		this(label, widgets, hint, optional, sameLine, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint, boolean optional, boolean sameLine,
			final String suffix) {
		this.widgets = widgets;
		createComponents(label, null, hint, optional, suffix);

		this.sameLine = sameLine;
		super.pack();
	}

	protected Widget getWidgetForPanel() {
		Panel panel;

		panel = !isSameLine() ? new VerticalPanel() : new HorizontalPanel();

		for (T w : widgets) {
			FormItemWidgetWrapper<T> wrapper = new FormItemWidgetWrapper<T>(w, getMainPanel(), getHint());
			panel.add(wrapper.getWidget());
		}

		if (widgets.length > 0) {
			final T localWidget = widgets[0];
			setLabelClickListener(localWidget);
		}

		return panel;
	}

	public boolean isSameLine() {
		return sameLine;
	}

	@Override
	public void clear() {
		for (T widget : widgets) {
			clearWidget(widget);
		}
		isNew = true;
		refresh();
	}
	
	@Override
	public T getWidget() {
		throw new UnsupportedOperationException("Use GroupFormItem.getWidgets() instead!");
	}
	
	public T[] getWidgets() {
		return widgets;
	}

}

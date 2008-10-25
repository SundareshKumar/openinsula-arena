package org.openinsula.arena.gwt.client.user.ui.table;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
@SuppressWarnings("unchecked")
public abstract class WidgetsTableModel extends BeanTableModel {

	private final List<Widget[]> widgets = new LinkedList<Widget[]>();

	protected abstract Widget[] createWidgets(Object item);

	public Object getValueAt(int rowIndex, int columnIndex) {
		final Object obj = get(rowIndex);
		final Widget[] widgets = getWidgets(obj);

		return widgets == null ? null : widgets[columnIndex];
	}

	@Override
	public void clear() {
		widgets.clear();

		super.clear();
	}

	@Override
	public <T> T remove(int index) {
		final Widget[] widgets2 = widgets.remove(index);

		if (widgets2 != null) {
			return (T) super.remove(index);
		}
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		final int index = indexOf(obj);
		final Widget[] widgets2 = widgets.remove(index);

		return widgets2 == null ? false : super.remove(obj);
	}

	public <T extends Widget> T getWidget(Object obj, int columnIndex) {
		final Widget[] widgets = getWidgets(obj);

		if (widgets == null) {
			return null;
		}
		return (T) widgets[columnIndex];
	}

	public Widget[] getWidgets(Object obj) {
		final int index = indexOf(obj);
		Widget[] widgetsArray = null;

		if (index >= 0 && index < widgets.size()) {
			widgetsArray = widgets.get(index);
		}

		if (widgetsArray == null) {
			widgetsArray = createWidgets(obj);

			widgets.add(index, widgetsArray);
		}

		return widgetsArray;
	}

	@Override
	public void setItems(List items) {
		widgets.clear();

		super.setItems(items);
	}

}

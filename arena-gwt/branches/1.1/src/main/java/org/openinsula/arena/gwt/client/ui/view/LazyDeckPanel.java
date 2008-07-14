package org.openinsula.arena.gwt.client.ui.view;

import java.util.LinkedList;

import org.openinsula.arena.gwt.client.ui.LazyWidget;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

public class LazyDeckPanel extends DeckPanel {

	private final LinkedList<ListEntry<?>> lazyWidgetList;

	public LazyDeckPanel() {
		lazyWidgetList = new LinkedList<ListEntry<?>>();
	}

	@Override
	public void add(final Widget w) {
		lazyWidgetList.add(new WidgetEntry(w));
	}

	public void add(final LazyWidget w) {
		lazyWidgetList.add(new LazyEntry(w));
	}

	@Override
	public void insert(final Widget w, final int beforeIndex) {
		lazyWidgetList.add(beforeIndex, new WidgetEntry(w));
	}

	public void insert(final LazyWidget w, final int beforeIndex) {
		lazyWidgetList.add(beforeIndex, new LazyEntry(w));
	}

	@Override
	public boolean remove(final Widget w) {
		if (lazyWidgetList.contains(w)) {
			int idx = lazyWidgetList.indexOf(w);
			ListEntry<?> entry = lazyWidgetList.get(idx);
			boolean removed = entry.remove();

			if (removed) {
				lazyWidgetList.remove(idx);
			}
			return removed;
		}

		return false;
	}

	public boolean remove(final LazyWidget w) {
		if (lazyWidgetList.contains(w)) {
			int idx = lazyWidgetList.indexOf(w);
			ListEntry<?> entry = lazyWidgetList.get(idx);
			boolean removed = entry.remove();

			if (removed) {
				lazyWidgetList.remove(idx);
			}
			return removed;
		}

		return false;
	}

	@Override
	public boolean remove(final int index) {
		try {
			ListEntry<?> entry = lazyWidgetList.get(index);
			boolean removed = entry.remove();

			if (removed) {
				lazyWidgetList.remove(index);
			}
			return removed;

		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	@Override
	public Widget getWidget(final int index) {
		ListEntry<?> entry = lazyWidgetList.get(index);
		entry.loadAndReturnIndex();

		if (entry instanceof WidgetEntry) {
			return ((WidgetEntry) entry).widget;
		}

		return ((LazyEntry) entry).loadedWidget;
	}

	@Override
	public int getWidgetCount() {
		return lazyWidgetList.size();
	}

	public int getLazyWidgetCount() {
		int result = 0;
		for (ListEntry<?> entry : lazyWidgetList) {
			if (entry.isLazy()) {
				result++;
			}
		}
		return result;
	}

	public int getLoadedWidgetCount() {
		int result = 0;
		for (ListEntry<?> entry : lazyWidgetList) {
			if (!entry.isLazy()) {
				result++;
			}
		}
		return result;
	}

	@Override
	public void showWidget(final int index) {
		ListEntry<?> lazyEntry = lazyWidgetList.get(index);
		super.showWidget(lazyEntry.loadAndReturnIndex());
	}

	private abstract class ListEntry<T> {
		final T widget;

		ListEntry(final T widget) {
			this.widget = widget;
		}

		final void realAdd(final Widget w) {
			LazyDeckPanel.super.add(w);
		}

		final boolean realRemove(final Widget w) {
			return LazyDeckPanel.super.remove(w);
		}

		@Override
		public int hashCode() {
			return widget.hashCode();
		}

		@Override
		public boolean equals(final Object obj) {
			return widget.equals(obj);
		}

		abstract boolean isLazy();

		abstract int loadAndReturnIndex();

		abstract boolean remove();

	}

	private class LazyEntry extends ListEntry<LazyWidget> {
		private Widget loadedWidget;

		public LazyEntry(final LazyWidget lazy) {
			super(lazy);
		}

		boolean isLazy() {
			return loadedWidget == null;
		}

		int loadAndReturnIndex() {
			if (isLazy()) {
				loadedWidget = widget.createWidget();
				realAdd(loadedWidget);
			}
			return LazyDeckPanel.super.getWidgetIndex(loadedWidget);
		}

		boolean remove() {
			if (isLazy()) {
				return true;
			}
			return realRemove(loadedWidget);
		}
	}

	private class WidgetEntry extends ListEntry<Widget> {

		public WidgetEntry(final Widget w) {
			super(w);
			realAdd(widget);
		}

		boolean isLazy() {
			return false;
		}

		int loadAndReturnIndex() {
			return LazyDeckPanel.super.getWidgetIndex(widget);
		}

		boolean remove() {
			return realRemove(widget);
		}

	}

}

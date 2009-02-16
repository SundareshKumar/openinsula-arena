package org.openinsula.arena.gwt.components.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class HTMLWidget<T extends Element> extends ComplexPanel implements WidgetBuilder {

	private final T element;

	private Map<Element, Widget> _wrappedWidgets;

	public HTMLWidget() {
		element = createHTMLElement(Document.get());
		setElement(element);
	}

	private Map<Element, Widget> wrappedWidgets() {
		if (_wrappedWidgets == null) {
			_wrappedWidgets = new HashMap<Element, Widget>();
		}
		return _wrappedWidgets;
	}

	public final T getHTMLElement() {
		return element;
	}

	protected abstract T createHTMLElement(final Document document);

	// Widget enhancements

	public void insert(final Widget child, final int beforeIndex) {
		super.insert(child, getElement(), beforeIndex, true);
	}

	public void insert(final Element element, final int beforeIndex) {
		if (element != null) {
			Widget widget = wrappedWidgets().get(element);

			if (widget == null) {
				widget = HTMLWidgetFactory.wrap(element);
				wrappedWidgets().put(element, widget);
			}
			insert(widget, beforeIndex);
		}
	}

	public void addFirst(final Widget child) {
		insert(child, 0);
	}

	public void addFirst(final Element element) {
		insert(element, 0);
	}

	public void add(final Element element) {
		if (element != null) {
			Widget widget = wrappedWidgets().get(element);

			if (widget == null) {
				widget = HTMLWidgetFactory.wrap(element);
				wrappedWidgets().put(element, widget);
			}
			add(widget);
		}
	}

	public boolean remove(final Element element) {
		boolean result = element != null && wrappedWidgets().containsKey(element);

		if (result) {
			final Widget widget = wrappedWidgets().get(element);
			result = super.remove(widget);

			if (result) {
				wrappedWidgets().remove(element);
			}
		}
		return result;
	}

	public int getWidgetIndex(final Element child) {
		if (child != null && wrappedWidgets().containsKey(child)) {
			return getWidgetIndex(wrappedWidgets().get(child));
		}

		return -1;
	}

	// ComplexPanel overrides

	@Override
	public void clear() {
		super.clear();

		if (_wrappedWidgets != null) {
			_wrappedWidgets.clear();
		}
	}

	@Override
	public void add(final Widget child) {
		super.add(child, getElement());
	}

	// WidgetBuilder Impl

	public Widget toWidget() {
		return this;
	}
	
}

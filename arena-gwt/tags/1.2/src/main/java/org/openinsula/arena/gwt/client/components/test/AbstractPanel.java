package org.openinsula.arena.gwt.client.components.test;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPanel<T extends PanelListener> extends Composite implements ActionPanel<T>, HasWidgets {

	protected T listener;
	
	public T getListener() {
		return listener;
	}

	public void setListener(T listener) {
		this.listener = listener;
	}

	public void setPanelListener(T listener) {
		this.listener = listener;
	}
	
	protected abstract Widget getMainWidget();
	
	private HasWidgets hasWidgets;
	
	{
		Widget widget = getMainWidget();
		if (!(widget instanceof HasWidgets)) {
			throw new IllegalArgumentException("O widget adicionado deve implementar a interface " + HasWidgets.class.getName());
		}
		hasWidgets = (HasWidgets) widget;
		
		initWidget(widget);
	}

	public void add(Widget w) {
		hasWidgets.add(w);
	}

	public void clear() {
		hasWidgets.clear();
	}

	public Iterator<Widget> iterator() {
		return hasWidgets.iterator();
	}

	public boolean remove(Widget w) {
		return hasWidgets.remove(w);
	}
	
}

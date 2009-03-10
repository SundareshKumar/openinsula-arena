package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.form.client.UIModel;
import org.openinsula.arena.gwt.form.client.WindowRenderer;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;

class WufooWindowRenderer extends WufooWindowLayout implements WindowRenderer {
	
	private LazyChildWidget<HeadingElement> titleElement;

	public WufooWindowRenderer() {
		super(true);
	}

	public void onTitleChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			titleElement.remove();
		}
		else {
			titleElement.get().setInnerHTML(newValue);
		}
	}

	public void onBodyChange(final UIModel<?> oldValue, final UIModel<?> newValue) {
		if (oldValue == null) {
			windowElement.add(newValue.toWidget());
		}
		else {
			windowElement.remove(oldValue.toWidget());

			if (newValue != null) {
				windowElement.add(newValue.toWidget());
			}
		}
	}

	@Override
	protected void initLazyWidgets() {
		titleElement = new LazyChildWidget<HeadingElement>() {
			@Override
			protected HeadingElement createProperty(final Document document) {
				final HeadingElement h1 = document.createHElement(1);
				h1.setId("logo");
				windowElement.add(h1);
				return h1;
			}

			@Override
			protected void beforeRemove(final HeadingElement widget) {
				windowElement.remove(widget);
			}
		};
	}

}

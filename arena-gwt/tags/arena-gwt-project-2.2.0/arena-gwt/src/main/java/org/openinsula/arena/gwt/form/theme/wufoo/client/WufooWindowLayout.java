package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Widget;

abstract class WufooWindowLayout extends WufooWidget {

	private ImageElement topShadow;

	private ImageElement bottomShadow;

	protected HTMLWidget<DivElement> windowElement;

	WufooWindowLayout(final boolean topLevel) {
		topLevel(topLevel);
	}

	@Override
	Widget createRequiredWidgets() {
		windowElement = HTMLWidgetFactory.div();

		final Document document = Document.get();

		topShadow = document.createImageElement();
		topShadow.setAlt("");
		topShadow.setSrc("images/top.png");

		bottomShadow = document.createImageElement();
		bottomShadow.setAlt("");
		bottomShadow.setSrc("images/bottom.png");

		final HTMLWidget<DivElement> wrapper = HTMLWidgetFactory.div();
		wrapper.add(topShadow);
		wrapper.add(windowElement);
		wrapper.add(bottomShadow);

		return wrapper;
	}

	void topLevel(final boolean isTopLevel) {
		if (isTopLevel) {
			topShadow.setId("top");
			bottomShadow.setId("bottom");
			windowElement.getElement().setId("container");
			setStyleName("wrapper");
		} else {
			topShadow.setId("top2");
			bottomShadow.setId("bottom2");
			windowElement.getElement().setId("container2");
		}
	}

	// ParentWidgetAware impl

//	public void onAdd(final HTMLWidget<?> parent) {
//		parent.add(topShadow);
//		parent.add(windowElement);
//		parent.add(bottomShadow);
//	}
//
//	public void onRemove(final HTMLWidget<?> parent) {
//		parent.remove(topShadow);
//		parent.remove(windowElement);
//		parent.remove(bottomShadow);
//	}

}

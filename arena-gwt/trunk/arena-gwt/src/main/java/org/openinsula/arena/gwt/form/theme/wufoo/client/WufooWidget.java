package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.form.client.UIModelRenderer;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

abstract class WufooWidget extends Composite implements UIModelRenderer {

	WufooWidget() {
		initWidget(createRequiredWidgets());
		initLazyWidgets();
	}

	/**
	 * @return Main widget to be wrapped with {@link Composite}
	 */
	abstract Widget createRequiredWidgets();

	protected void initLazyWidgets() {
	}

	public final Widget renderModel() {
		return this;
	}

}

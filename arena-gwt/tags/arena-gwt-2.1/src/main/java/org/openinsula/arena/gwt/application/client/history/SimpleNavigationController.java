package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class SimpleNavigationController implements NavigationController {

	private final SimplePanel panel;

	public SimpleNavigationController() {
		this(new SimplePanel());
	}

	public SimpleNavigationController(SimplePanel panel) {
		this.panel = panel;
	}

	public void show(Widget widget) {
		final Widget currentWidget = panel.getWidget();

		if (currentWidget != null) {
			panel.remove(currentWidget);
		}

		panel.add(widget);
	}

	public native void scrollTop() /*-{
		scroll(0,0);
	}-*/;

	public void showPageNotFound(String historyToken) {
		if (!GWT.isScript()) {
			GWT.log("Page Not Found: " + historyToken, null);
		}
	}

	public SimplePanel getPanel() {
		return panel;
	}

}

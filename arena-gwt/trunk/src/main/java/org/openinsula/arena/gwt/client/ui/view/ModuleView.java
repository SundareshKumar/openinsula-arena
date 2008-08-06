package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public abstract class ModuleView extends NavigationSupport {
	protected final HorizontalPanel mainPanel;

	public ModuleView() {
		mainPanel = new HorizontalPanel();
		initWidget(mainPanel);

		mainPanel.setWidth("97%");
		mainPanel.setStyleName("ModuleView");

		configureViews();
		pack();
	}

	protected abstract void configureViews();

	public void pack() {
		super.pack();

		Widget menuBar = createMenuBar();
		menuBar.setStyleName("ModuleViewMenuBar");

		mainPanel.add(menuBar);
		mainPanel.add(getViewDeck());

		mainPanel.setCellWidth(menuBar, "20%");
		mainPanel.setCellWidth(getViewDeck(), "80%");
	}

	protected Widget createMenuBar() {
		FlowPanel menuPanel = new FlowPanel();

		for (Hyperlink link : getLinks()) {
			menuPanel.add(link);
		}

		return menuPanel;
	}

}

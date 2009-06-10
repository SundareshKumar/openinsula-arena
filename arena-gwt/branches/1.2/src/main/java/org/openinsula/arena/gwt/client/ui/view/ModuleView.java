package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public abstract class ModuleView extends NavigationSupport {

	protected final HorizontalPanel mainPanel;

	protected final String moduleLinkToken = getHistoryToken(null, this);;

	public ModuleView() {
		mainPanel = new HorizontalPanel();
		initWidget(mainPanel);

		mainPanel.setWidth("97%");
		mainPanel.setStyleName("ModuleView");

		//		configureViews();
		//		pack();

		handleModuleHistory();
	}

	protected abstract void configureViews();

	@Override
	public void pack() {
		super.pack();

		final Widget menuBar = createMenuBar();
		menuBar.setStyleName("ModuleViewMenuBar");

		mainPanel.add(menuBar);
		mainPanel.add(getViewDeck());

		mainPanel.setCellWidth(menuBar, "20%");
		mainPanel.setCellWidth(getViewDeck(), "80%");
	}

	protected Widget createMenuBar() {
		final FlowPanel menuPanel = new FlowPanel();

		for (final Hyperlink link : getLinks()) {
			menuPanel.add(link);
		}

		return menuPanel;
	}


	private void handleModuleHistory() {
		History.addHistoryListener(new HistoryListener() {
			public void onHistoryChanged(String token) {
				if (moduleLinkToken.equals(token)) {
					onHistoryChangedToThisModule(token);
				}
			}
		});

	}

	protected void onHistoryChangedToThisModule(String token) {

	}

}

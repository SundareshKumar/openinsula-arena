package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>CSS rules:
 * <p>.ApplicationView { }
 * <p>.ApplicationViewTitle { }
 * <p>.ApplicationViewTitleBar { }
 * <p>.ApplicationViewMenuBar { }
 * @author Eduardo Rebola
 *
 */
public class ApplicationView extends NavigationSupport {
	private Label title;

	private VerticalPanel mainPanel;

	public ApplicationView() {
		createTitle("Insert Title Here");
	}

	public ApplicationView(final String applicationTitle) {
		createTitle(applicationTitle);
	}

	private void createTitle(final String string) {
		title = new Label(string);
		title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		title.setStyleName("ApplicationViewTitle");
	}

	public void setTitle(final String title) {
		this.title.setText(title);
	}

	protected Widget getApplicationTitle() {
		return title;
	}
	
	public void pack() {
		super.pack();

		HorizontalPanel titlePanel = new HorizontalPanel();
		titlePanel.setWidth("100%");
		titlePanel.setStyleName("ApplicationViewTitleBar");
		titlePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

//		titlePanel.add(title);
		titlePanel.add(getApplicationTitle());

		FlowPanel menuPanel = new FlowPanel();
		menuPanel.setStyleName("ApplicationViewMenuBar");

		for (Hyperlink link : getLinks()) {
			menuPanel.add(link);
		}

		titlePanel.add(menuPanel);

		mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setStyleName("ApplicationView");

		mainPanel.add(titlePanel);
		mainPanel.add(getViewDeck());

		initWidget(mainPanel);
	}

}

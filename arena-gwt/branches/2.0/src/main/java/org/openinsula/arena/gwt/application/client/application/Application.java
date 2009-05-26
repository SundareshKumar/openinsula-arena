package org.openinsula.arena.gwt.application.client.application;

import org.openinsula.arena.gwt.application.client.history.HistoryController;
import org.openinsula.arena.gwt.application.client.history.NavigationController;
import org.openinsula.arena.gwt.application.client.history.SimpleNavigationController;

/**
 * @author Lucas K Mogari
 */
public final class Application {

	private ApplicationContext context;

	private HistoryController historyController;

	private NavigationController navigationController;

	private static Application instance;

	private Application() {
		context = new DefaultApplicationContext();
		historyController = new ApplicationHistoryController();
		navigationController = new SimpleNavigationController();
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public HistoryController getHistoryController() {
		return historyController;
	}

	public void setHistoryController(HistoryController historyController) {
		this.historyController = historyController;
	}

	public NavigationController getNavigationController() {
		return navigationController;
	}

	public void setNavigationController(NavigationController navigationController) {
		this.navigationController = navigationController;
	}

}

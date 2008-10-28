package org.openinsula.arena.gwt.client.application;

import org.openinsula.arena.gwt.client.application.history.HistoryController;

/**
 * @author Lucas K Mogari
 */
public final class Application {

	private ApplicationContext context;

	private ContextAttributeLoader attributeLoader;

	private HistoryController historyController;

	private static Application instance;

	private Application() {
		context = new DefaultApplicationContext();
		historyController = new ApplicationHistoryController();
		attributeLoader = new DefaultContextAttributeLoader();
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

	public ContextAttributeLoader getAttributeLoader() {
		return attributeLoader;
	}

	public void setAttributeLoader(ContextAttributeLoader attributeLoader) {
		this.attributeLoader = attributeLoader;
	}

}

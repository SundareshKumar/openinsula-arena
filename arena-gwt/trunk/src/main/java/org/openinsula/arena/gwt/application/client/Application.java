package org.openinsula.arena.gwt.application.client;

import org.openinsula.arena.gwt.application.client.context.ApplicationContext;
import org.openinsula.arena.gwt.application.client.context.DefaultApplicationContext;
import org.openinsula.arena.gwt.application.client.display.DeckPanelDisplayer;
import org.openinsula.arena.gwt.application.client.display.WidgetDisplayer;
import org.openinsula.arena.gwt.application.client.history.DefaultHistoryController;
import org.openinsula.arena.gwt.application.client.history.HistoryController;

/**
 * @author Lucas K Mogari
 */
public final class Application {

	private ApplicationContext context;

	private HistoryController historyController;

	private WidgetDisplayer widgetDisplayer;

	private static Application instance;

	private Application() {
		context = new DefaultApplicationContext();
		historyController = new DefaultHistoryController();
		widgetDisplayer = new DeckPanelDisplayer();
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}

	public void setAttribute(String name, Object attribute) {
		context.setAttribute(name, attribute);
	}

	public <T> T getAttribute(String name) {
		return context.<T> getAttribute(name);
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

	public WidgetDisplayer getWidgetDisplayer() {
		return widgetDisplayer;
	}

	public void setWidgetDisplayer(WidgetDisplayer widgetDisplayer) {
		this.widgetDisplayer = widgetDisplayer;
	}

}

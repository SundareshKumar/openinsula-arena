package org.openinsula.arena.gwt.application.client.history;

import com.google.gwt.i18n.client.Constants;

/**
 * @author Lucas K Mogari
 */
public interface HistoryTokens extends Constants {

	@DefaultStringValue("start")
	public String startPageToken();

	@DefaultStringValue("page-not-found")
	public String pageNotFoundToken();

}

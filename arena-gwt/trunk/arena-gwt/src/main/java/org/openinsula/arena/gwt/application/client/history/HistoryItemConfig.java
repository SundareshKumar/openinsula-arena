package org.openinsula.arena.gwt.application.client.history;

/**
 * @author Lucas K Mogari
 */
public abstract class HistoryItemConfig {

	private static final String[] TOKEN_SEPARATORS = { "/", "," };

	public static String[] separeHistoryToken(String historyToken) {
		String[] strings = null;

		for (final String separator : TOKEN_SEPARATORS) {
			if (historyToken.contains(separator)) {
				strings = historyToken.split(separator);
				break;
			}
		}

		if (strings == null) {
			strings = new String[1];
			strings[0] = historyToken;
		}

		return strings;
	}
	
	public static String createHistoryToken(String token, String...tokens) {
		if (tokens == null || tokens.length == 0) {
			return token;
		}
		
		StringBuilder sb = new StringBuilder(token);
		
		for (String t : tokens) {
			sb.append('/');
			sb.append(t);
		}
		
		return sb.toString();
	}

	public abstract boolean containsHistoryToken(String historyToken);

	public abstract HistoryItem getHistoryItem(String historyToken);

	public abstract void addHistoryItem(HistoryItem historyItem);

	public abstract void addHistoryItems(HistoryItemsProvider historyItemsProvider);

	public abstract void addHistoryItem(String token, String name, Object target);

	public abstract HistoryItem removeHistoryItem(String historyToken);

}

package org.openinsula.arena.gwt.samples.client;

public abstract class Constants {
	public static final String BASE_URL = "http://localhost:8080/arena-gwt-samples-server/";

	public static String getURL(final String serviceUrl) {
		assert serviceUrl != null;

		String s = serviceUrl.trim();

		int beginIndex = s.startsWith("/") ? 1 : 0;
		int endIndex = s.endsWith("/") ? s.length() - 1 : s.length();

		return BASE_URL + s.substring(beginIndex, endIndex);
	}

}

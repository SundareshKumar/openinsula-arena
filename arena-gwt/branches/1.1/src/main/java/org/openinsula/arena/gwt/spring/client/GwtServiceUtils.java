package org.openinsula.arena.gwt.spring.client;

import com.google.gwt.core.client.GWT;

/**
 * @author Joao Galli
 * @deprecated Refactored to {@link org.openinsula.arena.gwt.spring.client.rpc.GwtServiceUtils}
 */
public class GwtServiceUtils {

	private static String prefix = "";

	private static String suffix = ".do";

	/**
	 *
	 * @param proxy
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String setRpcEndPointUrl(final Object proxy, final String prefix, final String suffix) {
		GwtServiceUtils.setPrefix(prefix);
		GwtServiceUtils.setSuffix(suffix);

		return setRpcEndPointUrl(proxy);
	}

	/**
	 * Prepares the url of the proxy to be compatible with the use of Spring Controller.
	 * @param proxy
	 * @return
	 */
	public static String setRpcEndPointUrl(final Object proxy) {
		StringBuffer sb = new StringBuffer();
		sb.append(GWT.getTypeName(proxy));
		String endPointName = sb.substring(0, sb.indexOf("_Proxy"));
		endPointName = endPointName.replace('.', '/');

		endPointName = GWT.getHostPageBaseURL() + prefix + endPointName + suffix;

		return endPointName;
	}

	public static String getPrefix() {
		return prefix;
	}

	public static void setPrefix(final String prefix) {
		GwtServiceUtils.prefix = prefix;
	}

	public static String getSuffix() {
		return suffix;
	}

	public static void setSuffix(final String suffix) {
		GwtServiceUtils.suffix = suffix;
	}

}

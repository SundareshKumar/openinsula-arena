package org.openinsula.arena.gwt.spring.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * @author Joao Galli
 * @author Eduardo Rebola
 */
public abstract class GwtServiceUtils {
	public static final String DEFAULT_PREFIX = "";
	public static final String DEFAULT_SUFFIX = ".do";

	/**
	 * @author rebola
	 */
	public static void configureRemoteService(final Object remoteService) {
		ServiceDefTarget endpoint = (ServiceDefTarget) remoteService;
		String moduleRelativeURL = setRpcEndPointUrl(endpoint);
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}

	/**
	 *
	 * @param proxy
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String setRpcEndPointUrl(final Object proxy) {
		return setRpcEndPointUrl(proxy, DEFAULT_PREFIX, DEFAULT_SUFFIX);
	}

	/**
	 * Prepares the url of the proxy to be compatible with the use of Spring
	 * Controller.
	 *
	 * @param proxy
	 * @return
	 */
	public static String setRpcEndPointUrl(final Object proxy, final String prefix, final String suffix) {
		StringBuffer sb = new StringBuffer();
		sb.append(GWT.getTypeName(proxy));
		String endPointName = sb.substring(0, sb.indexOf("_Proxy"));
		endPointName = endPointName.replace('.', '/');

		endPointName = GWT.getHostPageBaseURL() + prefix + endPointName + suffix;

		return endPointName;
	}

}

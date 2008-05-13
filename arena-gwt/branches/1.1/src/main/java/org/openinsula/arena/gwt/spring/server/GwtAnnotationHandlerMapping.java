package org.openinsula.arena.gwt.spring.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.AbstractDetectingUrlHandlerMapping;

import com.google.gwt.user.client.rpc.RemoteService;

public class GwtAnnotationHandlerMapping extends AbstractDetectingUrlHandlerMapping {

	private String prefix = "/";

	private String suffix = ".rpc";

	protected final Log logger = LogFactory.getLog(getClass());

	private String getRemoteServiceInterfaceName(final Class<?> handlerType) {
		Class<?>[] interfaces = handlerType.getInterfaces();

		for (Class<?> _interface : interfaces) {
			if (RemoteService.class.isAssignableFrom(_interface)) {
				return _interface.getName();
			}
		}

		return null;
	}

	protected String[] buildUrls(final Class<?> handlerType, final String beanName) {
		String remoteServiceName = getRemoteServiceInterfaceName(handlerType);

		Assert.notNull(remoteServiceName, "Unable to generate name for " + handlerType.getName()
				+ "; cannot locate interface that is a subclass of RemoteService");

		String classPath = StringUtils.replace(remoteServiceName, ".", "/");

		if (logger.isDebugEnabled()) {
			logger.debug("remote service interface classpath: " + classPath);
		}

		String url = String.format("%s%s%s", prefix, classPath, suffix);

		return new String[] { url };
	}

	@Override
	protected final String[] determineUrlsForHandler(final String beanName) {
		Class<?> handlerType = getApplicationContext().getType(beanName);

		if (handlerType.isAnnotationPresent(GwtRpcEndPoint.class)) {
			GwtRpcEndPoint endPointAnnotation = handlerType.getAnnotation(GwtRpcEndPoint.class);
			String endPoint = endPointAnnotation.value();
			return StringUtils.hasText(endPoint) ? new String[] {endPoint} : buildUrls(handlerType, beanName);
		}

		return new String[0];
	}

	public final void setPrefix(final String prefix) {
		this.prefix = prefix;
	}

	public final void setSuffix(final String suffix) {
		this.suffix = suffix;
	}
}

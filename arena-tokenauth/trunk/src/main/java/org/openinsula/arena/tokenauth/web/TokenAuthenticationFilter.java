package org.openinsula.arena.tokenauth.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.tokenauth.TokenAuthenticator;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenAuthenticationFilter implements Filter {

	protected final Log logger = LogFactory.getLog(getClass());

	protected static final String CONFIG_DELIMITERS = " \t\n";

	private TokenAuthenticator tokenAuthenticator;

	private String[] exceptionUris;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.exceptionUris = getExceptionUrls(filterConfig);

		ServletContext servletContext = filterConfig.getServletContext();
		this.tokenAuthenticator = getTokenAuthenticator(servletContext);
	}

	protected String[] getExceptionUrls(FilterConfig filterConfig) {
		String[] urls = StringUtils.tokenizeToStringArray(filterConfig.getInitParameter("exceptionUris"),
				CONFIG_DELIMITERS);
		return urls == null ? new String[] {} : urls;
	}

	protected TokenAuthenticator getTokenAuthenticator(ServletContext servletContext) {
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		return (TokenAuthenticator) BeanFactoryUtils.beanOfType(applicationContext, TokenAuthenticator.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = getUri(httpRequest);
		if (isExceptionUri(uri)) {
			chain.doFilter(request, response);
			return;
		}

		String tokenHeader = httpRequest.getHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER);

		if (tokenHeader == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Token Header not found for request: %s", uri));
			}
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		String newToken = tokenAuthenticator.isAuthenticated(tokenHeader);
		if (newToken != null) {
			chain.doFilter(request, response);

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Setting Header '%s' to value '%s'",
						TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER, newToken));
			}
			httpResponse.setHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER, newToken);
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Could not renewal token '%s'. Forbidding access.", tokenHeader));
			}
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	protected String getUri(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();

		sb.append(request.getServletPath());

		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			sb.append(pathInfo);
		}

		return sb.toString();
	}

	protected boolean isExceptionUri(String uri) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Verifying URI '%s' as exception URI.", uri));
		}

		for (String exceptionUri : exceptionUris) {
			if (uri.matches(exceptionUri)) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("URI '%s' matched exception URI '%s'", uri, exceptionUri));
				}

				return true;
			}
		}

		return false;
	}

	@Override
	public void destroy() {
	}

}

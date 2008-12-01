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

import org.openinsula.arena.tokenauth.TokenAuthenticator;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenAuthenticationFilter implements Filter {

	protected static final String CONFIG_DELIMITERS = " \t\n";

	private TokenAuthenticator tokenAuthenticator;

	private String[] exceptionUrls;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.exceptionUrls = getExceptionUrls(filterConfig);

		ServletContext servletContext = filterConfig.getServletContext();
		this.tokenAuthenticator = getTokenAuthenticator(servletContext);
	}

	protected String[] getExceptionUrls(FilterConfig filterConfig) {
		String[] urls = StringUtils.tokenizeToStringArray(filterConfig.getInitParameter("exceptionUrls"),
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

		if (isExceptionUri(httpRequest.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		String tokenHeader = httpRequest.getHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER);

		String newToken = tokenAuthenticator.isAuthenticated(tokenHeader);
		if (newToken != null) {
			chain.doFilter(request, response);
			httpResponse.setHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER, newToken);
		}
		else {
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	protected boolean isExceptionUri(String uri) {
		for (String exceptionUrl : exceptionUrls) {
			if (uri.matches(exceptionUrl)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void destroy() {
	}

}

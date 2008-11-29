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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TokenAuthenticationFilter implements Filter {

	private TokenAuthenticator tokenAuthenticator;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		tokenAuthenticator = (TokenAuthenticator) BeanFactoryUtils.beanOfType(applicationContext,
				TokenAuthenticator.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

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

	@Override
	public void destroy() {
	}

}

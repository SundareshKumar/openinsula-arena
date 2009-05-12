package org.openinsula.arena.openid;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenIdFilter implements Filter {

	public static final String GOOGLE_ACCOUNT_OPENID_URL = "https://www.google.com/accounts/o8/id";

	public static final String OPENID_DISCOVERY_INFORMATION_ATTRIBUTE = "org.openinsula.arena.openid.DiscoveryInformation";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private ConsumerManager manager;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			manager = new ConsumerManager();
			manager.getRealmVerifier().setEnforceRpId(false);
		}
		catch (ConsumerException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Identifier identifier = null;
		if (isAuthenticated(httpServletRequest)) {
			chain.doFilter(request, response);
		}
		else if ((identifier = verify(httpServletRequest)) != null) {
			httpServletRequest.getSession().setAttribute(IdentityUtils.IDENTITY_ATTRIBUTE, identifier);
			chain.doFilter(request, response);
		}
		else {
			authenticate(httpServletRequest, httpServletResponse);
		}
	}

	private void authenticate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException {
		logger.debug("Redirecting to Google OpenID authentication URL.");
		
		try {
			DiscoveryInformation discovered = manager.associate(manager.discover(GOOGLE_ACCOUNT_OPENID_URL));

			httpServletRequest.getSession().setAttribute(OPENID_DISCOVERY_INFORMATION_ATTRIBUTE, discovered);

			AuthRequest authReq = manager.authenticate(discovered, buildUrlString(httpServletRequest));

			FetchRequest request = FetchRequest.createFetchRequest();
			request.addAttribute("email", "http://schema.openid.net/contact/email", true);
			authReq.addExtension(request);

			httpServletResponse.sendRedirect(authReq.getDestinationUrl(true));
		}
		catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private boolean isAuthenticated(HttpServletRequest httpServletRequest) throws ServletException {
		logger.debug("Verifying if the request is already authenticated.");
		return IdentityUtils.obtainIdentity(httpServletRequest) != null;
	}

	private Identifier verify(HttpServletRequest httpServletRequest) throws ServletException {
		logger.debug("Verifying if the request is an authentication response.");
		try {
			DiscoveryInformation discovered = (DiscoveryInformation) httpServletRequest.getSession().getAttribute(
					OPENID_DISCOVERY_INFORMATION_ATTRIBUTE);

			ParameterList parameterList = new ParameterList(httpServletRequest.getParameterMap());

			VerificationResult verification = manager.verify(buildUrlString(httpServletRequest), parameterList,
					discovered);

			Identifier verified = verification.getVerifiedId();

			if (verified != null) {
				logger.debug("Authentication suceeded.");

				AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

				if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
					FetchResponse fetchResp = (FetchResponse) authSuccess.getExtension(AxMessage.OPENID_NS_AX);

					String email = fetchResp.getAttributeValue("email");
					Identity identity = new Identity();
					identity.setEmail(email);
					
					IdentityUtils.setIdentity(httpServletRequest, identity);

					if (logger.isDebugEnabled()) {
						logger.debug(String.format("e-mail informed by authentication response: %s", email));
					}
				}

				return verified;
			}
		}
		catch (Exception ex) {
			logger.debug("Exception processing response. Probably a non authenticated request.", ex);
		}
		return null;
	}

	private String buildUrlString(HttpServletRequest httpServletRequest) {
		StringBuffer receivingURL = httpServletRequest.getRequestURL();
		String queryString = httpServletRequest.getQueryString();
		if (queryString != null && queryString.length() > 0) {
			receivingURL.append("?").append(httpServletRequest.getQueryString());
		}
		return receivingURL.toString();
	}

	@Override
	public void destroy() {
	}

}

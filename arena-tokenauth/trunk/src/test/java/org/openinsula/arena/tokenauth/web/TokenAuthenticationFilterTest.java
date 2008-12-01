package org.openinsula.arena.tokenauth.web;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Test;
import org.openinsula.arena.tokenauth.TokenAuthenticator;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TokenAuthenticationFilterTest {

	@Test
	public void testNonDeclaredExceptionUrlsInitParam() throws ServletException {
		TokenAuthenticationFilter filter = new TokenAuthenticationFilter() {
			@Override
			protected TokenAuthenticator getTokenAuthenticator(ServletContext servletContext) {
				return null;
			}
		};

		MockFilterConfig filterConfig = new MockFilterConfig();

		filter.init(filterConfig);

		assertFalse(filter.isExceptionUri("/rest/test"));
	}

	@Test
	public void testDeclaredExceptionUrlsInitParam() throws ServletException {
		TokenAuthenticationFilter filter = new TokenAuthenticationFilter() {
			@Override
			protected TokenAuthenticator getTokenAuthenticator(ServletContext servletContext) {
				return null;
			}
		};

		MockFilterConfig filterConfig = new MockFilterConfig();
		filterConfig.addInitParameter("exceptionUris", "/rest/test.* /rest/auth.* /rest/login.*");

		filter.init(filterConfig);

		assertTrue(filter.isExceptionUri("/rest/test"));
		assertTrue(filter.isExceptionUri("/rest/auth"));
		assertTrue(filter.isExceptionUri("/rest/login"));
		assertFalse(filter.isExceptionUri("/rest/function"));
		assertFalse(filter.isExceptionUri("/rest/report"));
	}

	@Test
	public void testMissingAuthenticationHeader() throws ServletException, IOException {
		TokenAuthenticationFilter filter = new TokenAuthenticationFilter() {
			@Override
			protected TokenAuthenticator getTokenAuthenticator(ServletContext servletContext) {
				TokenAuthenticator authenticator = createStrictMock(TokenAuthenticator.class);
				expect(authenticator.isAuthenticated((String) anyObject())).andReturn(null).andReturn("abcdef")
						.andReturn("123456").andReturn(null);
				replay(authenticator);
				return authenticator;
			}
		};
		filter.init(new MockFilterConfig());

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain chain = new MockFilterChain();

		filter.doFilter(request, response, chain);
		assertEquals(403, response.getStatus());

		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
		filter.doFilter(request, response, chain);
		assertEquals(200, response.getStatus());
		assertEquals("abcdef", response.getHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER));
		
		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
		filter.doFilter(request, response, chain);
		assertEquals(200, response.getStatus());
		assertEquals("123456", response.getHeader(TokenAuthenticator.TOKEN_AUTHENTICATOR_HEADER));
		
		response = new MockHttpServletResponse();
		chain = new MockFilterChain();
		filter.doFilter(request, response, chain);
		assertEquals(403, response.getStatus());
	}

}

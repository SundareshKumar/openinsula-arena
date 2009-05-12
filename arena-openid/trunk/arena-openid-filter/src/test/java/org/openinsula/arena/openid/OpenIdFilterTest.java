package org.openinsula.arena.openid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Enumeration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;

public class OpenIdFilterTest {

	private static ServletTester tester = new ServletTester();

	@BeforeClass
	public static void before() throws Exception {
		tester.setContextPath("/context");
		tester.addFilter(OpenIdFilter.class, "/*", Handler.DEFAULT);
		tester.addServlet(DefaultServlet.class, "/*");
		tester.start();
	}

	@AfterClass
	public static void after() throws Exception {
		tester.stop();
	}

	@Test
	public void testDiscover() throws Exception {
		HttpTester request = new HttpTester();
		HttpTester response = new HttpTester();
		request.setMethod("GET");
		request.setURI("/context/hello/info");
		request.setVersion("HTTP/1.0");

		response.parse(tester.getResponses(request.generate()));

		assertTrue(response.getMethod() == null);
		assertEquals(302, response.getStatus());
		System.out.println(response.getReason());
		Enumeration enumeration = response.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String headerName = enumeration.nextElement().toString();
			System.out.println(headerName + ": " + response.getHeader(headerName));
		}
	}

}

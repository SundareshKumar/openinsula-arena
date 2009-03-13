package org.openinsula.arena.gwt.util.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestUtilsSuite extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("arena-gwt utils TEST SUITE");
		suite.addTestSuite(StringUtilsGwtTestCase.class);
		return suite;
	}
	
}

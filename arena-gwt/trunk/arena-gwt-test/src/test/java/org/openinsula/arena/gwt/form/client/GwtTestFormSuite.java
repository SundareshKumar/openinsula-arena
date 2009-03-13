package org.openinsula.arena.gwt.form.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestFormSuite extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("arena-gwt form TEST SUITE");
		suite.addTestSuite(FormSectionGwtTestCase.class);
		return suite;
	}
	
}

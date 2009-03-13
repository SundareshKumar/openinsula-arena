package org.openinsula.arena.gwt.form.theme.wufoo.client;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtTestWufooSuite extends GWTTestSuite {
	
	public static Test suite() {
		TestSuite suite = new TestSuite("arena-gwt form TEST SUITE");
		suite.addTestSuite(WufooFormSectionGwtTestCase.class);
		return suite;
	}
	
}

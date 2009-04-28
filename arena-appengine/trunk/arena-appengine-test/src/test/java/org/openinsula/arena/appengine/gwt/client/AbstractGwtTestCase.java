package org.openinsula.arena.appengine.gwt.client;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class AbstractGwtTestCase extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.openinsula.arena.appengine.gwt.GWT";
	}

}

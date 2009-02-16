package org.openinsula.arena.gwt.components.client.ui;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.TextBox;

// TODO
public class HTMLWidgetTestCase extends GWTTestCase {

	public void testClear() {
		HTMLWidget<DivElement> div = new HTMLWidget<DivElement>() {
		
			@Override
			protected DivElement createHTMLElement(final Document document) {
				return document.createDivElement();
			}
		};

		assertTrue(div.getWidgetCount() == 0);
		div.clear();
		assertTrue(div.getWidgetCount() == 0);
		
		div.add(new TextBox());
		assertTrue(div.getWidgetCount() == 1);
		
		div.clear();
		assertTrue(div.getWidgetCount() == 0);
	}

	public void testHTMLWidget() {
		fail("Not yet implemented");
	}

	public void testGetHTMLElement() {
		fail("Not yet implemented");
	}

	public void testCreateHTMLElement() {
		fail("Not yet implemented");
	}

	public void testInsertWidgetInt() {
		fail("Not yet implemented");
	}

	public void testInsertElementInt() {
		fail("Not yet implemented");
	}

	public void testAddFirstWidget() {
		fail("Not yet implemented");
	}

	public void testAddFirstElement() {
		fail("Not yet implemented");
	}

	public void testAddElement() {
		fail("Not yet implemented");
	}

	public void testRemoveElement() {
		fail("Not yet implemented");
	}

	public void testGetWidgetIndexElement() {
		fail("Not yet implemented");
	}

	public void testAddWidget() {
		fail("Not yet implemented");
	}

	public void testToWidget() {
		fail("Not yet implemented");
	}

	@Override
	public String getModuleName() {
		return "org.openinsula.arena.gwt.components.Components";
	}

}

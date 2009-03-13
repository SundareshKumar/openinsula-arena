package org.openinsula.arena.gwt.form.client;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestFormSection extends GWTTestCase {

	public void testConstructorWithRenderer() {
		FormSection section = new FormSection();
		assertNotNull(section.getRenderer());
	}

	public void testAttachRendererFormSectionRendererPropertyChangeSupport() {
	}

	public void testTitleString() {
	}

	public void testTitle() {
	}

	public void testSubtitleString() {
	}

	public void testSubtitle() {
	}

	public void testFormItems() {
	}

	public void testAddItem() {
	}

	public void testAddItemOnNewLine() {
	}

	public void testAddItemOnSameLine() {
	}

	public void testAddItemAfter() {
	}

	public void testAddItemOnNewLineAfter() {
	}

	public void testAddItemOnSameLineAfter() {
	}

	public void testAddAction() {
	}

	public void testRemove() {
	}

	@Override
	public String getModuleName() {
		return "br.com.openinsula.arena.gwt.form.Form";
	}

}

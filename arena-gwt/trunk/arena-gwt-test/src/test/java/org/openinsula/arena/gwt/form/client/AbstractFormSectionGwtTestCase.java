package org.openinsula.arena.gwt.form.client;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Renderer independent test case class. You must extends this with the underlying
 * FormSectionRenderer.
 * 
 * @author Eduardo Rebola
 */
public abstract class AbstractFormSectionGwtTestCase extends GWTTestCase {

	public void testConstructorWithRenderer() {
		FormSection section = new FormSection();
		assertNotNull(section.getRenderer());
	}

	public void testTitleProperty() {
		FormSection section = new FormSection();
		assertNull(section.title());
		
		String expected = "Title";
		
		section.title(expected);
		assertSame(expected, section.title());
		
		section.title(new String("Title"));
		assertSame(expected, section.title());
		
		onTitleChangeTest(section);
	}

	protected abstract void onTitleChangeTest(FormSection renderer);

	public void testSubtitleProperty() {
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

}

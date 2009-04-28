package org.openinsula.arena.appengine.gwt.client.forms;

import org.openinsula.arena.appengine.gwt.client.AbstractGwtTestCase;

/**
 * Renderer independent testcase class. You must extends this with the underlying
 * FormSectionRenderer implementation test.
 *
 * @author Eduardo Rebola
 */
// TODO finish
public abstract class AbstractFormSectionGwtTestCase extends AbstractGwtTestCase {

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

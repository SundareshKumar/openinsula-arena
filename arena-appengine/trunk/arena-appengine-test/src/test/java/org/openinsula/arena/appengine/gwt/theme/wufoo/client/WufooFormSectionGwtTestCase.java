package org.openinsula.arena.appengine.gwt.theme.wufoo.client;

import org.openinsula.arena.appengine.gwt.client.forms.FormSection;

public class WufooFormSectionGwtTestCase extends AbstractWufooGwtTestCase {

	@Override
	protected void onTitleChangeTest(final FormSection section) {
		WufooFormSectionRenderer wufoo = (WufooFormSectionRenderer) section.getRenderer();

		section.title("Title");
		assertEquals("Title", wufoo.headerTitleElement.get(false).getInnerHTML());

		section.title(null);
		assertNull(wufoo.headerTitleElement.get(false));
	}

}
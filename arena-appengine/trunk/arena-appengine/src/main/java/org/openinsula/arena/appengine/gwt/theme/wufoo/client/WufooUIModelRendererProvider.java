package org.openinsula.arena.appengine.gwt.theme.wufoo.client;

import org.openinsula.arena.appengine.gwt.client.forms.FormItemRenderer;
import org.openinsula.arena.appengine.gwt.client.forms.FormRenderer;
import org.openinsula.arena.appengine.gwt.client.forms.FormSectionRenderer;
import org.openinsula.arena.appengine.gwt.client.forms.UIModelRendererProviderImpl;

public class WufooUIModelRendererProvider extends UIModelRendererProviderImpl {


	public FormItemRenderer createFormItemRenderer() {
		return new WufooFormItemRenderer();
	}


	public FormRenderer createFormRenderer() {
		return new WufooFormRenderer();
	}


	public FormSectionRenderer createFormSectionRenderer() {
		return new WufooFormSectionRenderer();
	}

}

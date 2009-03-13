package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.form.client.FormItemRenderer;
import org.openinsula.arena.gwt.form.client.FormRenderer;
import org.openinsula.arena.gwt.form.client.FormSectionRenderer;
import org.openinsula.arena.gwt.form.client.UIModelRendererProviderImpl;
import org.openinsula.arena.gwt.form.client.WindowRenderer;

public class WufooUIModelRendererProvider extends UIModelRendererProviderImpl {

	@Override
	public FormItemRenderer createFormItemRenderer() {
		return new WufooFormItemRenderer();
	}

	@Override
	public FormRenderer createFormRenderer() {
		return new WufooFormRenderer();
	}

	@Override
	public FormSectionRenderer createFormSectionRenderer() {
		return new WufooFormSectionRenderer();
	}

	@Override
	public WindowRenderer createWindowRenderer() {
		return new WufooWindowRenderer();
	}

}

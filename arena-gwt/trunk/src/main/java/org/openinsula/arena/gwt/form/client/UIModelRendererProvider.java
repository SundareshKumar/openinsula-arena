package org.openinsula.arena.gwt.form.client;

import com.google.gwt.core.client.GWT;

public class UIModelRendererProvider {

	private static UIModelRendererProvider instance;
	
	private final UIModelRendererProviderImpl impl;
	
	protected UIModelRendererProvider() {
		impl = GWT.create(UIModelRendererProviderImpl.class);
	}
	
	public static UIModelRendererProvider get() {
		if (instance == null) {
			instance = new UIModelRendererProvider();
		}
		return instance;
	}
	
	public WindowRenderer createWindowRenderer() {
		return impl.createWindowRenderer();
	}
	
	public FormRenderer createFormRenderer() {
		return impl.createFormRenderer();
	}
	
	public FormSectionRenderer createFormSectionRenderer() {
		return impl.createFormSectionRenderer();
	}
	
	public FormItemRenderer createFormItemRenderer() {
		return impl.createFormItemRenderer();
	}
	
}

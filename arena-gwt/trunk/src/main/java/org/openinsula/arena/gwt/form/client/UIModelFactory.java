package org.openinsula.arena.gwt.form.client;

import com.google.gwt.core.client.GWT;

public class UIModelFactory {
	
	private static UIModelFactory instance;
	
	private final UIModelFactory impl;
	
	protected UIModelFactory() {
		impl = GWT.create(UIModelFactoryImpl.class);
	}
	
	public static UIModelFactory get() {
		if (instance == null) {
			instance = new UIModelFactory();
		}
		return instance;
	}
	
	public Window createWindow() {
		return impl.createWindow();
	}

	public Form createForm() {
		return impl.createForm();
	}

	public FormSection createFormSection() {
		return impl.createFormSection();
	}

	public FormItem createFormItem() {
		return impl.createFormItem();
	}
	
}

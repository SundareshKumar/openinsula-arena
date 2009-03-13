package org.openinsula.arena.gwt.components.client.ui.form;

public interface UIBean<T> {

	void setValue(T value);
	
	T getValue();
	
	void setLabelFunction(LabelFunction labelFunction);
	
	LabelFunction getLabelFunction();
	
}

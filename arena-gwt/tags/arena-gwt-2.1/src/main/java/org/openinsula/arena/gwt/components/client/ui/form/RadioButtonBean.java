package org.openinsula.arena.gwt.components.client.ui.form;

import com.google.gwt.user.client.ui.RadioButton;

public class RadioButtonBean<T> extends RadioButton implements UIBean<T> {

	private T value;
	
	private LabelFunction labelFunction;

	// Inherited Constructors
	
	public RadioButtonBean(String name) {
		super(name);
	}
	
	// new Constructors
	
	public RadioButtonBean(String name, T bean, LabelFunction labelFunction) {
		super(name, labelFunction.getLabel(bean), labelFunction.asHTML());
		setLabelFunction(labelFunction);
		setValue(bean);
	}
	
	public RadioButtonBean(String name, T bean) {
		this(name, bean, new ToStringLabelFunction());
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	public void setLabelFunction(LabelFunction labelFunction) {
		this.labelFunction = labelFunction;
	}
	
	public LabelFunction getLabelFunction() {
		return labelFunction;
	}

	// Builder for Enum
	
	public static <E extends Enum<?>> RadioButtonBean<E> createFromEnum(String name, E enumConstant) {
		return new RadioButtonBean<E>(name, enumConstant);
	}
	
	@SuppressWarnings("unchecked")
	public static <E extends Enum<?>> RadioButtonBean<E>[] createFromEnum(String name, Class<E> enumType) {
		E[] enumConstants = enumType.getEnumConstants();
		RadioButtonBean<E>[] result = new RadioButtonBean[enumConstants.length]; 
		
		for (int i = 0; i < enumConstants.length; i++) {
			result[i] = createFromEnum(name, enumConstants[i]);
		}
		
		return result;
	}

}

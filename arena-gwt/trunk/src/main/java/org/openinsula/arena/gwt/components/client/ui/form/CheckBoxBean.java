package org.openinsula.arena.gwt.components.client.ui.form;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.CheckBox;

public class CheckBoxBean<T> extends CheckBox implements UIBean<T> {

	private T value;

	private LabelFunction labelFunction;

	// Inherited Constructors

	public CheckBoxBean() {
		super();
	}

	public CheckBoxBean(Element elem) {
		super(elem);
	}

	// new Constructors

	public CheckBoxBean(T bean, LabelFunction labelFunction) {
		super(labelFunction.getLabel(bean), labelFunction.asHTML());
		setValue(bean);
		setLabelFunction(labelFunction);
	}

	public CheckBoxBean(T bean) {
		this(bean, new ToStringLabelFunction());
	}

	public LabelFunction getLabelFunction() {
		return labelFunction;
	}

	public T getValue() {
		return value;
	}

	public void setLabelFunction(LabelFunction labelFunction) {
		this.labelFunction = labelFunction;
	}

	public void setValue(T value) {
		this.value = value;
	}

	// Builder for Enum

	public static <E extends Enum<?>> CheckBoxBean<E> createFromEnum(E enumConstant) {
		return new CheckBoxBean<E>(enumConstant);
	}
	
	@SuppressWarnings("unchecked")
	public static <E extends Enum<?>> CheckBoxBean<E>[] createFromEnum(Class<E> enumType) {
		E[] enumConstants = enumType.getEnumConstants();
		CheckBoxBean<E>[] result = new CheckBoxBean[enumConstants.length]; 
		
		for (int i = 0; i < enumConstants.length; i++) {
			result[i] = createFromEnum(enumConstants[i]);
		}
		
		return result;
	}

}

package org.openinsula.arena.gwt.form.client;

import org.openinsula.arena.gwt.components.client.beans.PropertyChangeCallback;
import org.openinsula.arena.gwt.components.client.beans.PropertyChangeSupport;
import org.openinsula.arena.gwt.form.client.validator.FormItemValidator;
import org.openinsula.arena.gwt.form.client.validator.ValidationCallback;

import com.google.gwt.user.client.ui.Widget;

public class FormItem extends AbstractUIModel<FormItemRenderer> {

	public enum Size {
		SMALL, MEDIUM, LARGE
	}

	static final String LABEL_PROPERTY = "label";

	static final String REQUIRED_PROPERTY = "required";

	static final String TIP_PROPERTY = "tip";

	static final String SIZE_PROPERTY = "size";

	static final String WIDGET_PROPERTY = "widget";

	static final String VALID_PROPERTY = "valid";

	static final String VALIDATION_MESSAGE_PROPERTY = "errorMessage";

	private FormItemValidator validator;

	private FormSection parentSection;

	public FormItem() {
		super(UIModelRendererProvider.get().createFormItemRenderer());
		size(Size.SMALL);
	}

	@Override
	protected void attachRenderer(final FormItemRenderer renderer, final PropertyChangeSupport model) {
		model.addPropertyChangeCallback(LABEL_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onLabelChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(REQUIRED_PROPERTY, new PropertyChangeCallback<Boolean>() {
			public void onChange(final Boolean oldValue, final Boolean newValue) {
				renderer.onRequiredChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(TIP_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onTipChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(WIDGET_PROPERTY, new PropertyChangeCallback<Widget> () {
			public void onChange(final Widget oldValue, final Widget newValue) {
				renderer.onWidgetChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(SIZE_PROPERTY, new PropertyChangeCallback<Size> () {
			public void onChange(final Size oldValue, final Size newValue) {
				renderer.onSizeChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(VALID_PROPERTY, new PropertyChangeCallback<Boolean>() {
			public void onChange(final Boolean oldValue, final Boolean newValue) {
				renderer.onValidChange(oldValue, newValue);
			}
		});
		
		model.addPropertyChangeCallback(VALIDATION_MESSAGE_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onValidationMessageChange(oldValue, newValue);
			}
		});
		
		
	}

	public FormItem label(final String label) {
		setProperty(LABEL_PROPERTY, label);
		return this;
	}

	public String label() {
		return getProperty(LABEL_PROPERTY);
	}

	public FormItem required(final boolean value) {
		setProperty(REQUIRED_PROPERTY, value);
		return this;
	}

	public boolean required() {
		Boolean property = getProperty(REQUIRED_PROPERTY);
		return property == null ? false : property;
	}

	public FormItem widget(final Widget widget) {
		setProperty(WIDGET_PROPERTY, widget);
		return this;
	}

	public Widget widget() {
		return getProperty(WIDGET_PROPERTY);
	}

	public FormItem size(final Size size) {
		setProperty(SIZE_PROPERTY, size);
		return this;
	}

	public Size size() {
		return getProperty(SIZE_PROPERTY);
	}

	public FormItem tip(final String hint) {
		setProperty(TIP_PROPERTY, hint);
		return this;
	}

	public String tip() {
		return getProperty(TIP_PROPERTY);
	}

	public FormItem parentSection(final FormSection section) {
		this.parentSection = section;
		return this;
	}

	public FormSection parentSection() {
		return parentSection;
	}

	public FormItem valid(final boolean isValid, final String message) {
		setProperty(VALID_PROPERTY, isValid);
		setProperty(VALIDATION_MESSAGE_PROPERTY, message);
		return this;
	}

	public FormItem validator(final FormItemValidator validator) {
		this.validator = validator;
		return this;
	}

	public FormItemValidator validator() {
		return validator;
	}

	public void validate(final ValidationCallback callback) {
		if (validator != null) {
			validator.validate(this, callback);
		}
	}

}

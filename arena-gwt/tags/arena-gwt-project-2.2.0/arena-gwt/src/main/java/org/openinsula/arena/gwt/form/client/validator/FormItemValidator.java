package org.openinsula.arena.gwt.form.client.validator;

import org.openinsula.arena.gwt.form.client.FormItem;
import org.openinsula.arena.gwt.util.client.FocusUtils;

import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.Widget;

public abstract class FormItemValidator implements AsyncValidator<FormItem> {

	private final ValidationCallback customCallback;

	public FormItemValidator() {
		this(null);
	}

	public FormItemValidator(final ValidationCallback customCallback) {
		this.customCallback = customCallback;
	}

	public final void validate(final FormItem formItem, final ValidationCallback callback) {
		CompositeValidationCallback compositeValidatorCallback = new CompositeValidationCallback(customCallback);

		compositeValidatorCallback.addCallback(new ValidationCallback() {
			public void onSuccess(final String message) {
				formItem.valid(true, message);
			}

			public void onFail(final String message, final Throwable error) {
				Widget widget = formItem.widget();
				
				if (widget instanceof HasFocus) {
					FocusUtils.setFocus((HasFocus) widget);
				}
				
				formItem.valid(false, message);
			}
		});

		compositeValidatorCallback.addCallback(callback);
		validateFormItem(formItem, compositeValidatorCallback);
	}

	protected abstract void validateFormItem(FormItem formItem, ValidationCallback callback);

	public static abstract class WidgetValidator<W extends Widget> implements AsyncValidator<W> {

		private final Class<W> widgetType;

		public WidgetValidator(final Class<W> widgetType) {
			this.widgetType = widgetType;
		}

		public final boolean matches(final Class<?> type) {
			Class<?> iterator = type;

			while (iterator != Widget.class) {
				if (widgetType == iterator) {
					return true;
				}
				iterator = iterator.getSuperclass();
			}

			return false;
		}

	}
	
}

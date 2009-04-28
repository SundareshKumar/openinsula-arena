package org.openinsula.arena.appengine.gwt.client.forms.validator;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem;
import org.openinsula.arena.appengine.gwt.client.util.FocusUtils;

import com.google.gwt.user.client.ui.Focusable;
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

				if (widget instanceof Focusable) {
					FocusUtils.setFocus((Focusable) widget);
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

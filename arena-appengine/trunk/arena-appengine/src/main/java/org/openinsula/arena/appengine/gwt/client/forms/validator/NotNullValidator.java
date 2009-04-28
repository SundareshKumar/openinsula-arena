package org.openinsula.arena.appengine.gwt.client.forms.validator;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.appengine.gwt.client.forms.FormItem;
import org.openinsula.arena.appengine.gwt.client.util.StringUtils;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

public class NotNullValidator extends FormItemValidator {

	private List<WidgetValidator<? extends Widget>> validatorList;

	private final String errorMessage;

	public NotNullValidator() {
		this("NULL value not allowed!");
	}

	public NotNullValidator(final String errorMessage) {
		createWidgetValidators();
		this.errorMessage = errorMessage;
	}

	@Override
	protected void validateFormItem(final FormItem formItem, final ValidationCallback callback) {
		Widget widget = formItem.widget();

		WidgetValidator<Widget> validator = getValidatorFor(widget);

		if (validator != null) {
			validator.validate(widget, callback);
		}
		else {
			throw new IllegalArgumentException("NotNullValidator does not support widget class '"
					+ widget.getClass().getName() + "'");
		}
	}

	@SuppressWarnings("unchecked")
	private WidgetValidator<Widget> getValidatorFor(final Widget widget) {

		for (WidgetValidator<?> widgetValidator : validatorList) {
			if (widgetValidator.matches(widget.getClass())) {
				return (WidgetValidator<Widget>) widgetValidator;
			}
		}

		return null;
	}

	private void createWidgetValidators() {
		validatorList = new ArrayList<WidgetValidator<? extends Widget>>();

		validatorList.add(new WidgetValidator<TextBoxBase>(TextBoxBase.class) {
			public void validate(final TextBoxBase widget, final ValidationCallback callback) {
				if (!StringUtils.hasText(widget.getText())) {
					callback.onFail(errorMessage, null);
				}
				else {
					callback.onSuccess(null);
				}
			}
		});

		validatorList.add(new WidgetValidator<ListBox>(ListBox.class) {
			public void validate(final ListBox widget, final ValidationCallback callback) {
				if (widget.getSelectedIndex() == -1) {
					callback.onFail(errorMessage, null);
				}
				callback.onSuccess(null);
			}
		});

	}

}

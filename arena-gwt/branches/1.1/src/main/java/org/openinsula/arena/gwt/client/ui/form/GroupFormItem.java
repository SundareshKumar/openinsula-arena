package org.openinsula.arena.gwt.client.ui.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openinsula.arena.gwt.client.ui.form.validator.FormItemValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * It's a FormItem that (can) contain more than one FocusWidget
 *
 * @see FormItem
 * @see FormItemWidgetWrapper
 * @param <T>
 */
public class GroupFormItem<T extends Widget> extends FormItem<T> {

	private boolean sameLine;

	private T[] widgets;

	public GroupFormItem(final String label, final T[] widgets) {
		this(label, widgets, null, false, false, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint) {
		this(label, widgets, hint, false, false, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint, boolean optional, boolean sameLine) {
		this(label, widgets, hint, optional, sameLine, null);
	}

	public GroupFormItem(final String label, final T[] widgets, final String hint, boolean optional, boolean sameLine,
			final String suffix) {
		this.widgets = widgets;
		createComponents(label, null, hint, optional, suffix);

		this.sameLine = sameLine;
		addFocusListener(new FocusListener() {
			public void onFocus(Widget sender) {
				setErrorMessage("");
				setValid(true);
				refresh();
			}
			public void onLostFocus(Widget sender) {
			}
		});
		super.pack();
	}

	@SuppressWarnings("unchecked")
	protected Widget getWidgetForPanel() {
		Panel panel;

		panel = !isSameLine() ? new VerticalPanel() : new HorizontalPanel();

		for (T w : widgets) {
			if (w instanceof FormItemValidator) {
				addFormItemValidator(w, (FormItemValidator<T>) w);
			}

			FormItemWidgetWrapper<T> wrapper = new FormItemWidgetWrapper<T>(w, getMainPanel(), getHint());
			panel.add(wrapper.getWidget());
		}

		if (widgets.length > 0) {
			final T localWidget = widgets[0];
			setLabelClickListener(localWidget);
		}

		return panel;
	}

	public boolean isSameLine() {
		return sameLine;
	}

	@Override
	public void clear() {
		for (T widget : widgets) {
			clearWidget(widget);
		}
		isNew = true;
		refresh();
	}

	@Override
	@Deprecated
	public T getWidget() {
		throw new UnsupportedOperationException("Use GroupFormItem.getWidgets() instead!");
	}

	public T[] getWidgets() {
		return widgets;
	}

	/*
	 * trecho experimental do validator
	 */

	@Override
	@Deprecated
	public void addFormItemValidator(FormItemValidator<T> validator) {
		throw new IllegalArgumentException("Use GroupFormItem.addFormItemValidator(T widget, FormItemValidator<T> validator) instead!");
	}

	@SuppressWarnings("unchecked")
	private Map<T, List<FormItemValidator>> validatorMap;

	@SuppressWarnings("unchecked")
	private Map<T, List<FormItemValidator>> validatorMap() {
		if (validatorMap == null) {
			validatorMap = new HashMap<T, List<FormItemValidator>>();
		}
		return validatorMap;
	}

	@SuppressWarnings("unchecked")
	public <W extends T> void addFormItemValidator(W widget, FormItemValidator<W> validator) {
		List<FormItemValidator> validators = validatorMap().get(widget);
		if (validators == null) {
			validators = new ArrayList<FormItemValidator>();
			validatorMap.put(widget, validators);
		}

		validators.add(validator);
	}

	@SuppressWarnings("unchecked")
	public boolean isValidated() {
		for (T widget : getWidgets()) {
			GWT.log("validando widget do tipo: " + widget.getClass().getName(), null);
			List<FormItemValidator> validators = validatorMap.get(widget);

			if (validators != null) {
				for (FormItemValidator validator : validators) {
					if (!validator.validate(widget)) {
						setErrorMessage(validator.getInvalidValueMessage());
						setValid(false);
						refresh();
						return false;
					}
				}
			}
		}
		setErrorMessage("");
		setValid(true);
		refresh();
		return true;
	}

}

package org.openinsula.arena.gwt.client.ui.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openinsula.arena.gwt.client.ui.form.validator.DefaultValidatorChainImpl;
import org.openinsula.arena.gwt.client.ui.form.validator.FormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorChain;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
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
				((FormItemValidator) w).setFormItem(this);
				addFormItemValidator(w, (FormItemValidator) w);
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


	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public void addFormItemValidator(FormItemValidator validator) {
		throw new IllegalArgumentException("Use GroupFormItem.addFormItemValidator(T widget, FormItemAsyncCallbackValidator<T> validator) instead!");
	}

	@SuppressWarnings("unchecked")
	private Map<T, ValidatorChain<T>> validatorMap;

	@SuppressWarnings("unchecked")
	private Map<T, ValidatorChain<T>> validatorMap() {
		if (validatorMap == null) {
			validatorMap = new HashMap<T, ValidatorChain<T>>();
		}
		return validatorMap;
	}

	@SuppressWarnings("unchecked")
	public <W extends T> void addFormItemValidator(W widget, FormItemValidator validator) {
		ValidatorChain<T> validatorChain = validatorMap().get(widget);
		if (validatorChain == null) {
			validatorChain = new DefaultValidatorChainImpl<T>();
			validatorMap().put(widget, validatorChain);
		}

		validatorChain.addValidator(validator);
	}

	@Override
	public void validate(ValidatorAction action) {
		Set<T> keySet = validatorMap().keySet();
		Iterator<T> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			T key = keyIterator.next();
			ValidatorChain<T> chain = validatorMap().get(key);
			chain.validate(key, action);
		}
	}

	@Override
	public void addFocusListener(FocusListener listener) {
		for (T widget : getWidgets()) {
			if (widget instanceof SourcesFocusEvents) {
				((SourcesFocusEvents) widget).addFocusListener(listener);
			}
		}
	}

	@Override
	public int getTabIndex() {
		T widget = getWidgets()[0];
		if (widget instanceof HasFocus) {
			return ((HasFocus) widget).getTabIndex();
		}

		return super.getTabIndex();
	}

	@Override
	public void setTabIndex(int index) {
		T widget = getWidgets()[0];
		if (widget instanceof HasFocus) {
			((HasFocus) widget).setTabIndex(index);
		} else {
			super.setTabIndex(index);
		}
	}
}

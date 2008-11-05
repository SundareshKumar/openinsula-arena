package org.openinsula.arena.gwt.components.client.form;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractFormPanel extends FlowPanel implements Form, FormChangeListener {

	public static final String STYLE_CLASS_NAME = "Form";

	private NewFormProvider formModel;

	public AbstractFormPanel() {
	}

	public AbstractFormPanel(NewFormProvider formModel) {
		setForm(formModel);
	}

	{
		setStyleName(STYLE_CLASS_NAME);
	}

	public void setForm(NewFormProvider form) {
		if (form == null) {
			throw new IllegalArgumentException("'formModel' must not be null");
		}

		final NewFormProvider oldFormModel = this.formModel;

		if (EqualsUtils.isDifferent(oldFormModel, form)) {
			this.formModel = form;

			if (oldFormModel != null) {
				oldFormModel.removeFormListener(this);
			}

			form.addFormListener(this);

			formChanged(new FormEvent(form, FormEvent.NEW_FORM));
		}
	}

	public NewFormProvider getModel() {
		return formModel;
	}

}

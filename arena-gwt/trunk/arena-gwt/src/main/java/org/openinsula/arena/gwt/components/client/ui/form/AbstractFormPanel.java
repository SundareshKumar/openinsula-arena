package org.openinsula.arena.gwt.components.client.ui.form;

import org.openinsula.arena.gwt.components.client.ui.form.event.FormChangeListener;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormEvent;
import org.openinsula.arena.gwt.components.client.ui.utils.EqualsUtils;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractFormPanel extends FlowPanel implements Form, FormChangeListener {

	public static final String STYLE_CLASS_NAME = "Form";

	private FormModel formModel;

	public AbstractFormPanel() {
		this(null);
	}

	public AbstractFormPanel(FormModel formModel) {
		if (formModel != null) {
			setModel(formModel);
		}
		setStyleName(STYLE_CLASS_NAME);
	}

	public void setModel(FormModel formModel) {
		if (formModel == null) {
			throw new IllegalArgumentException("'formModel' must not be null");
		}

		final FormModel oldFormModel = this.formModel;

		if (EqualsUtils.isDifferent(oldFormModel, formModel)) {
			this.formModel = formModel;

			if (oldFormModel != null) {
				oldFormModel.removeFormListener(this);
			}

			formModel.addFormListener(this);

			formChanged(new FormEvent(formModel, FormEvent.NEW_FORM));
		}
	}

	public FormModel getModel() {
		return formModel;
	}

}

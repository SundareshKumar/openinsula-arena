package org.openinsula.arena.gwt.components.client.ui.form;

import org.openinsula.arena.gwt.components.client.ui.form.builder.FormPanelBuilder;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormChangeListener;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractFormPanel extends Composite implements Form, FormChangeListener {

	public static final String STYLE_CLASS_NAME = "Form";

	private FormPanelBuilder formPanelBuilder;

	public AbstractFormPanel() {
		this(null);
	}

	public AbstractFormPanel(FormModel formModel) {
		setFormPanelBuilder(new FormPanelBuilder(formModel));

		getFormPanelBuilder().setFormChangeListener(this);

		initWidget(getPanel());

		setStyleName(STYLE_CLASS_NAME);
	}

	public abstract Panel getPanel();

	public void setModel(FormModel formModel) {
		getFormPanelBuilder().setFormModel(formModel);
	}

	public FormModel getModel() {
		return getFormPanelBuilder().getFormModel();
	}

	public void setFormPanelBuilder(FormPanelBuilder formPanelBuilder) {
		this.formPanelBuilder = formPanelBuilder;
	}

	public FormPanelBuilder getFormPanelBuilder() {
		return formPanelBuilder;
	}

}

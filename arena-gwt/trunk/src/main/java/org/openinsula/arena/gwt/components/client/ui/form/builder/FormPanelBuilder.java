package org.openinsula.arena.gwt.components.client.ui.form.builder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.Paragraph;
import org.openinsula.arena.gwt.components.client.ui.Title;
import org.openinsula.arena.gwt.components.client.ui.UnorderedList;
import org.openinsula.arena.gwt.components.client.ui.form.Action;
import org.openinsula.arena.gwt.components.client.ui.form.ButtonsListItem;
import org.openinsula.arena.gwt.components.client.ui.form.FormItem;
import org.openinsula.arena.gwt.components.client.ui.form.FormModel;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormChangeListener;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormEvent;
import org.openinsula.arena.gwt.components.client.ui.form.event.impl.NullFormChangeListener;
import org.openinsula.arena.gwt.components.client.ui.form.field.SimpleField;
import org.openinsula.arena.gwt.components.client.ui.utils.EqualsUtils;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author João Galli
 * 
 */
public class FormPanelBuilder {

	private FormModel formModel;

	private FormChangeListener formChangeListener;

	private Title title;

	private UnorderedList fieldsUnorderedList;

	private Button primaryActionButton;

	private Paragraph errorParagraph;

	private List<Widget> widgetList = new LinkedList<Widget>();

	public FormPanelBuilder(FormModel formModel) {
		initComponents();

		if (formModel != null) {
			setFormModel(formModel);
		}
	}

	public void setFormModel(FormModel formModel) {
		if (formModel == null) {
			throw new IllegalArgumentException("'formModel' must not be null");
		}

		final FormModel oldFormModel = this.formModel;

		if (EqualsUtils.isDifferent(oldFormModel, formModel)) {
			this.formModel = formModel;

			if (oldFormModel != null) {
				oldFormModel.removeFormListener(getFormChangeListener());
			}

			formModel.addFormListener(getFormChangeListener());

			getFormChangeListener().formChanged(new FormEvent(formModel, FormEvent.NEW_FORM));
		}

		this.formModel = formModel;
	}

	public FormModel getFormModel() {
		return formModel;
	}

	public void formChanged(final FormEvent e) {
		switch (e.getType()) {
		case FormEvent.NEW_FORM:
			initNewForm();
			break;

		case FormEvent.TITLE:
			setTitleText(getFormModel().getTitle());
			break;

		case FormEvent.DESCRIPTION:
			setTitleDescription(getFormModel().getDescription());
			break;

		case FormEvent.ERROR_MESSAGE:
			setErrorMessage(e.getSource().getErrorMessage());
			break;

		case FormEvent.FORM_ITEMS_CHANGED:
			appendFormFields();
			break;
		}
	}

	private void setErrorMessage(final String errorMessage) {
		errorParagraph.setText(errorMessage);

		if (errorMessage == null || errorMessage.trim().length() == 0) {
			errorParagraph.setStyleName(null);
		}
		else {
			errorParagraph.setStyleName("ErrorParagraph");
		}
	}

	private void initComponents() {
		title = new Title();
		fieldsUnorderedList = new UnorderedList();
		errorParagraph = new Paragraph();

		widgetList.add(title);
		widgetList.add(errorParagraph);
		widgetList.add(fieldsUnorderedList);
	}

	private Widget getWidget(final FormItem formItem) {
		Widget widget = formItem.toWidget();

		if (widget == null) {
			if (formItem instanceof Widget) {
				widget = (Widget) formItem;
			}
			else if (formItem instanceof SimpleField) {
				widget = ((SimpleField) formItem).getComponent();
			}
		}

		return widget;
	}

	private void setTitleText(final String text) {
		title.setText(text);
	}

	private void setTitleDescription(final String text) {
		title.setDescription(text);
	}

	private void initNewForm() {
		setTitleText(getFormModel().getTitle());
		setTitleDescription(getFormModel().getDescription());
		setErrorMessage(null);
		appendFormFields();
	}

	private void appendFormFields() {
		final Iterator<FormItem> formItems = getFormModel().getFormItems();

		fieldsUnorderedList.clear();

		while (formItems.hasNext()) {
			final Widget widget = getWidget(formItems.next());

			if (widget != null) {
				fieldsUnorderedList.add(widget);
			}
		}

		fieldsUnorderedList.add(createButtonsPane());
	}

	private ButtonsListItem createButtonsPane() {
		final Iterator<Action> secondaryActions = getFormModel().getSecondaryActions();
		final ButtonsListItem buttonsListItem = new ButtonsListItem();

		buttonsListItem.add(createButtonOrHyperlink(getFormModel().getPrimaryAction()));

		if (secondaryActions != null) {
			while (secondaryActions.hasNext()) {
				buttonsListItem.add(createHyperlink(secondaryActions.next()));
			}
		}

		return buttonsListItem;
	}

	private Widget createButtonOrHyperlink(final Action action) {
		final String historyToken = action.getHistoryToken();

		if (historyToken == null || historyToken.trim().length() == 0) {
			final Button button = createButton(action);
			primaryActionButton = button;
			return button;
		}
		else {
			return createHyperlink(action);
		}
	}

	private Button createButton(final Action action) {
		final Button button = new Button();

		button.setText(action.getText());

		for (final ClickListener clickListener : action.getClickListeners()) {
			button.addClickListener(clickListener);
		}

		return button;
	}

	private Widget createHyperlink(final Action action) {
		Widget link = null;
		final ClickListener[] clickListeners = action.getClickListeners();
		final String historyToken = action.getHistoryToken();

		if (historyToken == null || historyToken.isEmpty()) {
			link = new Label(action.getText());

			link.setStyleName("gwt-Hyperlink");
		}
		else {
			link = new Hyperlink(action.getText(), historyToken);
		}

		for (final ClickListener clickListener : clickListeners) {
			((SourcesClickEvents) link).addClickListener(clickListener);
		}
		return link;
	}

	public void setFormChangeListener(FormChangeListener formChangeListener) {
		this.formChangeListener = formChangeListener;
	}

	public FormChangeListener getFormChangeListener() {
		if (formChangeListener == null) {
			formChangeListener = new NullFormChangeListener();
		}
		return formChangeListener;
	}

	public void primaryActionButtonClick() {
		primaryActionButton.click();
	}

	public List<Widget> getWidgetList() {
		initComponents();

		return widgetList;
	}

}

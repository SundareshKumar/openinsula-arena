package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.Iterator;

import org.openinsula.arena.gwt.components.client.ui.Paragraph;
import org.openinsula.arena.gwt.components.client.ui.Title;
import org.openinsula.arena.gwt.components.client.ui.UnorderedList;
import org.openinsula.arena.gwt.components.client.ui.form.event.FormEvent;
import org.openinsula.arena.gwt.components.client.ui.form.field.SimpleField;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultFormPanel extends AbstractFormPanel {

	private Title title;

	private UnorderedList fieldsUnorderedList;

	private Button primaryActionButton;

	private Paragraph errorParagraph;

	public DefaultFormPanel() {
		this(null);
	}

	// FIXME Melhorar isso!
	public DefaultFormPanel(final FormModel formModel) {
		super();
		initComponents();

		if (formModel != null) {
			setModel(formModel);
		}
	}

	public Widget toWidget() {
		return this;
	}

	public void formChanged(final FormEvent e) {
		switch (e.getType()) {
		case FormEvent.NEW_FORM:
			initNewForm();
			break;

		case FormEvent.TITLE:
			setTitleText(getModel().getTitle());
			break;

		case FormEvent.DESCRIPTION:
			setTitleDescription(getModel().getDescription());
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

		add(title);
		add(errorParagraph);
		add(fieldsUnorderedList);
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
		setTitleText(getModel().getTitle());
		setTitleDescription(getModel().getDescription());
		setErrorMessage(null);
		appendFormFields();
	}

	private void appendFormFields() {
		final Iterator<FormItem> formItems = getModel().getFormItems();

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
		final Iterator<Action> secondaryActions = getModel().getSecondaryActions();
		final ButtonsListItem buttonsListItem = new ButtonsListItem();

		buttonsListItem.add(createButtonOrHyperlink(getModel().getPrimaryAction()));

		if (secondaryActions != null) {
			while (secondaryActions.hasNext()) {
				buttonsListItem.add(createHyperlink(secondaryActions.next()));
			}
		}

		return buttonsListItem;
	}

	public void doPrimaryAction() {
		primaryActionButton.click();
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

}

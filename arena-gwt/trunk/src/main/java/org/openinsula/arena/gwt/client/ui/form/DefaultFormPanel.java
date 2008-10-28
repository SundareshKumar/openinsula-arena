package org.openinsula.arena.gwt.client.ui.form;

import java.util.Iterator;

import org.openinsula.arena.gwt.client.ui.Paragraph;
import org.openinsula.arena.gwt.client.ui.Title;
import org.openinsula.arena.gwt.client.ui.UnorderedList;
import org.openinsula.arena.gwt.client.ui.form.field.SimpleField;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasText;
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
	}

	public DefaultFormPanel(FormModel formModel) {
		super(formModel);
	}

	{
		initComponents();

		sinkEvents(Event.ONKEYPRESS);
	}

	public Widget toWidget() {
		return this;
	}

	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);

		if (DOM.eventGetType(event) == Event.ONKEYPRESS) {
			switch (event.getKeyCode()) {
			case 13:
				primaryActionButton.click();
				break;
			}
		}
	}

	public void formChanged(FormEvent e) {
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

	private void setErrorMessage(String errorMessage) {
		if (errorParagraph == null) {
			errorParagraph = new Paragraph();

			insert(errorParagraph, 2);
		}

		errorParagraph.setText(errorMessage);
	}

	private void initComponents() {
		title = new Title();
		fieldsUnorderedList = new UnorderedList();

		add(title);
		add(fieldsUnorderedList);
	}

	private Widget getWidget(FormItem formItem) {
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

	private void setTitleText(String text) {
		title.setText(text);
	}

	private void setTitleDescription(String text) {
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

	private Widget createButtonOrHyperlink(Action action) {
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

	private Button createButton(Action action) {
		final Button button = new Button();

		button.setText(action.getText());

		for (final ClickListener clickListener : action.getClickListeners()) {
			button.addClickListener(clickListener);
		}

		return button;
	}

	private Widget createHyperlink(Action action) {
		Widget link = null;
		final ClickListener[] clickListeners = action.getClickListeners();
		final String historyToken = action.getHistoryToken();

		if (historyToken == null || historyToken.isEmpty()) {
			link = new Label();

			link.setStyleName("link");
		}
		else {
			link = new Hyperlink();

			((Hyperlink) link).setTargetHistoryToken(historyToken);
		}

		((HasText) link).setText(action.getText());

		for (final ClickListener clickListener : clickListeners) {
			((SourcesClickEvents) link).addClickListener(clickListener);
		}

		return link;
	}

}

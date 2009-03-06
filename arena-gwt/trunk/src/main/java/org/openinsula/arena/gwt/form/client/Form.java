package org.openinsula.arena.gwt.form.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openinsula.arena.gwt.components.client.beans.PropertyChangeCallback;
import org.openinsula.arena.gwt.components.client.beans.PropertyChangeSupport;
import org.openinsula.arena.gwt.form.client.validator.CompositeFormItemValidator;
import org.openinsula.arena.gwt.form.client.validator.ValidationCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;

public class Form extends AbstractUIModel<FormRenderer> {

	private static final String TITLE_PROPERTY = "title";

	private static final String SUBTITLE_PROPERTY = "subtitle";

	private static final String PRIMARY_ACTION_PROPERTY = "primaryAction";

	private List<FormSection> sectionList;

	private List<Action> secondaryActionList;

	public Form() {
		setRenderer(UIModelRendererProvider.get().createFormRenderer());
	}
	
	@Override
	protected void attachRenderer(final FormRenderer renderer, final PropertyChangeSupport model) {
		model.addPropertyChangeCallback(TITLE_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onTitleChange(oldValue, newValue);
			}
		});

		model.addPropertyChangeCallback(SUBTITLE_PROPERTY, new PropertyChangeCallback<String>() {
			public void onChange(final String oldValue, final String newValue) {
				renderer.onSubtitleChange(oldValue, newValue);
			}
		});

		model.addPropertyChangeCallback(PRIMARY_ACTION_PROPERTY, new PropertyChangeCallback<Action>() {
			public void onChange(final Action oldValue, final Action newValue) {
				renderer.onPrimaryActionChange(oldValue, newValue);
			}
		});
	}

	public Form title(final String title) {
		setProperty(TITLE_PROPERTY, title);
		return this;
	}

	public String title() {
		return getProperty(TITLE_PROPERTY);
	}

	public Form subtitle(final String subtitle) {
		setProperty(SUBTITLE_PROPERTY, subtitle);
		return this;
	}

	public String subtitle() {
		return getProperty(SUBTITLE_PROPERTY);
	}

	public Form addSection(final FormSection section) {
		if (section != null) {
			if (sectionList == null) {
				sectionList = new ArrayList<FormSection>();
			}

			if (sectionList.add(section)) {
				getRenderer().onFormSectionAdded(sections(), section);
			}
		}
		return this;
	}
	
	public Form removeSection(final FormSection section) {
		if (section != null) {
			if (sectionList != null && sectionList.remove(section)) {
				getRenderer().onFormSectionRemoved(sectionList, section);
			}
		}
		return this;
	}

	public List<FormSection> sections() {
		if (sectionList == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(sectionList);
	}

	public Form primaryAction(final Action action) {
		Command validationCommand = new Command() {
			public void execute() {
				CompositeFormItemValidator validator = new CompositeFormItemValidator();

				for (FormSection section : sections()) {
					List<FormItem> formItems = section.formItems();
					for (FormItem item : formItems) {
						validator.addFormItemWithValidator(item);
					}
				}

				validator.validate(null, new ValidationCallback() {

					public void onSuccess(final String message) {
						action.execute();
					}

					public void onFail(final String message, final Throwable error) {
						if (!GWT.isScript()) {
							GWT.log(message, error);
						}
					}
				});
			}
		};
		
		Action validatedAction = action.clone();
		validatedAction.command(validationCommand);

		setProperty(PRIMARY_ACTION_PROPERTY, validatedAction);
		return this;
	}

	public Form addSecondaryAction(final Action action) {
		if (action != null) {

			if (secondaryActionList == null) {
				secondaryActionList = new ArrayList<Action>();
			}

			if (secondaryActionList.add(action)) {
				getRenderer().onSecondaryActionAdded(action, secondaryActionList.indexOf(action));
			}
		}
		return this;
	}

}

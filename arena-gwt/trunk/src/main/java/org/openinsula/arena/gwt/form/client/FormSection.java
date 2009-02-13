package org.openinsula.arena.gwt.form.client;

import java.util.Collections;
import java.util.List;

import org.openinsula.arena.gwt.components.client.beans.PropertyChangeCallback;
import org.openinsula.arena.gwt.components.client.beans.PropertyChangeSupport;

public class FormSection extends AbstractUIModel<FormSectionRenderer> {

	static final String TITLE_PROPERTY = "title";

	static final String SUBTITLE_PROPERTY = "subtitle";

	public enum Position {
		INLINE, NEW_LINE
	}

	private List<FormItem> itemList;

	FormSection(final FormSectionRenderer renderer) {
		setRenderer(renderer);
	}

	@Override
	protected void attachRenderer(final FormSectionRenderer renderer, final PropertyChangeSupport model) {
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
	}

	public FormSection title(final String title) {
		setProperty(TITLE_PROPERTY, title);
		return this;
	}

	public String title() {
		return getProperty(TITLE_PROPERTY);
	}

	public FormSection subtitle(final String subtitle) {
		setProperty(SUBTITLE_PROPERTY, subtitle);
		return this;
	}

	public String subtitle() {
		return getProperty(SUBTITLE_PROPERTY);
	}

	public List<FormItem> formItems() {
		return Collections.unmodifiableList(itemList);
	}

	public FormSection addItem(final FormItem formItem) {
		return addItemOnNewLine(formItem);
	}

	public FormSection addItemOnNewLine(final FormItem formItem) {
		addFormItem(formItem, Position.NEW_LINE);
		return this;
	}

	public FormSection addItemOnSameLine(final FormItem formItem) {
		addFormItem(formItem, Position.INLINE);
		return this;
	}

	private void addFormItem(final FormItem formItem, final Position position) {
		if (itemList.add(formItem)) {
			getRenderer().onFormItemAdded(formItem, itemList.indexOf(formItem), position);
		}
	}

	public FormSection remove(final FormItem formItem) {
		int idx = itemList.indexOf(formItem);

		if (itemList.remove(formItem)) {
			getRenderer().onFormItemRemoved(formItem, idx);
		}

		return this;
	}

}

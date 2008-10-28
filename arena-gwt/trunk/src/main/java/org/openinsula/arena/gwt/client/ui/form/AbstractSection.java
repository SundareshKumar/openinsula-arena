package org.openinsula.arena.gwt.client.ui.form;

import java.util.LinkedList;
import java.util.List;

import org.openinsula.arena.gwt.client.ui.form.field.FieldFactory;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractSection implements Section {

	private final SectionBreak sectionBreak;

	public AbstractSection() {
		this(null, null);
	}

	public AbstractSection(String title) {
		this(title, null);
	}

	public AbstractSection(String title, String description) {
		sectionBreak = FieldFactory.sectionBreak(title, description);
	}

	protected abstract List<FormItem> getSectionFormItems();

	public final List<FormItem> getFormItems() {
		final List<FormItem> formItems = new LinkedList<FormItem>();

		formItems.add(sectionBreak);
		formItems.addAll(getSectionFormItems());

		return formItems;
	}

	public void setDescription(String description) {
		sectionBreak.setDescription(description);
	}

	public void setText(String text) {
		sectionBreak.setText(text);
	}

}

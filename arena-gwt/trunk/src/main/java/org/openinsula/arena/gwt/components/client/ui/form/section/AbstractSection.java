package org.openinsula.arena.gwt.components.client.ui.form.section;

import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.FormItem;
import org.openinsula.arena.gwt.components.client.ui.form.builder.SectionBuilder;

/**
 * @author Lucas K Mogari
 */
public abstract class AbstractSection implements Section {

	private SectionBuilder sectionBuilder;

	public AbstractSection() {
		this(null, null);
	}

	public AbstractSection(String title) {
		this(title, null);
	}

	public AbstractSection(String title, String description) {
		sectionBuilder = new SectionBuilder(title, description);
	}

	protected abstract List<FormItem> getSectionFormItems();

	public final List<FormItem> getFormItems() {
		sectionBuilder.append(getSectionFormItems());
		
		return sectionBuilder.getFormItems();
	}

	public void setDescription(String description) {
		sectionBuilder.setDescription(description);
	}

	public void setText(String text) {
		sectionBuilder.setText(text);
	}

}

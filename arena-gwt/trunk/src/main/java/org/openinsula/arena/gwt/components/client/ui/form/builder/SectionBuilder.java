package org.openinsula.arena.gwt.components.client.ui.form.builder;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.FieldFactory;
import org.openinsula.arena.gwt.components.client.ui.form.FormItem;
import org.openinsula.arena.gwt.components.client.ui.form.section.Section;
import org.openinsula.arena.gwt.components.client.ui.form.section.SectionBreak;

/**
 * @author João Galli
 * 
 */
public class SectionBuilder {

	private SectionBreak sectionBreak;

	private List<FormItem> formItems = new ArrayList<FormItem>();

	public SectionBuilder() {
	}

	public SectionBuilder(String title, String description) {
		sectionBreak = FieldFactory.sectionBreak(title, description);
		formItems.add(sectionBreak);
	}

	public final List<FormItem> getFormItems() {
		return formItems;
	}

	public Section getSection() {
		return new Section() {
			public void setDescription(String description) {
				SectionBuilder.this.setDescription(description);
			}

			public void setText(String text) {
				SectionBuilder.this.setText(text);
			}

			public List<FormItem> getFormItems() {
				return SectionBuilder.this.getFormItems();
			}
		};
	}

	public SectionBuilder append(FormItem formItem, FormItem... items) {
		formItems.add(formItem);

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				FormItem item = items[i];
				formItems.add(item);
			}
		}

		return this;
	}

	public SectionBuilder append(List<FormItem> sectionFormItems) {
		formItems.addAll(sectionFormItems);
		return this;
	}

	public SectionBuilder setDescription(String description) {
		sectionBreak.setDescription(description);
		return this;
	}

	public SectionBuilder setText(String text) {
		sectionBreak.setText(text);
		return this;
	}

}

package org.openinsula.arena.gwt.components.client.form;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas K Mogari
 */
public class SectionedFormModel extends DefaultFormModel {

	private final List<Section> sections = new LinkedList<Section>();

	public SectionedFormModel() {
	}

	public SectionedFormModel(String title, String description) {
		super(title, description);
	}

	public SectionedFormModel(String title) {
		super(title);
	}

	public void appendSection(Section section) {
		super.appendFormItems(section);

		sections.add(section);
	}

	public void removeSection(Section section) {
		for (final FormItem formItem : section.getFormItems()) {
			removeFormItem(formItem);
		}

		sections.remove(section);
	}

	@Override
	public void appendFormItems(FormItemProvider formItemProvider) {
		if (formItemProvider instanceof Section) {
			appendSection((Section) formItemProvider);
		}
		else {
			super.appendFormItems(formItemProvider);
		}
	}

	public List<Section> getSections() {
		return sections;
	}

}

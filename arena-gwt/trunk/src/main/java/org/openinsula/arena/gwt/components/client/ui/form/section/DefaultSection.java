package org.openinsula.arena.gwt.components.client.ui.form.section;

import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.FormItem;
import org.openinsula.arena.gwt.components.client.ui.form.FormItemListBuilder;

/**
 * @author João Galli
 *
 */
public abstract class DefaultSection extends AbstractSection {

	
	public abstract void buildSection(FormItemListBuilder builder);
	
	@Override
	protected List<FormItem> getSectionFormItems() {
		FormItemListBuilder builder = new FormItemListBuilder();
		buildSection(builder);
		return builder.toList();
	}

}

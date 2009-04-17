package org.openinsula.arena.gwt.client.ui.form;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The default form Builder. 
 * <p>
 * This layout only allows one {@link FormItem} or GroupFormItem per line.
 * 
 * @see FormItem
 * @see FormItemWidgetWrapper
 * @see GroupFormItem
 */
public class FormBuilder {

	private List<FormItem<?>> items;

	public FormBuilder() {
		createComponents();
	}

	private void createComponents() {
		items = new LinkedList<FormItem<?>>();
	}

	public Panel toPanel() {
		return new FormPanelWrapper().getWrapper();
	}

	public FormBuilder add(FormItem<?> item) {
		items.add(item);
		return this;
	}
	
	/**
	 * Wrapper class for rendering the items
	 *
	 */
	class FormPanelWrapper {

		Panel mainPanel;
		
		FormPanelWrapper() {
			mainPanel = new VerticalPanel();

			for (FormItem<?> item : items) {
				mainPanel.add(item);
			}

		}

		Panel getWrapper() {
			return mainPanel;
		}
	}

}

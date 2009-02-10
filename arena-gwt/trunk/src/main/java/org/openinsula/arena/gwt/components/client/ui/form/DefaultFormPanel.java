package org.openinsula.arena.gwt.components.client.ui.form;

import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.form.event.FormEvent;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class DefaultFormPanel extends AbstractFormPanel {

	public DefaultFormPanel() {
		this(null);
	}

	public DefaultFormPanel(final FormModel formModel) {
		super(formModel);

		List<Widget> widgetList = getFormPanelBuilder().getWidgetList();

		Panel panel = (Panel) this.getWidget();

		for (Widget widget : widgetList) {
			panel.add(widget);
		}
	}

	@Override
	public Panel getPanel() {
		if (this.getWidget() == null) {
			return new FlowPanel();
		}
		else {
			return (Panel) this.getWidget();
		}
	}

	public Widget toWidget() {
		return this;
	}

	public void formChanged(final FormEvent e) {
		getFormPanelBuilder().formChanged(e);
	}

	public void doPrimaryAction() {
		getFormPanelBuilder().primaryActionButtonClick();
	}

}

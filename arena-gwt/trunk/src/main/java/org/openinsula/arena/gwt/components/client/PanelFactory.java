package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public abstract class PanelFactory {

	public static FlowPanel createPanel(String name, String description) {
		return new NamedPanel(name, description);
	}

	public static FlowPanel createPanel(String name) {
		return new NamedPanel(name);
	}

	public static Widget createTopLabelPlacementFormPanel() {
		return null;
	}

	public static Widget createLeftLabelPlacementFormPanel() {
		return null;
	}

	public static Widget createRightLabelPlacementFormPanel() {
		return null;
	}

}

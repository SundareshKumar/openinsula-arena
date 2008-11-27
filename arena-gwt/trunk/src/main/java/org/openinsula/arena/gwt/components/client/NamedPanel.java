package org.openinsula.arena.gwt.components.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class NamedPanel extends FlowPanel {

	private final SimpleTitle simpleTitle;

	public NamedPanel() {
		this(null, null);
	}

	public NamedPanel(String name) {
		this(name, null);

	}

	public NamedPanel(String name, String description) {
		simpleTitle = new SimpleTitle(name, description);

		add(simpleTitle);

		setStyleName("arena-Panel");
	}

	public void setName(String name) {
		simpleTitle.setText(name);
	}

	public void setDescription(String description) {
		simpleTitle.setDescription(description);
	}

	@Override
	public boolean remove(int index) {
		return super.remove(++index);
	}

	@Override
	public void insert(Widget w, int beforeIndex) {
		super.insert(w, ++beforeIndex);
	}

}

package org.openinsula.arena.gwt.components.client;

import org.openinsula.arena.gwt.components.client.form.Title;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lucas K Mogari
 */
public class NamedPanel extends FlowPanel {

	private final Title title;

	public NamedPanel() {
		this(null, null);
	}

	public NamedPanel(String name) {
		this(name, null);

	}

	public NamedPanel(String name, String description) {
		title = new Title(name, description);

		add(title);

		setStyleName("arena-Panel");
	}

	public void setName(String name) {
		title.setText(name);
	}

	public void setDescription(String description) {
		title.setDescription(description);
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

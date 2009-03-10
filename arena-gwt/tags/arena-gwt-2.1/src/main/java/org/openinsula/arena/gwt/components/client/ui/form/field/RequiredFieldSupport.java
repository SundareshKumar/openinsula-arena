package org.openinsula.arena.gwt.components.client.ui.form.field;

import org.openinsula.arena.gwt.components.client.ui.Label;
import org.openinsula.arena.gwt.components.client.ui.Strong;

public class RequiredFieldSupport implements RequiredField {

	private final Label label;
	
	private boolean requiredStatus;

	private Strong _required;

	public RequiredFieldSupport(final Label label) {
		assert label != null : "Label required!";
		this.label = label;
	}

	public boolean isRequired() {
		return requiredStatus;
	}

	public void setRequired(final boolean value) {
		if (requiredStatus != value) {
			if (value) {
				label.insert(required(), 0);
			}
			else {
				label.remove(required());
			}
			requiredStatus = value;
		}
	}
	
	private Strong required() {
		if (_required == null) {
			_required = new Strong();
			_required.setText("*");
		}
		return _required;
	}

}

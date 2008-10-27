package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Eduardo V. Bruno
 *
 */
public class ApplicationViewWithLogo extends ApplicationView {

	private AbstractImagePrototype imagePrototype;
	
	public ApplicationViewWithLogo(AbstractImagePrototype imagePrototype) {
		this.imagePrototype = imagePrototype;
	}

	@Override
	protected Widget getApplicationTitle() {
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		wrapper.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		wrapper.add(imagePrototype.createImage());
		
		return wrapper;
	}

}

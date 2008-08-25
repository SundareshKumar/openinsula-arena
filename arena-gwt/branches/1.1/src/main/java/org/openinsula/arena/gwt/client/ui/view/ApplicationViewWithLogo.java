package org.openinsula.arena.gwt.client.ui.view;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ApplicationViewWithLogo extends ApplicationView {

	public enum LogoPosition {
		TOP, RIGHT, BOTTOM, LEFT
	}
	
	private AbstractImagePrototype imagePrototype;
	
	private LogoPosition logoPosition;
	
	public ApplicationViewWithLogo(String title) {
		super(title);
	}
	

	@Override
	protected Widget getApplicationTitle() {
		
		Label title = (Label) super.getApplicationTitle();
		
		Panel panel = null;
		Image image = imagePrototype.createImage();
		
		if (logoPosition == LogoPosition.BOTTOM){
			panel = new VerticalPanel();
			panel.add(title);
			panel.add(image);
		} else if (logoPosition == LogoPosition.TOP) {
			panel = new VerticalPanel();
			panel.add(image);
			panel.add(title);
		}else if (logoPosition == LogoPosition.LEFT) {
			panel = new HorizontalPanel();
			panel.add(image);
			panel.add(title);
		}else if (logoPosition == LogoPosition.RIGHT) {
			panel = new HorizontalPanel();
			panel.add(title);
			panel.add(image);
		}

		panel.getElement().setPropertyString("border", "1px solid #000");
		panel.getElement().setPropertyString("vertical-align", "top");
		return panel;
	}
	
	public void setLogo(AbstractImagePrototype imagePrototype, LogoPosition logoPosition) {
		setImagePrototype(imagePrototype);
		setLogoPosition(logoPosition);
	}

	public void setImagePrototype(AbstractImagePrototype imagePrototype) {
		this.imagePrototype = imagePrototype;
	}
	
	public void setLogoPosition(LogoPosition logoPosition) {
		this.logoPosition = logoPosition;
	}

}

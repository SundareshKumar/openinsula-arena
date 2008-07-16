package org.openinsula.arena.echo2.component.pager;

import java.io.Serializable;

import nextapp.echo2.app.Style;

public interface PagerStyles extends Serializable {
	Style getBackPagerButtonStyle();
	
	Style getSkipPagerButtonStyle();
	
	Style getSelectedPagerButtonStyle();
	
	Style getPagerButtonStyle();
	
	Style getNextPagerButtonStyle(); 

}

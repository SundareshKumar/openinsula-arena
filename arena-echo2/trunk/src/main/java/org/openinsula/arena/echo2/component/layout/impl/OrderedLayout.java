package org.openinsula.arena.echo2.component.layout.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.layout.AbstractLayout;
import org.openinsula.arena.echo2.component.layout.Layout;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Label;

public class OrderedLayout extends AbstractLayout {

	public static final int ORIENTATION_VERTICAL = 0;
	
	public static final int ORIENTATION_HORIZONTAL = 1;
	
	public static final int ORIENTATION_FORM = 2;
	
	private int orientation = ORIENTATION_HORIZONTAL;
	
	private Set<LayoutStyle> divStyles = new HashSet<LayoutStyle>();
	
	private Set<LayoutStyle> componentStyles = new HashSet<LayoutStyle>();
	
	private boolean captionVisibility = true;
	
	public OrderedLayout() {
	}
	
	public OrderedLayout(int orientation) {
		this();
		this.orientation = orientation;
	}

	public Component buildLayout() {
		List<LayoutEntry> properties = getProperties();
		
		Component main = null;
		
		int lineNumber = 2;
		
		if (!isCaptionVisibility()) {
			lineNumber = 1;
		}
		
		switch (orientation) {
			case ORIENTATION_FORM:
				main = new Column();
				break;
			case ORIENTATION_HORIZONTAL: 
				main = new Grid(lineNumber);
				((Grid)main).setOrientation(Grid.ORIENTATION_HORIZONTAL);
				break;
			case ORIENTATION_VERTICAL:
				main = new Grid(lineNumber);
				((Grid)main).setOrientation(Grid.ORIENTATION_VERTICAL);
				break;
		}
		
		for (LayoutEntry entry : properties) {
			switch (entry.getLayoutPropertyType()) {
			case LAYOUT:
				Layout layout = (Layout) entry.getLayoutElement();
				Component buildLayout = layout.buildLayout();
				
				main.add(buildAndConfigureDiv(new Label(buildLayout.getId())));
				main.add(buildAndConfigureDiv(buildLayout));
				break;
			case COMPONENT:
				LayoutComponent layoutComponent = (LayoutComponent) entry.getLayoutElement();
				if (layoutComponent.getCaption() != null) {
					main.add(buildAndConfigureDiv(layoutComponent.getCaption()));
				} else {
					main.add(buildAndConfigureDiv(new Label(layoutComponent.getField().getId())));
				}
				configureComponent(layoutComponent.getCaption());
				configureComponent(layoutComponent.getField());
				
				main.add(buildAndConfigureDiv(layoutComponent.getField()));
				break;
			case STYLE:
				LayoutStyle layoutStyle = (LayoutStyle) entry.getLayoutElement();
				
				if (layoutStyle.getKlazz() == null) {
					if (divStyles.contains(layoutStyle)) {
						if (!divStyles.remove(layoutStyle)) {
							logger.warn("Error on deleting from divStyles list, the property: "+layoutStyle.getName());
						}
					}
					
					divStyles.add(layoutStyle);
				} else {
					if (componentStyles.contains(layoutStyle)) {
						if (!componentStyles.remove(layoutStyle)) {
							logger.warn("Error on deleting from componentStyles list, the property: "+layoutStyle.getName());
						}
					}
					
					componentStyles.add(layoutStyle);
				}
				break;
			}
		}
		
		return main;
	}

	private void configureComponent(Component field) {
		if (field == null) {
			return;
		}
		
		Class klazz = field.getClass();
		
		for(LayoutStyle layoutStyle : componentStyles) {
			if (klazz.equals(layoutStyle.getKlazz())) {
				field.setProperty(layoutStyle.getName(), layoutStyle.getValue());
			}
		}
		
	}

	public Div buildAndConfigureDiv(Component component) {
		Div div = new Div();
		
		for (LayoutStyle layoutStyle : divStyles) {
			div.setProperty(layoutStyle.getName(), layoutStyle.getValue());
		}
		
		div.add(component);
		
		return div;
	}

	public boolean isCaptionVisibility() {
		return captionVisibility;
	}

	public void setCaptionVisibility(boolean captionVisibility) {
		this.captionVisibility = captionVisibility;
	}
	
}

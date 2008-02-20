package org.openinsula.arena.echo2.component.layout;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.openinsula.arena.echo2.component.layout.impl.LayoutComponent;
import org.openinsula.arena.echo2.component.layout.impl.LayoutPropertyType;
import org.openinsula.arena.echo2.component.layout.impl.LayoutStyle;


public abstract class AbstractLayout implements Layout {

	protected static final Logger logger = Logger.getLogger(AbstractLayout.class);
	
	private List<LayoutEntry> properties = new ArrayList<LayoutEntry>();
	
	public void addComponent(Component component) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.COMPONENT, 
						new LayoutComponent(null, component)));		
	}

	public void addComponent(String caption, Component component) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.COMPONENT, 
						new LayoutComponent(new Label(caption), component)));
	}

	public void addComponent(Component caption, Component component) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.COMPONENT, 
						new LayoutComponent(caption, component)));
	}

	public void addStyle(String name, Object value) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.STYLE, 
						new LayoutStyle(name, value)));
	}
	
	public void addLayout(Layout layout) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.LAYOUT,
						layout));
	}

	public void addComponentStyle(Class<? extends Component> klazz,
			String name, Object value) {
		properties.add(
				new LayoutEntry(
						LayoutPropertyType.STYLE, 
						new LayoutStyle(klazz, name, value)));
	}

	public List<LayoutEntry> getProperties() {
		return properties;
	}

	protected class LayoutEntry {
		
		private LayoutPropertyType layoutPropertyType;
		
		private LayoutElement layoutElement;

		public LayoutEntry(LayoutPropertyType layoutPropertyType,
				LayoutElement layoutElement) {
			super();
			this.layoutPropertyType = layoutPropertyType;
			this.layoutElement = layoutElement;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof LayoutEntry) {
				LayoutEntry other = (LayoutEntry)obj;
				
				return new EqualsBuilder()
					.append(this.layoutPropertyType, other.layoutPropertyType)
					.append(this.layoutElement, other.layoutElement)
					.isEquals();
			}
			return false;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
				.append(this.layoutPropertyType)
				.append(this.layoutElement)
				.toHashCode();
		}

		public LayoutPropertyType getLayoutPropertyType() {
			return layoutPropertyType;
		}

		public void setLayoutPropertyType(LayoutPropertyType layoutPropertyType) {
			this.layoutPropertyType = layoutPropertyType;
		}

		public LayoutElement getLayoutElement() {
			return layoutElement;
		}

		public void setLayoutElement(LayoutElement layoutElement) {
			this.layoutElement = layoutElement;
		}
		
	}
	
}

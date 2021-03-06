package br.com.insula.arena.echo2.component.layout.impl;

import nextapp.echo2.app.Component;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.insula.arena.echo2.component.layout.LayoutElement;

public class LayoutComponent implements LayoutElement {
	private Component caption;
	
	private Component field;

	public LayoutComponent(Component caption, Component field) {
		super();
		this.caption = caption;
		this.field = field;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LayoutComponent) {
			LayoutComponent other = (LayoutComponent)obj;
			
			return new EqualsBuilder()
				.append(caption, other.caption)
				.append(field, other.field)
				.isEquals();
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(caption)
			.append(field)
			.toHashCode();
	}

	public Component getCaption() {
		return caption;
	}

	public void setCaption(Component caption) {
		this.caption = caption;
	}

	public Component getField() {
		return field;
	}

	public void setField(Component field) {
		this.field = field;
	}
}

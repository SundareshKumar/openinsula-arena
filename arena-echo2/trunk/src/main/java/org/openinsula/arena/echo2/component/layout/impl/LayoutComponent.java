package org.openinsula.arena.echo2.component.layout.impl;

import nextapp.echo2.app.Component;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.echo2.component.layout.LayoutElement;

public class LayoutComponent implements LayoutElement {
	private Log logger = LogFactory.getLog(getClass());

	private Component caption;

	private Component field;

	private boolean visibleCaption = true;

	public LayoutComponent(Component caption, Component field) {
		this.caption = caption;
		this.field = field;

		if (logger.isDebugEnabled()) {
			logger.debug("Creating Layout Component: " + toString());
		}
	}

	public LayoutComponent(Component caption, Component field, boolean visibleCaption) {
		this(caption, field);
		this.visibleCaption = visibleCaption;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LayoutComponent) {
			LayoutComponent other = (LayoutComponent) obj;

			return new EqualsBuilder().append(caption, other.caption).append(field, other.field).isEquals();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(caption).append(field).toHashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("LayoutComponent, caption: ");
		sb.append(caption);
		sb.append(", field: ").append(field);
		sb.append(", visibleCaption: ").append(visibleCaption);
		return sb.toString();
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

	public boolean isVisibleCaption() {
		return visibleCaption;
	}

	public void setVisibleCaption(boolean visibleCaption) {
		this.visibleCaption = visibleCaption;
	}
}

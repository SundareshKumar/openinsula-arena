package br.com.insula.arena.echo2.component.layout.impl;

import nextapp.echo2.app.Component;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.insula.arena.echo2.component.layout.LayoutElement;

public class LayoutStyle implements LayoutElement {

	private Class<? extends Component> klazz;
	
	private String name;
	
	private Object value;

	public LayoutStyle(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public LayoutStyle(Class<? extends Component> klazz, String name,
			Object value) {
		super();
		this.klazz = klazz;
		this.name = name;
		this.value = value;
	}

	public Class<? extends Component> getKlazz() {
		return klazz;
	}

	public void setKlazz(Class<? extends Component> klazz) {
		this.klazz = klazz;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LayoutStyle) {
			LayoutStyle other = (LayoutStyle)obj;
			
			return new EqualsBuilder()
				.append(this.name, other.name)
				.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(this.name)
			.toHashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}

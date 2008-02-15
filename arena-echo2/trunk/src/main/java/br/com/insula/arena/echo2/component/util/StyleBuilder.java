package br.com.insula.arena.echo2.component.util;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.webcontainer.propertyrender.BorderRender;
import nextapp.echo2.webcontainer.propertyrender.ColorRender;
import nextapp.echo2.webcontainer.propertyrender.ExtentRender;

public class StyleBuilder {
	
	private StringBuffer stringBuffer;
	
	public StyleBuilder() {
		stringBuffer = new StringBuffer();
	}
	
	public void setBorderStyle(int style) {
		addProperty("border-style: ", borderStyleToString(style));
	}
	
	public void addProperty(String property, Extent value) {
		addProperty(property, extentToString(value));
	}
	
	public void addProperty(String property, Color value) {
		addProperty(property, colorToString(value));
	}
	
	public void addProperty(String property, String value) {
		stringBuffer.append(property).append(value).append("; ");
	}

	@Override
	public String toString() {
		return stringBuffer.toString();
	}

	private String colorToString(Color color) {
		return ColorRender.renderCssAttributeValue(color);
	}

	private String extentToString(Extent extent) {
		String str = "";

		str += extent.getValue();
		str += ExtentRender.renderUnits(extent.getUnits());

		return str;
	}

	private String borderStyleToString(int style) {
		return BorderRender.getStyleValue(style);
	}
}

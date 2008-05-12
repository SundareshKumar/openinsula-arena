package org.openinsula.arena.echo2.component.field;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.text.Document;
import nextapp.echo2.app.text.StringDocument;
import nextapp.echo2.app.text.TextComponent;

public class NumberMaskField extends TextComponent {
	private static final long serialVersionUID = 1L;
	
	private String numberMask;
	
	public NumberMaskField() {
		super(new StringDocument());
	}

	public NumberMaskField(Document document) {
		super(document);
	}

	public NumberMaskField(Document document, String text, int columns) {
		super(document);
		if (text != null) {
			document.setText(text);
		}
		setWidth(new Extent(columns, Extent.EX));
	}

	public String getNumberMask() {
		return numberMask;
	}

	public void setNumberMask(String numberMask) {
		this.numberMask = numberMask;
	}
}
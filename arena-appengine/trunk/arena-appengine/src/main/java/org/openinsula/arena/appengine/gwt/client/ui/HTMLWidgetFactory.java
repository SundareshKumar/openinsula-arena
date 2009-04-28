package org.openinsula.arena.appengine.gwt.client.ui;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.UListElement;

public abstract class HTMLWidgetFactory {
	
	public static HTMLWidget<DivElement> div() {
		return new HTMLWidget<DivElement>() {
			protected DivElement createHTMLElement(final Document document) {
				return document.createDivElement();
			}
		};
	}
	
	public static HTMLWidget<SpanElement> span() {
		return new HTMLWidget<SpanElement>() {
			protected SpanElement createHTMLElement(final Document document) {
				return document.createSpanElement();
			}
		};
	}
	
	public static HTMLWidget<ParagraphElement> p() {
		return new HTMLWidget<ParagraphElement>() {
			protected ParagraphElement createHTMLElement(final Document document) {
				return document.createPElement();
			}
		};
	}
	
	public static HTMLWidget<TableElement> table() {
		return new HTMLWidget<TableElement>() {
			protected TableElement createHTMLElement(final Document document) {
				return document.createTableElement();
			}
		};
	}
	
	
	public static HTMLWidget<FormElement> form() {
		return new HTMLWidget<FormElement>() {
			protected FormElement createHTMLElement(final Document document) {
				return document.createFormElement();
			}
		};
	}
	
	public static HTMLWidget<LabelElement> label() {
		return new HTMLWidget<LabelElement>() {
			protected LabelElement createHTMLElement(final Document document) {
				return document.createLabelElement();
			}
		};
	}
	
	public static HTMLWidget<UListElement> ul() {
		return new HTMLWidget<UListElement>() {
			protected UListElement createHTMLElement(final Document document) {
				return document.createULElement();
			}
		};
	}
	
	public static HTMLWidget<LIElement> li() {
		return new HTMLWidget<LIElement>() {
			protected LIElement createHTMLElement(final Document document) {
				return document.createLIElement();
			}
		};
	}
	
	public static <T extends Element> HTMLWidget<T> wrap(final T element) {
		assert element != null;
		return new HTMLWidget<T>() {
			protected T createHTMLElement(final Document document) {
				return element;
			}
		};
	}
	
}

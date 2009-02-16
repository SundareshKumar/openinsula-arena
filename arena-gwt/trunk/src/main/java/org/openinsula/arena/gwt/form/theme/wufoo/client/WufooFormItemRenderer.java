package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.components.client.ui.CssBuilder;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.components.client.util.StringUtils;
import org.openinsula.arena.gwt.form.client.FormItemRenderer;
import org.openinsula.arena.gwt.form.client.FormItem.Size;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WufooFormItemRenderer extends WufooWidget implements FormItemRenderer, FocusListener {

	HTMLWidget<LIElement> itemWidget;

	private LazyChildWidget<LabelElement> labelElement;

	private LazyChildWidget<SpanElement> requiredElement;

	private LazyChildWidget<ParagraphElement> hintElement;

	private LazyChildWidget<HTMLWidget<DivElement>> widgetWrapperElement;
	
	private LazyChildWidget<ParagraphElement> errorMessageElement;

	private Widget widget;
	
	private CssBuilder cssBuilder;

	@Override
	Widget createRequiredWidgets() {
		cssBuilder = new CssBuilder();
		cssBuilder.addClassname("field");
		
		itemWidget = HTMLWidgetFactory.li();
		return itemWidget;
	}

	public void onLabelChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			labelElement.remove();
		}
		else {
			labelElement.get().setInnerHTML(newValue);
		}
	}
	
	public void onRequiredChange(final Boolean oldValue, final Boolean newValue) {
		if (newValue) {
			requiredElement.get();
		}
		else {
			requiredElement.remove();
		}
	}
	
	public void onSizeChange(final Size oldValue, final Size newValue) {
		if (oldValue != null) {
			cssBuilder.removeClassname(oldValue.name().toLowerCase());
		}
		
		if (newValue != null) {
			cssBuilder.addClassname(newValue.name().toLowerCase());
		}
		
		applyClassname();
	}
	
	public void onTipChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			hintElement.remove();
		}
		else {
			hintElement.get().setInnerHTML(newValue);
		}
	}
	
	public void onValidChange(final Boolean oldValue, final Boolean newValue) {
		itemWidget.getHTMLElement().setClassName(newValue ? "": "error");
	}
	
	public void onValidationMessageChange(final String oldValue, final String newValue) {
		if (!StringUtils.hasText(newValue)) {
			errorMessageElement.get().setInnerHTML(newValue);
		} else {
			errorMessageElement.remove();
		}
	}
	
	public void onWidgetChange(final Widget oldValue, final Widget newValue) {
		if (newValue == null) {
			widgetWrapperElement.remove();
			
		} else {
			
			if (oldValue != null) {
				widgetWrapperElement.get().remove(oldValue);
				cssBuilder.removeClassname(getClassnameFor(oldValue));
			}
			
			widgetWrapperElement.get().add(newValue);
			this.widget = newValue;
			
			if (newValue instanceof HasFocus) {
				((HasFocus) newValue).addFocusListener(WufooFormItemRenderer.this);
			}
			
			cssBuilder.addClassname(getClassnameFor(newValue));
			
			applyClassname();
		}
	}
	
	@Override
	protected void initLazyWidgets() {
		labelElement = new LazyChildWidget<LabelElement>() {
			@Override
			protected void beforeRemove(final LabelElement property) {
				itemWidget.remove(property);
			}

			@Override
			protected LabelElement createProperty(final Document document) {
				final LabelElement label = document.createLabelElement();
				label.setClassName("desc");
				itemWidget.addFirst(label);
				return label;
			}
		};

		requiredElement = new LazyChildWidget<SpanElement>() {
			@Override
			protected void beforeRemove(final SpanElement widget) {
				labelElement.get().removeChild(widget);
			}

			@Override
			protected SpanElement createProperty(final Document document) {
				final SpanElement span = document.createSpanElement();
				span.setClassName("req");
				span.setInnerText("  *");
				labelElement.get().appendChild(span);
				return span;
			}
		};

		hintElement = new LazyChildWidget<ParagraphElement>() {
			@Override
			protected void beforeRemove(final ParagraphElement widget) {
				itemWidget.remove(widget);
			}

			@Override
			protected ParagraphElement createProperty(final Document document) {
				final ParagraphElement hint = document.createPElement();
				hint.setClassName("instruct");
				itemWidget.add(hint);
				return hint;
			}
		};

		widgetWrapperElement = new LazyChildWidget<HTMLWidget<DivElement>>() {
			@Override
			protected void beforeRemove(final HTMLWidget<DivElement> widget) {
				itemWidget.remove(widget);
			}

			@Override
			protected HTMLWidget<DivElement> createProperty(final Document document) {
				final HTMLWidget<DivElement> div = HTMLWidgetFactory.div();

				final int pos = itemWidget.getWidgetIndex(hintElement.get(false));

				if (pos == -1) {
					itemWidget.add(div);
				}
				else {
					itemWidget.insert(div, pos);
				}

				return div;
			}
		};
		
		errorMessageElement = new LazyChildWidget<ParagraphElement>() {
		
			@Override
			protected void beforeRemove(final ParagraphElement widget) {
				itemWidget.remove(widget);
			}
		
			@Override
			protected ParagraphElement createProperty(final Document document) {
				ParagraphElement p = document.createPElement();
				p.setClassName("error");
				itemWidget.add(p);
				return p;
			}
		};
	}
	
	private void applyClassname() {
		if (widget != null) {
			String css = cssBuilder.buildClassname();
			
			if (!GWT.isScript()) {
				GWT.log("Css: " + css, null);
			}
			
			widget.setStyleName(css);
		}
	}
	
	private String getClassnameFor(final Widget widget) {
		if (widget instanceof TextBox) {
			return "text";
		}
		if (widget instanceof TextArea) {
			return "textarea";
		}
		if (widget instanceof ListBox) {
			return "select";
		}
		
		return null;
	}

	// FocusListener impl

	public void onFocus(final Widget sender) {
		itemWidget.getHTMLElement().setClassName("focused");
	}

	public void onLostFocus(final Widget sender) {
		itemWidget.getHTMLElement().setClassName("");
	}
	
}

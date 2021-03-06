package org.openinsula.arena.appengine.gwt.theme.wufoo.client;

import org.openinsula.arena.appengine.gwt.client.forms.FormItemRenderer;
import org.openinsula.arena.appengine.gwt.client.forms.FormItem.Size;
import org.openinsula.arena.appengine.gwt.client.ui.HTMLWidget;
import org.openinsula.arena.appengine.gwt.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.appengine.gwt.client.ui.LazyChildWidget;
import org.openinsula.arena.appengine.gwt.client.util.CssUtils;
import org.openinsula.arena.appengine.gwt.client.util.StringUtils;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WufooFormItemRenderer extends WufooWidget implements FormItemRenderer, FocusHandler, BlurHandler {

	private HTMLWidget<LIElement> itemWidget;

	private LazyChildWidget<LabelElement> labelElement;

	private LazyChildWidget<SpanElement> requiredElement;

	private LazyChildWidget<ParagraphElement> hintElement;

	private LazyChildWidget<HTMLWidget<DivElement>> widgetWrapperElement;

	private LazyChildWidget<ParagraphElement> errorMessageElement;

	private Widget widget;

	private String size;

	Widget createRequiredWidgets() {
		this.itemWidget = HTMLWidgetFactory.li();
		return this.itemWidget;
	}

	public void onLabelChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			this.labelElement.remove();
		}
		else {
			this.labelElement.get().setInnerHTML(newValue);
		}
	}

	public void onRequiredChange(final Boolean oldValue, final Boolean newValue) {
		if (newValue) {
			this.requiredElement.get();
		}
		else {
			this.requiredElement.remove();
		}
	}

	public void onSizeChange(final Size oldValue, final Size newValue) {
		this.size = newValue == null ? "" : newValue.name().toLowerCase();
		resolveWufooWidgetCSSClass();
	}

	public void onTipChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			this.hintElement.remove();
		}
		else {
			this.hintElement.get().setInnerHTML(newValue);
		}
	}

	public void onValidChange(final Boolean oldValue, final Boolean newValue) {
		final CssUtils css = new CssUtils(itemWidget.getHTMLElement().getClassName());

		if (newValue && css.hasRule("error")) {
			css.dropRule("error");
		}
		else if (!newValue) {
			css.addRule("error");
		}

		itemWidget.getHTMLElement().setClassName(css.toString());

		if (newValue) {
			errorMessageElement.remove();
		}
	}

	public void onValidationMessageChange(final String oldValue, final String newValue) {
		if (StringUtils.hasText(newValue)) {
			this.errorMessageElement.get().setInnerHTML(newValue);
		}
		else {
			this.errorMessageElement.remove();
		}
	}

	public void onWidgetChange(final Widget oldValue, final Widget newValue) {
		if (newValue == null) {
			this.widgetWrapperElement.remove();
		}
		else {
			if (oldValue != null) {
				this.widgetWrapperElement.get().remove(oldValue);
			}

			this.widgetWrapperElement.get().add(newValue);
			this.widget = newValue;

			if (newValue instanceof HasFocusHandlers) {
				((HasFocusHandlers) newValue).addFocusHandler(WufooFormItemRenderer.this);
			}

			if (newValue instanceof HasBlurHandlers) {
				((HasBlurHandlers) newValue).addBlurHandler(WufooFormItemRenderer.this);
			}

			resolveWufooWidgetCSSClass();
		}
	}

	protected void initLazyWidgets() {
		this.labelElement = new LazyChildWidget<LabelElement>() {
			protected void beforeRemove(final LabelElement property) {
				WufooFormItemRenderer.this.itemWidget.remove(property);
			}


			protected LabelElement createProperty(final Document document) {
				final LabelElement label = document.createLabelElement();
				label.setClassName("desc");
				WufooFormItemRenderer.this.itemWidget.addFirst(label);
				return label;
			}
		};

		this.requiredElement = new LazyChildWidget<SpanElement>() {

			protected void beforeRemove(final SpanElement widget) {
				WufooFormItemRenderer.this.labelElement.get().removeChild(widget);
			}


			protected SpanElement createProperty(final Document document) {
				final SpanElement span = document.createSpanElement();
				span.setClassName("req");
				span.setInnerText("  *");
				WufooFormItemRenderer.this.labelElement.get().appendChild(span);
				return span;
			}
		};

		this.hintElement = new LazyChildWidget<ParagraphElement>() {

			protected void beforeRemove(final ParagraphElement widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}


			protected ParagraphElement createProperty(final Document document) {
				final ParagraphElement hint = document.createPElement();
				hint.setClassName("instruct");
				WufooFormItemRenderer.this.itemWidget.add(hint);
				return hint;
			}
		};

		this.widgetWrapperElement = new LazyChildWidget<HTMLWidget<DivElement>>() {

			protected void beforeRemove(final HTMLWidget<DivElement> widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}


			protected HTMLWidget<DivElement> createProperty(final Document document) {
				final HTMLWidget<DivElement> div = HTMLWidgetFactory.div();

				final int pos = WufooFormItemRenderer.this.itemWidget
				.getWidgetIndex(WufooFormItemRenderer.this.hintElement.get(false));

				if (pos == -1) {
					WufooFormItemRenderer.this.itemWidget.add(div);
				}
				else {
					WufooFormItemRenderer.this.itemWidget.insert(div, pos);
				}

				return div;
			}
		};

		this.errorMessageElement = new LazyChildWidget<ParagraphElement>() {


			protected void beforeRemove(final ParagraphElement widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}


			protected ParagraphElement createProperty(final Document document) {
				final ParagraphElement p = document.createPElement();
				p.setClassName("error");
				WufooFormItemRenderer.this.itemWidget.add(p);
				return p;
			}
		};
	}

	private void resolveWufooWidgetCSSClass() {
		if (this.widget != null) {
			if (this.widget instanceof TextBox || this.widget instanceof SuggestBox) {
				this.widget.setStyleName(this.size + " field text");
			}
			else if (this.widget instanceof TextArea) {
				this.widget.setStyleName(this.size + " field textarea");
			}
			else if (this.widget instanceof ListBox) {
				this.widget.setStyleName(this.size + " field select");
			}
		}
	}


	private String previousStyle;


	public void onFocus(final FocusEvent event) {
		this.previousStyle = this.itemWidget.getHTMLElement().getClassName();
		this.itemWidget.getHTMLElement().setClassName(this.previousStyle + " focused");
	}


	public void onBlur(final BlurEvent event) {
		this.itemWidget.getHTMLElement().setClassName(this.previousStyle);
	}

}

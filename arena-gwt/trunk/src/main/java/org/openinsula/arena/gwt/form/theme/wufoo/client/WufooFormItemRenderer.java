package org.openinsula.arena.gwt.form.theme.wufoo.client;

import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.components.client.util.StringUtils;
import org.openinsula.arena.gwt.form.client.FormItemRenderer;
import org.openinsula.arena.gwt.form.client.FormItem.Size;

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

	private HTMLWidget<LIElement> itemWidget;

	private LazyChildWidget<LabelElement> labelElement;

	private LazyChildWidget<SpanElement> requiredElement;

	private LazyChildWidget<ParagraphElement> hintElement;

	private LazyChildWidget<HTMLWidget<DivElement>> widgetWrapperElement;

	private LazyChildWidget<ParagraphElement> errorMessageElement;

	private Widget widget;

	private String size;

	@Override
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
		this.itemWidget.getHTMLElement().setClassName(newValue ? "" : "error");
		if (newValue) {
			this.errorMessageElement.remove();
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

			if (newValue instanceof HasFocus) {
				((HasFocus) newValue).addFocusListener(WufooFormItemRenderer.this);
			}

			resolveWufooWidgetCSSClass();
		}
	}

	@Override
	protected void initLazyWidgets() {
		this.labelElement = new LazyChildWidget<LabelElement>() {
			@Override
			protected void beforeRemove(final LabelElement property) {
				WufooFormItemRenderer.this.itemWidget.remove(property);
			}

			@Override
			protected LabelElement createProperty(final Document document) {
				final LabelElement label = document.createLabelElement();
				label.setClassName("desc");
				WufooFormItemRenderer.this.itemWidget.addFirst(label);
				return label;
			}
		};

		this.requiredElement = new LazyChildWidget<SpanElement>() {
			@Override
			protected void beforeRemove(final SpanElement widget) {
				WufooFormItemRenderer.this.labelElement.get().removeChild(widget);
			}

			@Override
			protected SpanElement createProperty(final Document document) {
				final SpanElement span = document.createSpanElement();
				span.setClassName("req");
				span.setInnerText("  *");
				WufooFormItemRenderer.this.labelElement.get().appendChild(span);
				return span;
			}
		};

		this.hintElement = new LazyChildWidget<ParagraphElement>() {
			@Override
			protected void beforeRemove(final ParagraphElement widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}

			@Override
			protected ParagraphElement createProperty(final Document document) {
				final ParagraphElement hint = document.createPElement();
				hint.setClassName("instruct");
				WufooFormItemRenderer.this.itemWidget.add(hint);
				return hint;
			}
		};

		this.widgetWrapperElement = new LazyChildWidget<HTMLWidget<DivElement>>() {
			@Override
			protected void beforeRemove(final HTMLWidget<DivElement> widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}

			@Override
			protected HTMLWidget<DivElement> createProperty(final Document document) {
				final HTMLWidget<DivElement> div = HTMLWidgetFactory.div();

				final int pos = WufooFormItemRenderer.this.itemWidget.getWidgetIndex(WufooFormItemRenderer.this.hintElement.get(false));

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

			@Override
			protected void beforeRemove(final ParagraphElement widget) {
				WufooFormItemRenderer.this.itemWidget.remove(widget);
			}

			@Override
			protected ParagraphElement createProperty(final Document document) {
				ParagraphElement p = document.createPElement();
				p.setClassName("error");
				WufooFormItemRenderer.this.itemWidget.add(p);
				return p;
			}
		};
	}

	private void resolveWufooWidgetCSSClass() {
		if (this.widget != null) {
			if (this.widget instanceof TextBox) {
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

	// FocusListener impl

	private String previousStyle;

	public void onFocus(final Widget sender) {
		this.previousStyle = this.itemWidget.getHTMLElement().getClassName();
		this.itemWidget.getHTMLElement().setClassName(this.previousStyle + " focused");
	}

	public void onLostFocus(final Widget sender) {
		this.itemWidget.getHTMLElement().setClassName(this.previousStyle);
	}

}

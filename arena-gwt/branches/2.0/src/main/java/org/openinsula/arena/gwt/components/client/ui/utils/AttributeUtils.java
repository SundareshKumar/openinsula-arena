package org.openinsula.arena.gwt.components.client.ui.utils;

import org.openinsula.arena.gwt.components.client.ui.form.FormItem;

import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBoxBase.TextAlignConstant;

/**
 * @author Lucas K Mogari
 */
public abstract class AttributeUtils {

	public static void setEnabled(boolean enabled, FocusWidget... widgets) {
		for (final FocusWidget focusWidget : widgets) {
			focusWidget.setEnabled(enabled);
		}
	}

	public static void setEnabled(boolean enabled, FormItem... formItems) {
		for (final FormItem formItem : formItems) {
			final Widget widget = formItem.toWidget();

			if (widget instanceof FocusWidget) {
				final FocusWidget focusWidget = (FocusWidget) widget;

				focusWidget.setEnabled(enabled);
			}
		}
	}

	public static void setVisible(boolean visible, Widget... widgets) {
		for (final Widget widget : widgets) {
			widget.setVisible(visible);
		}
	}

	public static void setVisible(boolean visible, FormItem... formItems) {
		for (final FormItem formItem : formItems) {
			formItem.toWidget().setVisible(visible);
		}
	}

	public static void setTextAligment(TextAlignConstant align, TextBoxBase... textBoxes) {
		for (final TextBoxBase textBoxBase : textBoxes) {
			textBoxBase.setTextAlignment(align);
		}
	}

	public static String getId(Element widgetElement) {
		String id = null;

		try {
			id = widgetElement.getAttribute("id");

			if (id == null || id.length() == 0) {
				id = DOM.createUniqueId();

				widgetElement.setAttribute("id", id);
			}
		}
		catch (final Exception e) {
		}

		return id;
	}

	public static String getId(Widget widget) {
		return getId(widget.getElement());
	}

	public static String findFirstFocusWidgetId(Element widgetElement) {
		String id = null;
		final String tag = widgetElement.getTagName();

		if ("span".equalsIgnoreCase(tag) || "div".equalsIgnoreCase(tag) || "li".equalsIgnoreCase(tag)) {
			final NodeList<Node> childNodes = widgetElement.getChildNodes();

			for (int i = 0; i < childNodes.getLength(); i++) {
				final Node node = childNodes.getItem(i);

				if (node instanceof Element) {
					id = findFirstFocusWidgetId((Element) node);

					if (id != null) {
						break;
					}
				}
			}
		}
		else {
			id = getId(widgetElement);
		}

		return id;
	}

}

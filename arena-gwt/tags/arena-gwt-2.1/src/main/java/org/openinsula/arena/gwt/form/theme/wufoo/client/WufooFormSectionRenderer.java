package org.openinsula.arena.gwt.form.theme.wufoo.client;

import java.util.List;

import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.form.client.Action;
import org.openinsula.arena.gwt.form.client.FormItem;
import org.openinsula.arena.gwt.form.client.FormSectionRenderer;
import org.openinsula.arena.gwt.form.client.FormSection.Position;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

class WufooFormSectionRenderer extends WufooWidget implements FormSectionRenderer {

	private static final String RIGHT_HALF_STYLENAME = "rightHalf";

	private static final String LEFT_HALF_STYLENAME = "leftHalf";

	private static final String SINGLE_LINE_STYLENAME = "";

	private HTMLWidget<UListElement> mainElement;

	private HTMLWidget<LIElement> headerWidget;

	private LazyChildWidget<HeadingElement> headerTitleElement;

	private LazyChildWidget<DivElement> headerSubtitleElement;

	private LazyChildWidget<HTMLWidget<LIElement>> buttonBarWidget;

	@Override
	Widget createRequiredWidgets() {
		headerWidget = HTMLWidgetFactory.li();
		headerWidget.setStyleName("section");

		mainElement = HTMLWidgetFactory.ul();
		mainElement.addFirst(headerWidget);

		return mainElement;
	}

	public void onTitleChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			headerTitleElement.remove();
		}
		else {
			headerTitleElement.get().setInnerHTML(newValue);
		}
	}

	public void onSubtitleChange(final String oldValue, final String newValue) {
		if (newValue == null) {
			headerSubtitleElement.remove();
		}
		else {
			headerSubtitleElement.get().setInnerHTML(newValue);
		}
	}

	@Override
	protected void initLazyWidgets() {
		headerTitleElement = new LazyChildWidget<HeadingElement>() {
			@Override
			protected void beforeRemove(final HeadingElement widget) {
				headerWidget.remove(widget);
			}

			@Override
			protected HeadingElement createProperty(final Document document) {
				final HeadingElement h3 = document.createHElement(3);
				headerWidget.addFirst(h3);
				return h3;
			}
		};

		headerSubtitleElement = new LazyChildWidget<DivElement>() {
			@Override
			protected void beforeRemove(final DivElement widget) {
				headerWidget.remove(widget);
			}

			@Override
			protected DivElement createProperty(final Document document) {
				final DivElement div = document.createDivElement();
				headerWidget.add(div);
				return div;
			}
		};

		buttonBarWidget = new LazyChildWidget<HTMLWidget<LIElement>>() {
			@Override
			protected void beforeRemove(final HTMLWidget<LIElement> property) {
				mainElement.remove(property);
			}

			@Override
			protected HTMLWidget<LIElement> createProperty(final Document document) {
				HTMLWidget<LIElement> li = HTMLWidgetFactory.li();
				li.getHTMLElement().setClassName("buttons");
				mainElement.add(li);
				return li;
			}
		};

	}

	public void onFormItemAdded(final List<FormItem> formItemList, final FormItem formItem, final Position position) {
		final Widget nextItem = formItem.toWidget();

		if (position == Position.INLINE) {
			Widget previousItem = null;
			int idx = formItemList.indexOf(formItem);

			if (idx > 0) {
				previousItem = formItemList.get(idx - 1).toWidget();

				if (RIGHT_HALF_STYLENAME.equals(previousItem.getStyleName())) {
					if (!GWT.isScript()) {
						GWT.log("Only 2 items per line is allowed. Putting in a new line", null);
					}
					nextItem.setStyleName(SINGLE_LINE_STYLENAME);
				}
				else {
					previousItem.setStyleName(LEFT_HALF_STYLENAME);
					nextItem.setStyleName(RIGHT_HALF_STYLENAME);
				}
			}
		}
		else if (position == Position.NEW_LINE) {
			nextItem.setStyleName(SINGLE_LINE_STYLENAME);
		}

		if (buttonBarWidget.get(false) != null) {
			mainElement.remove(buttonBarWidget.get());
			mainElement.add(nextItem);
			mainElement.add(buttonBarWidget.get());
		}

		mainElement.add(nextItem);
	}

	public void onFormItemRemoved(final List<FormItem> formItemList, final FormItem formItem) {
		// TODO terminar
		mainElement.remove(formItem.toWidget());
	}

	public void onActionAdded(final Action action, final int position) {
		Hyperlink link = new Hyperlink(action.label(), null);
		link.setStyleName("FormSecondaryAction");
		link.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				action.execute();
			}
		});

		buttonBarWidget.get().add(link);
	}

}

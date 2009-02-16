package org.openinsula.arena.gwt.form.theme.wufoo.client;

import java.util.List;

import org.openinsula.arena.gwt.components.client.beans.LazyProperty;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.form.client.Action;
import org.openinsula.arena.gwt.form.client.FormRenderer;
import org.openinsula.arena.gwt.form.client.FormSection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

class WufooFormRenderer extends WufooWidget implements FormRenderer {

	private HTMLWidget<FormElement> formWidget;

	private LazyChildWidget<HTMLWidget<DivElement>> headerWidget;

	private LazyChildWidget<HeadingElement> headerTitleElement;

	private LazyChildWidget<DivElement> headerSubtitleElement;

	private LazyChildWidget<HTMLWidget<UListElement>> itensWidget;

	private LazyChildWidget<HTMLWidget<LIElement>> buttonBarWidget;

	private LazyProperty<Button> primaryActionButton;

	@Override
	Widget createRequiredWidgets() {
		formWidget = HTMLWidgetFactory.form();
		formWidget.setStyleName("wufoo");
		formWidget.getHTMLElement().setMethod("POST");

		return formWidget;
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
		headerWidget = new LazyChildWidget<HTMLWidget<DivElement>>() {

			@Override
			protected void beforeRemove(final HTMLWidget<DivElement> property) {
				formWidget.remove(property);
			}

			@Override
			protected HTMLWidget<DivElement> createProperty() {
				final HTMLWidget<DivElement> div = HTMLWidgetFactory.div();
				div.setStyleName("info");
				formWidget.addFirst(div);
				return div;
			}
		};

		headerTitleElement = new LazyChildWidget<HeadingElement>() {
			@Override
			protected void beforeRemove(final HeadingElement widget) {
				headerWidget.get().remove(widget);
				headerWidget.removeIfLeaf();
			}

			@Override
			protected HeadingElement createProperty(final Document document) {
				final HeadingElement h2 = document.createHElement(2);
				headerWidget.get().addFirst(h2);
				return h2;
			}
		};

		headerSubtitleElement = new LazyChildWidget<DivElement>() {
			@Override
			protected void beforeRemove(final DivElement widget) {
				headerWidget.get().remove(widget);
				headerWidget.removeIfLeaf();
			}

			@Override
			protected DivElement createProperty(final Document document) {
				final DivElement div = document.createDivElement();
				headerWidget.get().add(div);
				return div;
			}
		};

		itensWidget = new LazyChildWidget<HTMLWidget<UListElement>>() {
			@Override
			protected void beforeRemove(final HTMLWidget<UListElement> widget) {
				formWidget.remove(widget);
			}

			@Override
			protected HTMLWidget<UListElement> createProperty(final Document document) {
				final HTMLWidget<UListElement> ul = HTMLWidgetFactory.ul();
				formWidget.add(ul);
				return ul;
			}
		};

		buttonBarWidget = new LazyChildWidget<HTMLWidget<LIElement>>() {
			@Override
			protected void beforeRemove(final HTMLWidget<LIElement> widget) {
				itensWidget.get().remove(widget);
			}

			@Override
			protected HTMLWidget<LIElement> createProperty(final Document document) {
				final HTMLWidget<LIElement> li = HTMLWidgetFactory.li();
				li.getHTMLElement().setClassName("buttons");
				itensWidget.get().add(li);
				return li;
			}
		};

		primaryActionButton = new LazyProperty<Button>() {
			@Override
			protected void beforeRemove(final Button property) {
				buttonBarWidget.get().remove(property);
				buttonBarWidget.removeIfLeaf();
			}

			@Override
			protected Button createProperty() {
				final Button button = new Button();
				button.setStyleName("btTxt");
				buttonBarWidget.get().add(button);
				return button;
			}
		};
	}

	public void onFormSectionAdded(final List<FormSection> sectionList, final FormSection formSection) {
		WufooFormSectionRenderer sectionRenderer = (WufooFormSectionRenderer) formSection.getRenderer();
		
		if (buttonBarWidget.get(false) == null) {
			mergeSectionItens(sectionRenderer.mainElement);
		}
		else {
			itensWidget.get().remove(buttonBarWidget.get());
			mergeSectionItens(sectionRenderer.mainElement);
			itensWidget.get().add(buttonBarWidget.get());
		}
	}
	
	private void mergeSectionItens(final HTMLWidget<UListElement> section) {
		if (!GWT.isScript()) {
			GWT.log(section.getHTMLElement().getInnerHTML(), null);
		}
		
		for (int i = 0, n = section.getWidgetCount(); i < n; i++) {
			if (!GWT.isScript()) {
				GWT.log("i = " + i + ", n = " + n, null);
			}
			
			Widget widget = section.getWidget(i);
			
			if (!GWT.isScript()) {
				GWT.log("widget = " + widget, null);
			}
			
			itensWidget.get().add(widget);
		}
	}

	public void onFormSectionRemoved(final List<FormSection> sectionList, final FormSection formSection) {
		WufooFormSectionRenderer sectionRenderer = (WufooFormSectionRenderer) formSection.getRenderer();
		
		for (int i = 0, n = sectionRenderer.mainElement.getWidgetCount(); i < n; i++) {
			itensWidget.get().remove(sectionRenderer.mainElement.getWidget(i));
		}
	}

	public void onPrimaryActionChange(final Action oldValue, final Action newValue) {
		if (newValue == null) {
			primaryActionButton.remove();
		}
		else {
			Button button = primaryActionButton.get();
			button.setText(newValue.label());
			button.addClickListener(new ClickListener() {
				public void onClick(final Widget sender) {
					newValue.execute();
				}
			});
		}
	}

	public void onSecondaryActionAdded(final Action action, final int position) {
		final Hyperlink link = new Hyperlink(action.label(), action.label());
		link.setStyleName("FormSecondaryAction");
		link.addClickListener(new ClickListener() {
			public void onClick(final Widget sender) {
				action.execute();
			}
		});

		buttonBarWidget.get().add(link);
	}

}

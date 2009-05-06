package org.openinsula.arena.appengine.gwt.theme.wufoo.client;

import java.util.List;

import org.openinsula.arena.appengine.gwt.client.beans.LazyProperty;
import org.openinsula.arena.appengine.gwt.client.forms.Action;
import org.openinsula.arena.appengine.gwt.client.forms.FormRenderer;
import org.openinsula.arena.appengine.gwt.client.forms.FormSection;
import org.openinsula.arena.appengine.gwt.client.ui.HTMLWidget;
import org.openinsula.arena.appengine.gwt.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.appengine.gwt.client.ui.LazyChildWidget;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

class WufooFormRenderer extends WufooWidget implements FormRenderer {

	private HTMLWidget<FormElement> formWidget;

	private LazyChildWidget<HTMLWidget<DivElement>> headerWidget;

	private LazyChildWidget<HeadingElement> headerTitleElement;

	private LazyChildWidget<DivElement> headerSubtitleElement;

	private ButtonBarWidget buttonBarWidget;

	private LazyProperty<Button> primaryActionButton;


	Widget createRequiredWidgets() {
		formWidget = HTMLWidgetFactory.form();
		formWidget.setStyleName("wufoo");
		formWidget.getHTMLElement().setMethod("POST");

		buttonBarWidget = new ButtonBarWidget();

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


	protected void initLazyWidgets() {
		headerWidget = new LazyChildWidget<HTMLWidget<DivElement>>() {


			protected void beforeRemove(final HTMLWidget<DivElement> property) {
				formWidget.remove(property);
			}


			protected HTMLWidget<DivElement> createProperty(final Document document) {
				final HTMLWidget<DivElement> div = HTMLWidgetFactory.div();
				div.setStyleName("info");
				formWidget.addFirst(div);
				return div;
			}
		};

		headerTitleElement = new LazyChildWidget<HeadingElement>() {

			protected void beforeRemove(final HeadingElement widget) {
				headerWidget.get().remove(widget);
				headerWidget.removeIfLeaf();
			}


			protected HeadingElement createProperty(final Document document) {
				final HeadingElement h2 = document.createHElement(2);
				headerWidget.get().addFirst(h2);
				return h2;
			}
		};

		headerSubtitleElement = new LazyChildWidget<DivElement>() {

			protected void beforeRemove(final DivElement widget) {
				headerWidget.get().remove(widget);
				headerWidget.removeIfLeaf();
			}


			protected DivElement createProperty(final Document document) {
				final DivElement div = document.createDivElement();
				headerWidget.get().add(div);
				return div;
			}
		};

		primaryActionButton = new LazyProperty<Button>() {

			protected void beforeRemove(final Button property) {
				buttonBarWidget.removeButton(property);
				buttonBarWidget.removeIfLeaf();
			}


			protected Button createProperty() {
				final Button button = new Button();
				button.setStyleName("btTxt");
				buttonBarWidget.addButton(button, true);
				return button;
			}
		};
	}

	public void onFormSectionAdded(final List<FormSection> sectionList, final FormSection formSection) {
		formWidget.add(formSection.toWidget());
		HTMLWidget<UListElement> buttonBar = buttonBarWidget.get(false);

		if (buttonBar != null) {
			formWidget.remove(buttonBar);
			formWidget.add(buttonBar);
		}
	}

	public void onFormSectionRemoved(final List<FormSection> sectionList, final FormSection formSection) {
		formWidget.remove(formSection.toWidget());
	}

	public void onPrimaryActionChange(final Action oldValue, final Action newValue) {
		primaryActionButton.remove();

		if (newValue != null) {
			Button button = primaryActionButton.get();
			button.setText(newValue.label());
			button.setEnabled(newValue.enabled());
			button.addClickHandler(new ClickHandler() {

				public void onClick(final ClickEvent event) {
					newValue.execute();
				}
			});
		}
	}

	public void onSecondaryActionAdded(final Action action, final int position) {
		final Hyperlink link = new Hyperlink(action.label(), action.label());
		link.setStyleName("FormSecondaryAction");
		link.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				action.execute();
			}
		});

		buttonBarWidget.addButton(link, false);
	}

	private class ButtonBarWidget extends LazyChildWidget<HTMLWidget<UListElement>> {

		private HTMLWidget<LIElement> li;


		protected void beforeRemove(final HTMLWidget<UListElement> widget) {
			formWidget.remove(widget);
		}


		protected HTMLWidget<UListElement> createProperty(final Document document) {
			li = HTMLWidgetFactory.li();
			li.getHTMLElement().setClassName("buttons");

			HTMLWidget<UListElement> ul = HTMLWidgetFactory.ul();
			ul.add(li);
			formWidget.add(ul);

			return ul;
		}

		public void addButton(final Widget widget, final boolean first) {
			get();

			if (first) {
				li.addFirst(widget);
			} else {
				li.add(widget);
			}
		}

		public void removeButton(final Widget widget) {
			get();
			li.remove(widget);
		}

	}

}

package org.openinsula.arena.gwt.form.theme.wufoo.client;

import java.util.List;

import org.openinsula.arena.gwt.components.client.beans.LazyProperty;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidget;
import org.openinsula.arena.gwt.components.client.ui.HTMLWidgetFactory;
import org.openinsula.arena.gwt.components.client.ui.LazyChildWidget;
import org.openinsula.arena.gwt.form.client.Action;
import org.openinsula.arena.gwt.form.client.FormRenderer;
import org.openinsula.arena.gwt.form.client.FormSection;

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

	private ButtonBarWidget buttonBarWidget;

	private LazyProperty<Button> primaryActionButton;

	@Override
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

	@Override
	protected void initLazyWidgets() {
		headerWidget = new LazyChildWidget<HTMLWidget<DivElement>>() {

			@Override
			protected void beforeRemove(final HTMLWidget<DivElement> property) {
				formWidget.remove(property);
			}

			@Override
			protected HTMLWidget<DivElement> createProperty(final Document document) {
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
		
		primaryActionButton = new LazyProperty<Button>() {
			@Override
			protected void beforeRemove(final Button property) {
				buttonBarWidget.removeButton(property);
				buttonBarWidget.removeIfLeaf();
			}

			@Override
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

		buttonBarWidget.addButton(link, false);
	}

	private class ButtonBarWidget extends LazyChildWidget<HTMLWidget<UListElement>> {

		private HTMLWidget<LIElement> li;

		@Override
		protected void beforeRemove(final HTMLWidget<UListElement> widget) {
			formWidget.remove(widget);
		}

		@Override
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

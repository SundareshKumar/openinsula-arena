package org.openinsula.arena.echo2.component.layout.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Row;

import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.layout.AbstractLayout;
import org.openinsula.arena.echo2.component.layout.Layout;

public class OrderedLayout extends AbstractLayout {

	public static final int ORIENTATION_VERTICAL = 0;

	public static final int ORIENTATION_HORIZONTAL = 1;

	public static final int ORIENTATION_FORM = 2;

	private int orientation = ORIENTATION_HORIZONTAL;

	private Set<LayoutStyle> divStyles = new HashSet<LayoutStyle>();

	private Set<LayoutStyle> componentStyles = new HashSet<LayoutStyle>();

	private boolean captionVisibility = true;

	private int captionWidth = -1;

	private int traversalIndex = 0;

	public OrderedLayout() {
	}

	public OrderedLayout(int orientation) {
		this();
		this.orientation = orientation;
	}

	/**
	 * Constrói o layout no modelo FORM
	 * @return
	 */
	private Component buildFormLayout() {
		List<LayoutEntry> properties = getProperties();

		Component main = new Column();

		for (LayoutEntry entry : properties) {
			switch (entry.getLayoutPropertyType()) {
			case LAYOUT:
				Layout layout = (Layout) entry.getLayoutElement();
				Component buildLayout = layout.buildLayout();

				main.add(buildAndConfigureDiv(new Label(buildLayout.getId())));
				main.add(buildAndConfigureDiv(buildLayout));
				break;
			case COMPONENT:
				LayoutComponent layoutComponent = (LayoutComponent) entry.getLayoutElement();
				if (layoutComponent.getCaption() != null) {
					main.add(buildAndConfigureDiv(layoutComponent.getCaption()));
				}
				else if (layoutComponent.isVisibleCaption()) {
					main.add(buildAndConfigureDiv(new Label(layoutComponent.getField().getId())));
				}
				configureComponent(layoutComponent.getCaption());
				configureComponent(layoutComponent.getField());

				main.add(buildAndConfigureDiv(layoutComponent.getField()));
				break;
			case STYLE:
				LayoutStyle layoutStyle = (LayoutStyle) entry.getLayoutElement();

				if (layoutStyle.getKlazz() == null) {
					if (divStyles.contains(layoutStyle) && !divStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from divStyles list, the property: " + layoutStyle.getName());
					}

					divStyles.add(layoutStyle);
				}
				else {
					if (componentStyles.contains(layoutStyle) && !componentStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from componentStyles list, the property: "
								+ layoutStyle.getName());
					}

					componentStyles.add(layoutStyle);
				}
				break;
			}
		}
		return main;
	}

	/**
	 * Constrói o layout no modelo HORIZONTAL
	 * @return
	 */
	private Component buildHorizontalLayout() {
		List<LayoutEntry> properties = getProperties();

		Column main = new Column();

		for (LayoutEntry entry : properties) {
			switch (entry.getLayoutPropertyType()) {
			case LAYOUT:
				Layout layout = (Layout) entry.getLayoutElement();
				Component buildLayout = layout.buildLayout();

				main.add(buildAndConfigureDiv(buildLayout));
				break;
			case COMPONENT:
				LayoutComponent layoutComponent = (LayoutComponent) entry.getLayoutElement();

				Row row = new Row();

				if (layoutComponent.getCaption() != null) {
					Div buildAndConfigureDiv = buildAndConfigureDiv(layoutComponent.getCaption());
					if (getCaptionWidth() != -1) {
						buildAndConfigureDiv.setWidth(new Extent(getCaptionWidth()));
					}
					row.add(buildAndConfigureDiv);
				}
				else if (layoutComponent.isVisibleCaption()) {
					Div buildAndConfigureDiv = buildAndConfigureDiv(new Label(layoutComponent.getField().getId()));
					if (getCaptionWidth() != -1) {
						buildAndConfigureDiv.setWidth(new Extent(getCaptionWidth()));
					}
					row.add(buildAndConfigureDiv);
				}
				configureComponent(layoutComponent.getCaption());
				configureComponent(layoutComponent.getField());

				row.add(buildAndConfigureDiv(layoutComponent.getField()));

				main.add(row);
				break;
			case STYLE:
				LayoutStyle layoutStyle = (LayoutStyle) entry.getLayoutElement();

				if (layoutStyle.getKlazz() == null) {
					if (divStyles.contains(layoutStyle) && !divStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from divStyles list, the property: " + layoutStyle.getName());
					}

					divStyles.add(layoutStyle);
				}
				else {
					if (componentStyles.contains(layoutStyle) && !componentStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from componentStyles list, the property: "
								+ layoutStyle.getName());
					}

					componentStyles.add(layoutStyle);
				}
				break;
			}
		}
		return main;
	}

	/**
	 * Constrói o layout no modelo VERTICAL
	 * @return
	 */
	private Component buildVerticalLayout() {
		List<LayoutEntry> properties = getProperties();

		Row main = new Row();

		for (LayoutEntry entry : properties) {
			switch (entry.getLayoutPropertyType()) {
			case LAYOUT:
				Layout layout = (Layout) entry.getLayoutElement();
				Component buildLayout = layout.buildLayout();

				main.add(buildAndConfigureDiv(buildLayout));
				break;
			case COMPONENT:
				LayoutComponent layoutComponent = (LayoutComponent) entry.getLayoutElement();

				Column column = new Column();

				if (layoutComponent.getCaption() != null) {
					configureComponent(layoutComponent.getCaption());
					column.add(buildAndConfigureDiv(layoutComponent.getCaption()));
				}
				else if (layoutComponent.isVisibleCaption()) {
					Label label = new Label(layoutComponent.getField().getId());
					configureComponent(label);
					column.add(buildAndConfigureDiv(label));
				}
				configureComponent(layoutComponent.getField());

				column.add(buildAndConfigureDiv(layoutComponent.getField()));

				main.add(column);
				break;
			case STYLE:
				LayoutStyle layoutStyle = (LayoutStyle) entry.getLayoutElement();

				if (layoutStyle.getKlazz() == null) {
					if (divStyles.contains(layoutStyle) && !divStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from divStyles list, the property: " + layoutStyle.getName());
					}

					divStyles.add(layoutStyle);
				}
				else {
					if (componentStyles.contains(layoutStyle) && !componentStyles.remove(layoutStyle)) {
						logger.warn("Error on deleting from componentStyles list, the property: "
								+ layoutStyle.getName());
					}

					componentStyles.add(layoutStyle);
				}
				break;
			}
		}
		return main;
	}

	public Component buildLayout() {
		switch (orientation) {
		case ORIENTATION_FORM:
			return buildFormLayout();
		case ORIENTATION_HORIZONTAL:
			return buildHorizontalLayout();
		case ORIENTATION_VERTICAL:
			return buildVerticalLayout();
		}
		return null;
	}

	private void configureComponent(Component field) {
		if (field == null) {
			return;
		}

		Class<? extends Component> klazz = field.getClass();

		for (LayoutStyle layoutStyle : componentStyles) {
			if (klazz.equals(layoutStyle.getKlazz())) {
				field.setProperty(layoutStyle.getName(), layoutStyle.getValue());
			}
		}

		field.setFocusTraversalIndex(traversalIndex++);
	}

	public Div buildAndConfigureDiv(Component component) {
		Div div = new Div();

		for (LayoutStyle layoutStyle : divStyles) {
			div.setProperty(layoutStyle.getName(), layoutStyle.getValue());
		}

		div.add(component);

		return div;
	}

	public boolean isCaptionVisibility() {
		return captionVisibility;
	}

	public void setCaptionVisibility(boolean captionVisibility) {
		this.captionVisibility = captionVisibility;
	}

	public int getCaptionWidth() {
		return captionWidth;
	}

	public void setCaptionWidth(int captionWidth) {
		this.captionWidth = captionWidth;
	}

	public int getTraversalIndex() {
		return traversalIndex;
	}

	public void setTraversalIndex(int traversalIndex) {
		this.traversalIndex = traversalIndex;
	}

}

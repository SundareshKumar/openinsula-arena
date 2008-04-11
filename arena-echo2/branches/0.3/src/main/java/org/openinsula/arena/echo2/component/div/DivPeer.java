package org.openinsula.arena.echo2.component.div;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.update.ServerComponentUpdate;
import nextapp.echo2.webcontainer.ComponentSynchronizePeer;
import nextapp.echo2.webcontainer.ContainerInstance;
import nextapp.echo2.webcontainer.DomUpdateSupport;
import nextapp.echo2.webcontainer.RenderContext;
import nextapp.echo2.webcontainer.SynchronizePeerFactory;
import nextapp.echo2.webcontainer.image.ImageRenderSupport;
import nextapp.echo2.webcontainer.propertyrender.FillImageRender;
import nextapp.echo2.webrender.output.CssStyle;
import nextapp.echo2.webrender.servermessage.DomUpdate;

import org.openinsula.arena.echo2.component.util.StyleBuilder;
import org.openinsula.arena.echo2.component.util.TextAlignment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DivPeer implements ComponentSynchronizePeer, DomUpdateSupport, ImageRenderSupport {
	private static final String IMAGE_ID_BACKGROUND = "background";

	public String getContainerId(Component child) {
		return ContainerInstance.getElementId(child.getParent());
	}

	public void renderAdd(RenderContext rc, ServerComponentUpdate update, String targetId, Component component) {
		Element domAddElement = DomUpdate.renderElementAdd(rc.getServerMessage());
		DocumentFragment htmlFragment = rc.getServerMessage().getDocument().createDocumentFragment();
		renderHtml(rc, update, htmlFragment, component);
		DomUpdate.renderElementAddContent(rc.getServerMessage(), domAddElement, targetId, htmlFragment);
	}

	public void renderDispose(RenderContext rc, ServerComponentUpdate update, Component component) {
	}

	public boolean renderUpdate(RenderContext rc, ServerComponentUpdate update, String targetId) {
		DomUpdate.renderElementRemove(rc.getServerMessage(), ContainerInstance.getElementId(update.getParent()));
		renderAdd(rc, update, targetId, update.getParent());
		return false;
	}

	public void renderHtml(RenderContext rc, ServerComponentUpdate update, Node parentNode, Component component) {
		Document doc = rc.getServerMessage().getDocument();
		Element divE = doc.createElement("div");
		divE.setAttribute("id", ContainerInstance.getElementId(component));

		StyleBuilder builder = new StyleBuilder();

		Div div = (Div) component;

		{ // text alignment
			TextAlignment textAlignment = div.getTextAlignment();
			if (textAlignment != null) {
				builder.addProperty("text-align: ", textAlignment.getName());
			}
		}

		{ // background color
			Color backgroundColor = div.getBackground();
			if (backgroundColor != null && !"".equals(backgroundColor)) {
				builder.addProperty("background: ", backgroundColor);
			}
		}

		{ // background image
			ImageReference image = div.getBackgroundImage();
			if (image != null) {
				FillImage backgroundImage = new FillImage(image);

				CssStyle cssStyle = new CssStyle();
				FillImageRender.renderToStyle(cssStyle, rc, this, div, IMAGE_ID_BACKGROUND, backgroundImage, 0);

				builder.addProperty("", cssStyle.renderInline());
			}
		}

		{ // width
			Extent width = div.getWidth();
			if (width != null) {
				builder.addProperty("width: ", width);
			}
		}

		{ // heigth
			Extent height = div.getHeight();
			if (height != null) {
				builder.addProperty("height: ", height);
			}
		}

		{ // Border
			Border border = div.getBorder();
			if (border != null) {
				builder.addProperty("border-width: ", border.getSize());
				builder.setBorderStyle(border.getStyle());
				builder.addProperty("border-color: ", border.getColor());
			}
		}

		{ // Border Radius
			Extent border = div.getBorderRadius();
			if (border != null) {
				builder.addProperty("-moz-border-radius: ", border);
			}
		}

		{ // padding
			// left
			Extent paddingTop = div.getPaddingTop();
			if (paddingTop != null) {
				builder.addProperty("padding-top: ", paddingTop);
			}
			// left
			Extent paddingLeft = div.getPaddingLeft();
			if (paddingLeft != null) {
				builder.addProperty("padding-left: ", paddingLeft);
			}
			// bottom
			Extent paddingBottom = div.getPaddingBottom();
			if (paddingBottom != null) {
				builder.addProperty("padding-bottom: ", paddingBottom);
			}
			// right
			Extent paddingRight = div.getPaddingRight();
			if (paddingRight != null) {
				builder.addProperty("padding-right: ", paddingRight);
			}
		}

		{ // margin
			// top
			Extent marginTop = div.getMarginTop();
			if (marginTop != null) {
				builder.addProperty("margin-top: ", marginTop);
			}
			// left
			Extent marginLeft = div.getMarginLeft();
			if (marginLeft != null) {
				builder.addProperty("margin-left: ", marginLeft);
			}
			// bottom
			Extent marginBottom = div.getMarginBottom();
			if (marginBottom != null) {
				builder.addProperty("margin-bottom: ", marginBottom);
			}
			// right
			Extent marginRight = div.getMarginRight();
			if (marginRight != null) {
				builder.addProperty("margin-right: ", marginRight);
			}
		}

		{ // overflow
			String overflow = ((Div) component).getOverflow();
			if (overflow != null) {
				builder.addProperty("overflow: ", overflow);
			}
		}

		divE.setAttribute("style", builder.toString());

		String toolTipText = ((Div) component).getToolTipText();
		if (toolTipText != null) {
			divE.setAttribute("title", toolTipText);
		}

		Component[] children = component.getVisibleComponents();
		for (Component child : children) {
			renderChild(rc, update, child);
		}

		parentNode.appendChild(divE);
	}

	private void renderChild(RenderContext rc, ServerComponentUpdate update, Component child) {
		ComponentSynchronizePeer syncPeer = SynchronizePeerFactory.getPeerForComponent(child.getClass());
		syncPeer.renderAdd(rc, update, getContainerId(child), child);
	}

	public ImageReference getImage(Component component, String imageId) {
		if (IMAGE_ID_BACKGROUND.equals(imageId)) {
			return ((Div) component).getBackgroundImage();
		}

		return null;
	}
}

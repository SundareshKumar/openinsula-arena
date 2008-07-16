package org.openinsula.arena.echo2.component.rulerline;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.update.ServerComponentUpdate;
import nextapp.echo2.webcontainer.ComponentSynchronizePeer;
import nextapp.echo2.webcontainer.ContainerInstance;
import nextapp.echo2.webcontainer.DomUpdateSupport;
import nextapp.echo2.webcontainer.RenderContext;
import nextapp.echo2.webrender.servermessage.DomUpdate;

import org.openinsula.arena.echo2.component.util.StyleBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RulerLinePeer implements ComponentSynchronizePeer, DomUpdateSupport {
	public String getContainerId(Component arg0) {
		throw new IllegalStateException("RulerLinePeer does not work as a DOM container");
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
		Element hrE = doc.createElement("hr");
		
		hrE.setAttribute("id",ContainerInstance.getElementId(component));
		
		StyleBuilder builder = new StyleBuilder();
		
		RulerLine line = (RulerLine)component;
		
		{ // width
			int width = line.getWidth();
			hrE.setAttribute("width", Integer.toString(width));
		}
		
		{ // color
			Color color = line.getColor();
			if (color != null) {
				builder.addProperty("border:", "0pt none");
				builder.addProperty("height:", new Extent(1));
				builder.addProperty("color:", color);
				builder.addProperty("background-color:", color);
			}
		}
		
		hrE.setAttribute("style", builder.toString());
		
		parentNode.appendChild(hrE);
	}
}

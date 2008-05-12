package org.openinsula.arena.echo2.component.field;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.update.ServerComponentUpdate;
import nextapp.echo2.webcontainer.ActionProcessor;
import nextapp.echo2.webcontainer.ComponentSynchronizePeer;
import nextapp.echo2.webcontainer.ContainerInstance;
import nextapp.echo2.webcontainer.DomUpdateSupport;
import nextapp.echo2.webcontainer.FocusSupport;
import nextapp.echo2.webcontainer.PartialUpdateManager;
import nextapp.echo2.webcontainer.PartialUpdateParticipant;
import nextapp.echo2.webcontainer.PropertyUpdateProcessor;
import nextapp.echo2.webcontainer.RenderContext;
import nextapp.echo2.webcontainer.image.ImageRenderSupport;
import nextapp.echo2.webcontainer.partialupdate.BorderUpdate;
import nextapp.echo2.webcontainer.partialupdate.ColorUpdate;
import nextapp.echo2.webcontainer.partialupdate.InsetsUpdate;
import nextapp.echo2.webcontainer.propertyrender.AlignmentRender;
import nextapp.echo2.webcontainer.propertyrender.BorderRender;
import nextapp.echo2.webcontainer.propertyrender.ColorRender;
import nextapp.echo2.webcontainer.propertyrender.ExtentRender;
import nextapp.echo2.webcontainer.propertyrender.FillImageRender;
import nextapp.echo2.webcontainer.propertyrender.FontRender;
import nextapp.echo2.webcontainer.propertyrender.InsetsRender;
import nextapp.echo2.webrender.ServerMessage;
import nextapp.echo2.webrender.Service;
import nextapp.echo2.webrender.WebRenderServlet;
import nextapp.echo2.webrender.output.CssStyle;
import nextapp.echo2.webrender.servermessage.DomUpdate;
import nextapp.echo2.webrender.servermessage.WindowUpdate;
import nextapp.echo2.webrender.service.JavaScriptService;
import nextapp.echo2.webrender.util.DomUtil;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class NumberMaskFieldPeer implements ActionProcessor, ComponentSynchronizePeer, DomUpdateSupport, FocusSupport, ImageRenderSupport, PropertyUpdateProcessor {
	private static final String IMAGE_ID_BACKGROUND = "background";

    static final Service NUMBER_MASK_FIELD_COMPONENT_SERVICE = JavaScriptService.forResource("Echo.NumberMaskField", "/js/NumberMaskField.js");

    static {
        WebRenderServlet.getServiceRegistry().add(NUMBER_MASK_FIELD_COMPONENT_SERVICE);
    }
    
    private class TextUpdate implements PartialUpdateParticipant {
        public boolean canRenderProperty(RenderContext rc, ServerComponentUpdate update) {
            return true;
        }
    
        public void renderProperty(RenderContext rc, ServerComponentUpdate update) {
        	NumberMaskField numberMaskField = (NumberMaskField) update.getParent();
        	
        	String elementId = ContainerInstance.getElementId(numberMaskField);
        	ServerMessage serverMessage = rc.getServerMessage();
        	Element itemizedUpdateElement = serverMessage.getItemizedDirective(ServerMessage.GROUP_ID_POSTUPDATE, "EchoNumberMaskField.MessageProcessor", "set-text", new String[0], new String[0]);
        	Element itemElement = serverMessage.getDocument().createElement("item");
        	itemElement.setAttribute("eid", elementId);
        	
        	itemElement.setAttribute("text", numberMaskField.getText());
        	
        	itemizedUpdateElement.appendChild(itemElement);
        }
    }

    private PartialUpdateManager partialUpdateManager;

    public NumberMaskFieldPeer() {
        partialUpdateManager = new PartialUpdateManager();
        partialUpdateManager.add(NumberMaskField.PROPERTY_FOREGROUND, new ColorUpdate(NumberMaskField.PROPERTY_FOREGROUND, null, ColorUpdate.CSS_COLOR));
        partialUpdateManager.add(NumberMaskField.PROPERTY_BACKGROUND, new ColorUpdate(NumberMaskField.PROPERTY_BACKGROUND, null, ColorUpdate.CSS_BACKGROUND_COLOR));
        partialUpdateManager.add(NumberMaskField.PROPERTY_BORDER, new BorderUpdate(NumberMaskField.PROPERTY_BORDER, null, BorderUpdate.CSS_BORDER));
        partialUpdateManager.add(NumberMaskField.PROPERTY_INSETS, new InsetsUpdate(NumberMaskField.PROPERTY_INSETS, null, InsetsUpdate.CSS_PADDING));
        partialUpdateManager.add(NumberMaskField.TEXT_CHANGED_PROPERTY, new TextUpdate());
    }

    protected CssStyle createBaseCssStyle(RenderContext rc, NumberMaskField numberMaskField) {
        CssStyle cssStyle = new CssStyle();

        boolean renderEnabled = numberMaskField.isRenderEnabled();

        Border border;
        Color foreground, background;
        Font font;
        FillImage backgroundImage;
        if (!renderEnabled) {
            background = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_BACKGROUND);
            backgroundImage = (FillImage) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_BACKGROUND_IMAGE);
            border = (Border) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_BORDER);
            font = (Font) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_FONT);
            foreground = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_FOREGROUND);

            if (background == null) {
                background = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND);
                if (backgroundImage == null) {
                    backgroundImage = (FillImage) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND_IMAGE);
                }
            }
            if (border == null) {
                border = (Border) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BORDER);
            }
            if (font == null) {
                font = (Font) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_FONT);
            }
            if (foreground == null) {
                foreground = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_FOREGROUND);
            }
        } else {
            border = (Border) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BORDER);
            foreground = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_FOREGROUND);
            background = (Color) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND);
            font = (Font) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_FONT);
            backgroundImage = (FillImage) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND_IMAGE);
        }
        
        Alignment alignment = (Alignment) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_ALIGNMENT);
        if (alignment != null) {
            int horizontalAlignment = AlignmentRender.getRenderedHorizontal(alignment, numberMaskField);
            switch (horizontalAlignment) {
            case Alignment.LEFT:
                cssStyle.setAttribute("text-align", "left");
                break;
            case Alignment.CENTER:
                cssStyle.setAttribute("text-align", "center");
                break;
            case Alignment.RIGHT:
                cssStyle.setAttribute("text-align", "right");
                break;
            }
        }
        
        BorderRender.renderToStyle(cssStyle, border);
        ColorRender.renderToStyle(cssStyle, foreground, background);
        FontRender.renderToStyle(cssStyle, font);
        FillImageRender.renderToStyle(cssStyle, rc, this, numberMaskField, IMAGE_ID_BACKGROUND, backgroundImage, FillImageRender.FLAG_DISABLE_FIXED_MODE);
        
        InsetsRender.renderToStyle(cssStyle, "padding", (Insets) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_INSETS));
        
        Extent width = (Extent) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_WIDTH);
        Extent height = (Extent) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_HEIGHT);

        if (width != null) {
            cssStyle.setAttribute("width", ExtentRender.renderCssAttributeValue(width));
        }

        if (height != null) {
            cssStyle.setAttribute("height", ExtentRender.renderCssAttributeValue(height));
        }
        return cssStyle;
    }
    
    public String getContainerId(Component child) {
//    	System.out.println("getConteinerId");
    	
        throw new UnsupportedOperationException("Component does not support children.");
    }
    
    public ImageReference getImage(Component component, String imageId) {
//    	System.out.println("getImage");
    	
        if (IMAGE_ID_BACKGROUND.equals(imageId)) {
            FillImage backgroundImage;
            if (component.isRenderEnabled()) {
                backgroundImage = (FillImage) component.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND_IMAGE);
            } else {
                backgroundImage = (FillImage) component.getRenderProperty(NumberMaskField.PROPERTY_DISABLED_BACKGROUND_IMAGE);
                if (backgroundImage == null) {
                    backgroundImage = (FillImage) component.getRenderProperty(NumberMaskField.PROPERTY_BACKGROUND_IMAGE);
                }
            }
            if (backgroundImage == null) {
                return null;
            } else {
                return backgroundImage.getImage();
            }
        } else {
            return null;
        }
    }
    
    public void processAction(ContainerInstance ci, Component component, Element actionElement) {
//    	System.out.println("processAction");
    	
    	ci.getUpdateManager().getClientUpdateManager().setComponentAction(component, NumberMaskField.INPUT_ACTION, null);
    }
    
    public void processPropertyUpdate(ContainerInstance ci, Component component, Element propertyElement) {
//        System.out.println("processPropertyUpdate: " +  propertyElement.getAttribute(PropertyUpdateProcessor.PROPERTY_NAME));
        
        String propertyName = propertyElement.getAttribute(PropertyUpdateProcessor.PROPERTY_NAME);
        if (NumberMaskField.TEXT_CHANGED_PROPERTY.equals(propertyName)) {
            String propertyValue = DomUtil.getElementText(propertyElement);
            
            ci.getUpdateManager().getClientUpdateManager().setComponentProperty(component, NumberMaskField.TEXT_CHANGED_PROPERTY, propertyValue);
        } else if (NumberMaskField.PROPERTY_HORIZONTAL_SCROLL.equals(propertyName)) {
            Extent propertyValue = new Extent(Integer.parseInt(propertyElement.getAttribute(PropertyUpdateProcessor.PROPERTY_VALUE)));
            ci.getUpdateManager().getClientUpdateManager().setComponentProperty(component, NumberMaskField.PROPERTY_HORIZONTAL_SCROLL, propertyValue);
        } else if (NumberMaskField.PROPERTY_VERTICAL_SCROLL.equals(propertyName)) {
            Extent propertyValue = new Extent(Integer.parseInt(propertyElement.getAttribute(PropertyUpdateProcessor.PROPERTY_VALUE)));
            ci.getUpdateManager().getClientUpdateManager().setComponentProperty(component, NumberMaskField.PROPERTY_VERTICAL_SCROLL, propertyValue);
        }
    }
    
    public void renderAdd(RenderContext rc, ServerComponentUpdate update, String targetId, Component component) {
//    	System.out.println("renderAdd");
    	
        Element domAddElement = DomUpdate.renderElementAdd(rc.getServerMessage());
        DocumentFragment htmlFragment = rc.getServerMessage().getDocument().createDocumentFragment();
        renderHtml(rc, update, htmlFragment, component);
        DomUpdate.renderElementAddContent(rc.getServerMessage(), domAddElement, targetId, htmlFragment);
    }
    
    public void renderDispose(RenderContext rc, ServerComponentUpdate update, Component component) {
//    	System.out.println("renderDispose");
    	
        rc.getServerMessage().addLibrary(NUMBER_MASK_FIELD_COMPONENT_SERVICE.getId());
        
        String elementId = ContainerInstance.getElementId((NumberMaskField) component);
        ServerMessage serverMessage = rc.getServerMessage();
        Element itemizedUpdateElement = serverMessage.getItemizedDirective(ServerMessage.GROUP_ID_PREREMOVE, "EchoNumberMaskField.MessageProcessor", "dispose", new String[0], new String[0]);
        Element itemElement = serverMessage.getDocument().createElement("item");
        itemElement.setAttribute("eid", elementId);
        itemizedUpdateElement.appendChild(itemElement);
    }

    public void renderSetFocus(RenderContext rc, Component component) {
//    	System.out.println("renderSetFocus");
    	
        WindowUpdate.renderSetFocus(rc.getServerMessage(), ContainerInstance.getElementId(component));
    }
    
    public boolean renderUpdate(RenderContext rc, ServerComponentUpdate update, String targetId) {
//    	System.out.println("renderUpdate");
    	
        boolean fullReplace = false;
        if (update.hasUpdatedProperties()) {
            if (!partialUpdateManager.canProcess(rc, update)) {
                fullReplace = true;
            }
        }

        if (fullReplace) {
        	String elementId = ContainerInstance.getElementId(update.getParent());
			String hiddenElementId = elementId + "_hidden";
			DomUpdate.renderElementRemove(rc.getServerMessage(), elementId);
			DomUpdate.renderElementRemove(rc.getServerMessage(), hiddenElementId);
            renderAdd(rc, update, targetId, update.getParent());
        } else {
            partialUpdateManager.process(rc, update);
        }

        return false;
    }
    
    public void renderHtml(RenderContext rc, ServerComponentUpdate addUpdate, Node parentNode, Component component) {
//    	System.out.println("renderHtml");
    	
        NumberMaskField numberMaskField = (NumberMaskField) component;
        String elementId = ContainerInstance.getElementId(numberMaskField);

        ServerMessage serverMessage = rc.getServerMessage();
        serverMessage.addLibrary(NUMBER_MASK_FIELD_COMPONENT_SERVICE.getId());
        
        Element inputElement = parentNode.getOwnerDocument().createElement("input");
        inputElement.setAttribute("id", elementId);
        inputElement.setAttribute("type", "text");
        String value = numberMaskField.getText();
        if (value != null) {
            inputElement.setAttribute("value", value);
        }
        
        if (numberMaskField.isFocusTraversalParticipant()) {
            inputElement.setAttribute("tabindex", Integer.toString(numberMaskField.getFocusTraversalIndex()));
        } else {
            inputElement.setAttribute("tabindex", "-1");
        }
        
        String toolTipText = (String) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_TOOL_TIP_TEXT);
        if (toolTipText != null) {
            inputElement.setAttribute("title", toolTipText);
        }
        
        Integer maximumLength = (Integer) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_MAXIMUM_LENGTH);
        if (maximumLength != null) {
            inputElement.setAttribute("maxlength", maximumLength.toString());
        }

        CssStyle cssStyle = createBaseCssStyle(rc, numberMaskField);
        if (cssStyle.hasAttributes()) {
            inputElement.setAttribute("style", cssStyle.renderInline());
        }
        parentNode.appendChild(inputElement);
        
        Element inputDecimalSizeElement = parentNode.getOwnerDocument().createElement("input");
		inputDecimalSizeElement.setAttribute("id", elementId + "_hidden");
		inputDecimalSizeElement.setAttribute("type", "hidden");
		inputDecimalSizeElement.setAttribute("value", numberMaskField.getNumberMask());
        
		parentNode.appendChild(inputDecimalSizeElement);
		
        renderInitDirective(rc, numberMaskField);
    }
    
    public void renderInitDirective(RenderContext rc, NumberMaskField numberMaskField) {
    	Extent horizontalScroll = (Extent) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_HORIZONTAL_SCROLL);
        Extent verticalScroll = (Extent) numberMaskField.getRenderProperty(NumberMaskField.PROPERTY_VERTICAL_SCROLL);
        String elementId = ContainerInstance.getElementId(numberMaskField);
        ServerMessage serverMessage = rc.getServerMessage();

        Element itemizedUpdateElement = serverMessage.getItemizedDirective(ServerMessage.GROUP_ID_POSTUPDATE, "EchoNumberMaskField.MessageProcessor", "init", new String[0], new String[0]);
        Element itemElement = serverMessage.getDocument().createElement("item");
        itemElement.setAttribute("eid", elementId);
        if (horizontalScroll != null && horizontalScroll.getValue() != 0) {
            itemElement.setAttribute("horizontal-scroll", ExtentRender.renderCssAttributePixelValue(horizontalScroll, "0"));
        }
        if (verticalScroll != null && verticalScroll.getValue() != 0) {
            itemElement.setAttribute("vertical-scroll", ExtentRender.renderCssAttributePixelValue(verticalScroll, "0"));
        }
        
        if (!numberMaskField.isRenderEnabled()) {
            itemElement.setAttribute("enabled", "false");
        }
        if (numberMaskField.hasActionListeners()) {
            itemElement.setAttribute("server-notify", "true");
        }
        
        itemizedUpdateElement.appendChild(itemElement);
    }
}
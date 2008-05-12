package org.openinsula.arena.echo2.component.div;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.ResourceImageReference;

import org.apache.log4j.Logger;
import org.openinsula.arena.echo2.component.util.TextAlignment;

public class Div extends Component {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Div.class);

	/* constantes para usar no Styles */
	public static final String PROPERTY_TEXT_ALIGNMENT = "text-alignment";

	public static final String PROPERTY_BORDER = "border";

	public static final String PROPERTY_WIDTH = "width";

	public static final String PROPERTY_HEIGHT = "height";

	public static final String PROPERTY_BACKGROUND_IMAGE = "backgroundImage";

	public static final String PROPERTY_OVERFLOW = "overflow";

	public static final String PROPERTY_PADDING_TOP = "paddingTop";

	public static final String PROPERTY_PADDING_BOTTOM = "paddingBottom";

	public static final String PROPERTY_PADDING_LEFT = "paddingLeft";

	public static final String PROPERTY_PADDING_RIGHT = "paddingRight";

	public static final String PROPERTY_MARGIN_TOP = "marginTop";

	public static final String PROPERTY_MARGIN_BOTTOM = "marginBottom";

	public static final String PROPERTY_MARGIN_LEFT = "marginLeft";

	public static final String PROPERTY_MARGIN_RIGHT = "marginRight";

	public static final String OVERFLOW_HIDDEN = "hidden";

	public static final String OVERFLOW_VISIBLE = "visible";

	public static final String OVERFLOW_SCROLL = "scroll";

	public static final String OVERFLOW_AUTO = "auto";

	public static final String OVERFLOW_INHERIT = "inherit";

	@Override
	public void setProperty(String propertyName, Object newValue) {
		if (propertyName == null) {
			return;
		}

		try {
			if (propertyName.equals(PROPERTY_BACKGROUND)) {
				super.setProperty(propertyName, newValue);
			}
			else if (propertyName.equals(PROPERTY_BACKGROUND_IMAGE)) {
				if (newValue instanceof String) {
					this.setBackgroundImage((String) newValue);
				}
				else if (newValue instanceof ImageReference) {
					this.setBackgroundImage((ImageReference) newValue);
				}
			}
			else if (propertyName.equals(PROPERTY_BORDER)) {
				this.setBorder((Border) newValue);
			}
			else if (propertyName.equals(PROPERTY_FONT)) {
				super.setProperty(propertyName, newValue);
			}
			else if (propertyName.equals(PROPERTY_FOREGROUND)) {
				super.setProperty(propertyName, newValue);
			}
			else if (propertyName.equals(PROPERTY_HEIGHT)) {
				this.setHeight((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_LAYOUT_DATA)) {
				super.setProperty(propertyName, newValue);
			}
			else if (propertyName.equals(PROPERTY_MARGIN_BOTTOM)) {
				this.setMarginBottom((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_MARGIN_LEFT)) {
				this.setMarginLeft((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_MARGIN_RIGHT)) {
				this.setMarginRight((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_MARGIN_TOP)) {
				this.setMarginTop((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_OVERFLOW)) {
				this.setOverflow((String) newValue);
			}
			else if (propertyName.equals(PROPERTY_PADDING_BOTTOM)) {
				this.setPaddingBottom((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_PADDING_LEFT)) {
				this.setPaddingLeft((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_PADDING_RIGHT)) {
				this.setPaddingRight((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_PADDING_TOP)) {
				this.setPaddingTop((Extent) newValue);
			}
			else if (propertyName.equals(PROPERTY_TEXT_ALIGNMENT)) {
				this.setTextAlignment((TextAlignment) newValue);
			}
			else if (propertyName.equals(PROPERTY_WIDTH)) {
				this.setWidth((Extent) newValue);
			}
		}
		catch (Exception e) {
			logger.warn("Propriedade de tipo (" + newValue.getClass().getName() + ") incorreta para o atributo: "
					+ propertyName);
			logger.error(e.getMessage());
		}

	}

	private ImageReference backgroundImage;

	private Extent width;

	private Extent height;

	private Border border;

	private String toolTipText;

	private String overflow;

	private Extent paddingTop;

	private Extent paddingLeft;

	private Extent paddingBottom;

	private Extent paddingRight;

	private Extent marginTop;

	private Extent marginLeft;

	private Extent marginBottom;

	private Extent marginRight;

	private Extent borderRadius;

	private TextAlignment textAlignment;

	public Div() {
		this.overflow = OVERFLOW_AUTO;
	}

	public void setPadding(Extent top, Extent left, Extent bottom, Extent right) {
		setPaddingTop(top);
		setPaddingLeft(left);
		setPaddingBottom(bottom);
		setPaddingRight(right);
	}

	public void setPadding(Extent margin) {
		setPaddingTop(margin);
		setPaddingLeft(margin);
		setPaddingBottom(margin);
		setPaddingRight(margin);
	}

	public void setMargin(Extent margin) {
		setMarginTop(margin);
		setMarginLeft(margin);
		setMarginBottom(margin);
		setMarginRight(margin);
	}

	public void setMargin(Extent top, Extent left, Extent bottom, Extent right) {
		setMarginTop(top);
		setMarginLeft(left);
		setMarginBottom(bottom);
		setMarginRight(right);
	}

	public Extent getWidth() {
		return width;
	}

	public void setWidth(Extent width) {
		this.width = width;
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

	public String getToolTipText() {
		return toolTipText;
	}

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	public String getOverflow() {
		return overflow;
	}

	public void setOverflow(String overflow) {
		this.overflow = overflow;
	}

	public Extent getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(Extent paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public Extent getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(Extent paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public Extent getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(Extent paddingRight) {
		this.paddingRight = paddingRight;
	}

	public Extent getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(Extent paddingTop) {
		this.paddingTop = paddingTop;
	}

	public Extent getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(Extent marginBottom) {
		this.marginBottom = marginBottom;
	}

	public Extent getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(Extent marginLeft) {
		this.marginLeft = marginLeft;
	}

	public Extent getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(Extent marginRight) {
		this.marginRight = marginRight;
	}

	public Extent getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(Extent marginTop) {
		this.marginTop = marginTop;
	}

	public ImageReference getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		ImageReference imageReference = new ResourceImageReference(backgroundImage);
		this.backgroundImage = imageReference;
	}

	public void setBackgroundImage(ImageReference backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Extent getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Extent borderRadius) {
		this.borderRadius = borderRadius;
	}

	public Extent getHeight() {
		return height;
	}

	public void setHeight(Extent height) {
		this.height = height;
	}

	public TextAlignment getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(TextAlignment textAlignment) {
		this.textAlignment = textAlignment;
	}

}

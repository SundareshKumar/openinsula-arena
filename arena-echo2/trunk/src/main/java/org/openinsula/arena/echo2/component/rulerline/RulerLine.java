package org.openinsula.arena.echo2.component.rulerline;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;

public class RulerLine extends Component {
    
    private static final long serialVersionUID = 1L;
    
    private int width;
    
    private Color color;
    
    public RulerLine() {
    }
    
    public RulerLine(int width) {
        this.width = width;
    }

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}

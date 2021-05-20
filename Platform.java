import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Platform extends Drawable {
	private double width = 50;
	private double height = 50;
	private boolean isRightEdge;
	private boolean isLeftEdge;
	
	public Platform(double x, double y) {
		super(x, y);
		this.setColor(Color.DARK_GRAY);
		this.setShape(new Rectangle2D.Double(x, y, this.width, this.height));
		this.isLeftEdge = false;
		this.isRightEdge = false;
	}
	
	public void setIsRightEdge() {
		this.isRightEdge = true;
	}
	
	public void setIsLeftEdge() {
		this.isLeftEdge = true;
	}

	public boolean isLeftEdge() {
		return isLeftEdge;
	}
	
	public boolean isRightEdge() {
		return isRightEdge;
	}


	
}

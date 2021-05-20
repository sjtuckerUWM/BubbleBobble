import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Fruit extends Drawable {

	public Fruit(double x, double y) {
		super(x, y);
		this.width = 10;
		this.height = 10;
		this.setColor(Color.ORANGE);
		this.setShape(new Rectangle2D.Double(x, y, width, height));
	}

}

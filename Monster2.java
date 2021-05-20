import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Monster2 extends Monster {

	Point2D heroToFollow;

	public Monster2(double x, double y) {
		super(x, y);
		this.type = 2;
		setHeight(37.5);
		setWidth(37.5);
		this.shooter = true;
		this.setColor(Color.GREEN);
		this.setShape(new Rectangle2D.Double(x, y, this.getWidth(), this.getHeight()));
	}

	public void updatePosition(ArrayList<Platform> platforms, Point2D heroLocation) {
		checkHeroPosition(heroLocation);
		super.updatePosition(platforms);
	}

	private void checkHeroPosition(Point2D heroPosition) {
		// TODO Auto-generated method stub
		if (this.getX() < heroPosition.getX() + 30 && this.getX() > heroPosition.getX() - 30) {
			if (this.getY() > heroPosition.getY()) {
				jumpUp();
			}
		}
		if (this.getX() < heroPosition.getX()) {
			if (this.getVelocityX() <= 0) {
				this.updateVelocityX(.25);
			}
		}
		if (this.getX() > heroPosition.getX()) {
			if (this.getVelocityX() >= 0) {
				this.updateVelocityX(-.25);
			}
		}
	}
	

}

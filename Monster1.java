import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Monster1 extends Monster {

	public Monster1(double x, double y) {
		super(x, y);
		this.type = 1;
		this.setWidth(25);
		this.setHeight(25);
		this.setColor(Color.RED);
		this.setShape(new Rectangle2D.Double(x, y, this.getWidth(), this.getHeight()));
		this.updateVelocityX(.5);
		this.isOnGround();

	}

	public void updatePosition(ArrayList<Platform> platforms, Point2D heroLocation) {
		this.setX(getX() + getVelocityX());
		this.setY(getY() + getVelocityY());
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
		for(Platform p: platforms) {
			if(isOnPlatform(p) && isOnEdge(p)) {
				this.switchDirections('x');
			}
		}
	}
	
	public boolean isOnPlatform(Platform p) {
		if(this.getY() + this.getHeight() == p.getY()) {
			return true;
		}
		return false;
	}
//	
//	private boolean isOnEdge(Platform p) {
//		if(p.isLeftEdge() && (this.getX() <= p.getX() && this.getX() + this.getWidth() >= p.getX())) {
//			return true;
//		}
//		else if(p.isRightEdge() && (this.getX() + this.getWidth() >= p.getX() + 50 && this.getX() <= p.getX() + 50)) {
//			return true;
//		}
//		return false;
//	}
//
//	private void switchDirections() {
//		this.updateVelocityX(-this.getVelocityX());
//	}



}

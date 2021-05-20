import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Bullet extends Movable{

	private Point2D heroToShoot;
	
	public Bullet(double x, double y, Point2D heroLocation) {
		super(x, y);
		this.heroToShoot = heroLocation;
		this.width = 15;
		this.height = 15;
		setVelocities();
		this.setColor(Color.BLACK);
		this.setShape(new Rectangle2D.Double(x, y, this.width, this.height));
	}
	
	private void setVelocities() {
		// TODO Auto-generated method stub
		double distanceX = Math.pow(heroToShoot.getX() - this.getX(), 2);
		double distanceY = Math.pow(heroToShoot.getY() - this.getY(), 2);
		double totalD = Math.sqrt(distanceX + distanceY);
		double xVector = (heroToShoot.getX() - this.getX()) / totalD;
		double yVector = (heroToShoot.getY() - this.getY()) / totalD;
		this.updateVelocityX(.75 * xVector);
		this.updateVelocityY(.75 * yVector);
	}

	public void travel() {
		this.setX(getX() + getVelocityX());
		this.setY(getY() + getVelocityY());
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}
	
	

}

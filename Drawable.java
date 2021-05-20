import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public abstract class Drawable {
	
	public double width;
	public double height;
	private Color color;
	private Shape shape;
	private double x;
	private double y;
	private double velocityX; 
	private double velocityY; 
	public boolean alive;
	
	public Drawable(double x, double y) {
		this.x = x;
		this.y = y;
		this.velocityX = 0;
		this.velocityY = 0;
		alive = true;
	}
	public Color getColor() {
		return this.color;
	}
	public Shape getShape() {
		return this.shape;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
//	public double getWidth() {
//		return this.width;
//	}
//	public double getHeight() {
//		return this.height;
//	}
	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fill(this.shape);
	}

	public void updateVelocityX(double x) {
		this.velocityX = x;
	}
	
	public void updateVelocityY(double y) {
		this.velocityY = y;
	}
	
	public double getVelocityX() {
		return this.velocityX;
	}
	
	public double getVelocityY() {
		return this.velocityY;
	}
	
	public void disable() {
		this.alive = false;
		this.width = 0;
		this.height = 0;
		this.updateVelocityX(0);
		this.updateVelocityY(0);
	}
	
	public void updatePosition() {
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.width, this.height));
	}
}

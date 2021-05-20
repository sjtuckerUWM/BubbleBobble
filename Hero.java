import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class Hero extends Movable {

	private int lives;
	private boolean isFacingRight = true;


	public Hero(double x, double y) {
		super(x, y);
		lives = 3;
		this.setShape(new Rectangle2D.Double(x, y, this.getWidth(), this.getHeight()));
		this.setColor(Color.BLUE);
		this.height = 37.5;
		this.width = 37.5;
	}

//	@Override
//	public void updatePosition(ArrayList<Platform> platforms) {
//		this.applyG();
//		this.checkBounds();
//		this.setX(getX() + getVelocityX());
//		this.setY(getY() + getVelocityY());
//		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
//		if (this.getVelocityY() >= 0)
//			checkGround(platforms);
//		if (onGround)
//			checkIfFalling(platforms);
//	} Removed because put in Movable class

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}


	public boolean getIsFacingRight() {
		// TODO Auto-generated method stub
		return this.isFacingRight;
	}
	
	public void setIsFacingRight(boolean direction) {
		this.isFacingRight = direction;
	}
	
	public void loseLife() {
		lives--;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}

	public void respawn() {
		this.setX(originalX);
		this.setY(originalY);
		
	}

}

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class Movable extends Drawable {

	public double originalX;
	public double originalY;
	public boolean onGround;
	public boolean gApplied;
	public boolean collision;
	
	
	public Movable(double x, double y) {
		super(x, y);
		onGround = true;
		originalX = x;
		originalY = y;
		

		// TODO Auto-generated constructor stub
	}
	
	
	public void updatePosition(ArrayList<Platform> platforms) {
		this.applyG();
		this.checkBounds();
		this.setX(getX() + getVelocityX());
		this.setY(getY() + getVelocityY());
		this.setShape(new Rectangle2D.Double(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
		if (this.getVelocityY() >= 0)
			checkGround(platforms);
		if (onGround)
			checkIfFalling(platforms);
	}
	
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
	
	public void checkBounds() {
		int mostLeft = 0;
		int mostRight = 600;
		if(this.getX() <= mostLeft) {
			this.updateVelocityX(.1);
		}
		if(this.getX()+this.width >= mostRight) {
			this.updateVelocityX(-.1);
		}
		if(this.getY() <= 0) {
			this.updateVelocityY(.1);
		}
	}

	
	public void applyG() {
		if (this.onGround == false) {
			if (this.getVelocityY() < 1.5) {
				this.updateVelocityY(this.getVelocityY() + .1);
			}
			setgApplied(true);
		}
	}
	
	public boolean onPlatform(Platform p) {
		double oRight = this.getX() + this.width;
		double oLeft = this.getX();
		double platformRight = p.getX() + 50;
		double platformLeft = p.getX();
		boolean onPlatformRight = platformRight > oRight && platformLeft < oRight;
		boolean onPlatformLeft = platformRight > oLeft && platformLeft < oLeft;
		if(onPlatformRight) {
			return true;
		}
		else if(onPlatformLeft) {
			return true;
		}
		return false;
	}
	
	public boolean atPlatformHeight(Platform p){	
		double threshold = Math.sqrt(Math.pow(p.getY() - this.getY() - this.height, 2));
		return threshold <= this.getVelocityY();
	}
	public void checkIfFalling(ArrayList<Platform> platforms) {
		for(Platform p: platforms) {
			if(atPlatformHeight(p)) {
				if(onPlatform(p)) {
					return;
				}
			}
		}
		setOnGround(false);
	}

	public void checkGround(ArrayList<Platform> platforms) {
		for (Platform p : platforms) {
			if (atPlatformHeight(p)) {
				if (onPlatform(p)) {
					if (isgApplied()) {
						this.updateVelocityY(0);
						this.setY(p.getY() - this.height);
						setgApplied(false);
					}
					setOnGround(true);
				} 		
			}
		}
	}
	public boolean isOnPlatform(Platform p) {
		if(this.getY() + this.getHeight() == p.getY()) {
			return true;
		}
		return false;
	}
	
	protected boolean isOnEdge(Platform p) {
		if(p.isLeftEdge() && (this.getX() <= p.getX() && this.getX() + this.getWidth() >= p.getX())) {
			return true;
		}
		else if(p.isRightEdge() && (this.getX() + this.getWidth() >= p.getX() + 50 && this.getX() <= p.getX() + 50)) {
			return true;
		}
		return false;
	}

	protected void switchDirections(char XorY) {
		if(XorY == 'x') {
			this.updateVelocityX(-this.getVelocityX());
		}
		if(XorY == 'y') {
			this.updateVelocityY(-this.getVelocityY());
		}
		
	}
	
	public void jumpUp() {
		if (onGround == true) {
			this.updateVelocityY(-5);
			onGround = false;
			gApplied = true;
		}
	}
		
	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public boolean isgApplied() {
		return gApplied;
	}

	public void setgApplied(boolean gApplied) {
		this.gApplied = gApplied;
	}
	


}
